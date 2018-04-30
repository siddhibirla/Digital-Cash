import java.io.*;
import java.util.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.math.BigInteger;
public class Init
{
public static void main(String[] args)
{
boolean t=true;
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
//System.out.println(line1);
String array3[] = line1.split("::");
System.out.println(array3[0]);
System.out.println("Select Option :");
System.out.println("1. Customer");
System.out.println("2. Merchant");
System.out.println("3.Exit");
Scanner sc=new Scanner(System.in);
String option=sc.nextLine();
switch(option)
{
case "Customer":
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
      int moneyorder=sc.nextInt();
      Customer c = new Customer(name,ssn);
      c.Create_Moneyorder(moneyorder);
      for(int i=0;i<c.random.size();i++)
      {
        System.out.println("From Customer random");
        System.out.println(c.random.get(i));
      }
      break;
case "Merchant":
      System.out.println("Merchant option");
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
