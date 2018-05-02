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
public int to_send;
private static ArrayList<String> decrypted_message= new ArrayList <>();
private static int money_order1;
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
  to_send = to_select.nextInt(randomord); // Limit values to total number of orders
  }
  else
  {
    System.out.println("No money order to process");
  }
  return to_send;
}
public String unblindingmoney(ArrayList<BigInteger> a1, ArrayList<byte[]> a2,BigInteger e1, BigInteger n1)
{
for(int i=0;i<a1.size();i++)
{
  BigInteger inverse=(a1.get(i)).modPow(e1,n1);
  byte[] decrypted = (((new BigInteger(a2.get(i))).divide(inverse)).toByteArray());
  //System.out.println("-------------------");//comment
  String temp= new String(decrypted);
  //System.out.println(temp);//comment
//  System.out.println("-------------------");//comment
  decrypted_message.add(temp);
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
public void ID_check()
{
  System.out.println("ID check and write to file");
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
     System.out.println(to_checkarr[i]+"="+Integer.toString(money_order1));
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
public void Signature()
{
  System.out.println("Signature");
}
public void Doublespendcheck()
{
  System.out.println("Check");
}
}
