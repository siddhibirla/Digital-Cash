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
  boolean c = true;
  temp_tobank=temp_2;
  System.out.println("to check hash");
  return c;
}
public void receive_orderfromCustomer()
{
  System.out.println("received order from customer");
}
}
