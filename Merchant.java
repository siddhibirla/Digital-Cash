import java.io.*;
import java.util.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.io.BufferedWriter;
import javax.crypto.*;
public class Merchant
{
public static String challenge_tosendbank;
public static String decrypting_temp1;
private static ArrayList<byte[]> temp_tobank = new ArrayList<>();
public Merchant()
{
  System.out.println("Merchant");
}
public static String UniqueID1() // To create Unique ID
{
  String s ="";
  Random r= new Random();
  while(s.length()<10)
  {
    int r1= r.nextInt(2);
    s=s+Integer.toString(r1);
  }
  return s;
}
public String challenge()
{
String to_challenge=UniqueID1();
challenge_tosendbank=to_challenge;
return to_challenge;
}
public void tosendm_back()
{
  System.out.println("to send to the bank");
}
public boolean tocheckhash(ArrayList<byte[]> temp_2)
{
  boolean c_temp = true;
  temp_tobank=temp_2;
  String array1[]=decrypting_temp1.split("::");
  ArrayList<String> left_temp=new ArrayList<>();
  ArrayList<String> right_temp=new ArrayList<>();
  for(int i=2;i<array1.length;i++)
  {
  if(i%2==0)
  {
    left_temp.add(array1[i]);
  }
  else
  {
    right_temp.add(array1[i]);
  }
  }
  for(int i=0;i<left_temp.size();i++)
  {
    System.out.println(left_temp.get(i));
    System.out.println(right_temp.get(i));
  }
  return c_temp;
}
public String received_orderfromCustomer(byte[] received,BigInteger public_key1,BigInteger Mod)
{
String confirmation="";
byte[] decrypted1_c=((((new BigInteger(received)).modPow(public_key1,Mod))).toByteArray());
String to_checkhash=(new String(decrypted1_c));
decrypting_temp1=to_checkhash;
//System.out.println(decrypting_temp1);
//System.out.println(new String(decrypted1_c));
return "Cannot Send order to bank, Hashes not correct";
}
}
