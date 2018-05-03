import java.io.*;
import java.util.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import javax.crypto.*;
public class Init
{
public static void main(String[] args)
{
boolean t=true;
int total_orders=0;
String filename="PK_M.txt";
String line="";
String line1="";
BigInteger public_key;
BigInteger modulusn;
BigInteger phi;
try
{
FileReader filereader = new FileReader(filename);
BufferedReader buff = new BufferedReader(filereader);
while((line=buff.readLine())!=null)
{
line1=line1+line;
}
buff.close();
}
catch(Exception e)
{
  System.out.println("File not found");
}
String array3[] = line1.split("::");
public_key = new BigInteger(array3[0]);
modulusn = new BigInteger(array3[1]);
phi= new BigInteger(array3[2]);
BigInteger d =public_key.modInverse(phi);
/*System.out.println(public_key);
System.out.println(modulusn);
System.out.println(phi);
System.out.println(array3[0]);*/
Customer c =new Customer();
Bank b = new Bank();
int moneyorder=0;
while(t)
{
System.out.println("Select Option :");
System.out.println("1. Customer");
System.out.println("2. Bank");
System.out.println("3. Merchant");
System.out.println("4. Exit");
Scanner sc=new Scanner(System.in);
String option=sc.nextLine();
switch(option)
{
case "Customer":
      System.out.println("Customer option selected");
      String name="";
      int ssn=0;
      System.out.println("Enter your Name and SSN");
      try
      {
          name=sc.nextLine();
          ssn=sc.nextInt();
          String ssn1=Integer.toString(ssn);
          if(ssn1.length()<9)
          {
          String error="Less than 9 digits in SSN";
          throw new Exception();
          }
      }
      catch(Exception e)
      {
        System.out.println("Enter valid name/SSN");
        ssn=sc.nextInt();
      }
      System.out.println("Enter the money order amount");
      moneyorder=sc.nextInt();
      c= new Customer(name,ssn);
      total_orders=c.Create_Moneyorder(moneyorder);
      c.Blinding(public_key,modulusn);
      break;
case "Bank":
      int unblindvar=0;
      System.out.println("Bank option selected");
      b = new Bank(total_orders,d,moneyorder);
      int z =b.to_selectorder();
      unblindvar=z;
      ArrayList<BigInteger> ar1= new ArrayList<>();
      ar1=c.Unblindkey(z);
      ArrayList<byte[]> ar2= new ArrayList<>();
      ar2=c.get_Encrypted();
      String to_print=b.unblindingmoney(ar1,ar2,public_key,modulusn);
      if(to_print.equals("Transaction is valid,all money orders are okay"))
      {
         System.out.println(to_print);
         System.out.println("Bank is signing the money order");
         byte[] temp1=b.Signature();
         c.Received_signedorder(temp1,public_key,modulusn);
      }
      else
      {
        System.out.println(to_print);
        System.out.println("Bank cannot sign this money order");
      }
      break;
case "Merchant":
      System.out.println("Merchant option selected");
      Merchant m = new Merchant();
      String cstr=m.challenge();
      byte[] temp_5=c.sendordertomerchant();
      String m2_temp=m.received_orderfromCustomer(temp_5,public_key,modulusn);
      ArrayList<byte[]> temp_4=new ArrayList<>();
      temp_4=c.challenge_merchant(cstr);
      boolean testing = true;
      //m.tocheckhash(temp_4);
      System.out.println(testing);
      if(testing)
      {
        System.out.println("Identity bits valid, hash is valid, will send order to bank");
        boolean r = b.ID_check(m2_temp);
        if(r)
        {
          System.out.println("Merchant-Bank Transaction Approved");
        }
        else
        {
            System.out.println("CHEATING!!! WILL PUBLISH IDENTITY OF CUSTOMER");
        }
      }
      else
      {
        System.out.println("Identity bits not valid, hash is not valid, CANNOT PROCESS THIS ORDER");
      }
      break;
case "Exit":
      t=false;
      break;
default:
      System.out.println("Enter correct option");
      break;
}
}
}
}
