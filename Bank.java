import java.io.*;
import java.util.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.io.BufferedWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import javax.crypto.*;
public class Bank
{
public static int randomord;
private static BigInteger privatekey;
public static int to_send;
private static ArrayList<String> decrypted_message= new ArrayList <>();
private static int money_order1;
private static BigInteger publickey_bank;
private static BigInteger modulus_bank;
private static byte[] temp_bankstore;
private static ArrayList<String> ID = new ArrayList<>();
public Bank(int random_toselect, BigInteger pvkey,int money_order)
{
money_order1=money_order;
randomord=random_toselect;
privatekey=pvkey;
}
public int to_selectorder()
{
  to_send=0;
  if(randomord!=0)
  {
  Random to_select = new Random();
  int Max=randomord-1;
  int Min=0;
//  to_send = to_select.nextInt(randomord); // Limit values to total number of orders
  to_send= Min + to_select.nextInt(Max - Min + 1);
  }
  else
  {
    System.out.println("No money order to process");
  }
  return to_send;
}
public String unblindingmoney(ArrayList<BigInteger> a1, ArrayList<byte[]> a2,BigInteger e1, BigInteger n1)
{
publickey_bank=e1;
modulus_bank=n1;
temp_bankstore=a2.get(to_send);
for(int i=0;i<a1.size();i++)
{
  if(i!=to_send)
  {
  BigInteger inverse=(a1.get(i)).modPow(e1,n1);
  byte[] decrypted = (((new BigInteger(a2.get(i))).divide(inverse)).toByteArray());
  //System.out.println("-------------------");//comment
  String temp= new String(decrypted);
//  System.out.println(temp);//comment
//  System.out.println("-------------------");//comment
  decrypted_message.add(temp);
}
else
{
  decrypted_message.add(new String(a2.get(i)));
}
}
try
{
writing_id();
}
catch(Exception e)
{
  System.out.println("Cant write ID to file");
}
boolean moc=money_check();
if(moc==true)
{
return "Transaction is valid,all money orders are okay";
}
return "Trying to Cheat the bank ";
}
private void writing_id()throws IOException
{
  File file = new File("ID_Mo.txt");
  file.createNewFile();
  FileWriter writer = new FileWriter(file);
  String to_write="";
  System.out.println(to_send);
  for(int i=0;i<decrypted_message.size();i++)
  {
  if(i==to_send)
  {
  to_write=to_write+"00N/A"+"::"+"00N/A"+"::";
  }
  else if(i==(decrypted_message.size()-1))
  {
    String to_write1[]=(decrypted_message.get(i)).split("::");
    to_write=to_write+to_write1[0]+"::"+to_write1[1];
  }
  else
  {
  String to_write1[]=(decrypted_message.get(i)).split("::");
  to_write=to_write+to_write1[0]+"::"+to_write1[1]+"::";
  }
  }
  writer.write(to_write);
  writer.flush();
  writer.close();
}
public void Merchant_order()
{
  System.out.println("Order from merchant");
}
public boolean ID_check(String idtocheck)
{
  String filename2="ID_Mo.txt";
  String line1="";
  String line2="";
  String to_checkarr2[];
  String array_split[]=idtocheck.split("::");
  String IDtocheck_here=array_split[0];
  boolean to_merchant=true;
  try
  {
  FileReader filereader2 = new FileReader(filename2);
  BufferedReader buff2 = new BufferedReader(filereader2);
  while((line1=buff2.readLine())!=null)
  {
  line2=line2+line1;
  }
  buff2.close();
  }
  catch(Exception e)
  {
    System.out.println("File not found");
  }
  to_checkarr2=line2.split("::");
  for(int i=0;i<to_checkarr2.length;i=i+2)
  {
    if(i!=((to_send*2)-1))
    {
    ID.add(to_checkarr2[i]);
    System.out.println("here here here");
    }
  }
  if(ID.contains(IDtocheck_here))
  {
    return false;
  }
  return to_merchant;
}
public boolean money_check()
{
 String filename1="ID_Mo.txt";
 String line1="";
 String line2="";
 String to_checkarr[];
 boolean f = true;
  try
  {
  FileReader filereader1 = new FileReader(filename1);
  BufferedReader buff1 = new BufferedReader(filereader1);
  while((line1=buff1.readLine())!=null)
  {
  line2=line2+line1;
  }
  buff1.close();
  }
  catch(Exception e)
  {
    System.out.println("File not found");
  }
to_checkarr=line2.split("::");
for(int i=1;i<to_checkarr.length;i=i+2)
{
  if(i!=((to_send*2)+1))
  {
   if(to_checkarr[i].equals(Integer.toString(money_order1)))
   {
  //System.out.println(to_checkarr[i]+"="+Integer.toString(money_order1));
     f=f;
   }
   else
   {
     f=false;
     break;
   }
  }
}
return f;
}
public byte[] Signature()
{
//String to_sign=decrypted_message.get(to_send);
//String to_sign1=to_sign;//"::"+to_sign.hashCode();
System.out.println("Bank");
//byte[] temp_message2 = to_sign1.getBytes();
System.out.println(temp_bankstore);
byte[] signed_message=((((new BigInteger(temp_bankstore)).modPow(privatekey,modulus_bank))).toByteArray());
return signed_message;
}
public void Doublespendcheck(String IDcheckmess)
{
/*String array_split[]=IDcheckmess.split("::");
String IDtocheck_here=array_split[0];
System.out.println(IDtocheck_here);
for(int i=0;i<ID.size();i++)
{
  System.out.println(ID.get(i));
}
if(ID.contains(IDtocheck_here))
{
  return false;
}
return true;*/
}
public Bank()
{
}
}
