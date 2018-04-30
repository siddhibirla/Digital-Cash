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
import javax.crypto.*;
public class Customer
{
public static ArrayList<Integer> list = new ArrayList<>();
public static ArrayList<String> random = new ArrayList<>();
public static ArrayList<BigInteger> Sym_Key= new ArrayList<>();
private String Identity;
public int random_orders;
private ArrayList<String> order_list = new ArrayList<>();
public String UniqueID() // To create Unique ID
{
  String s ="";
  Random r= new Random();
  while(s.length()<10)
  {
    int r1= r.nextInt(9);
    s=s+Integer.toString(r1);
  }
  return s;
}
void Create_Moneyorder(int orderval)
{
for(int i=0;i<random_orders;i++)
{
String order_req="";
String ID=UniqueID();
while(random.contains(ID))
{
  ID=UniqueID();
}
order_req=ID+" "+Integer.toString(orderval);
order_list.add(order_req);
//System.out.println(order_req);
random.add(ID);
}
}
public void Blinding(BigInteger public_key1,BigInteger Mod) //RSA Blind signature
{
Random r = new Random();
int bitlength=1024;
//for(int i=0;i<order_list.size();i++)
//{
String m1=order_list.get(0);
//System.out.println(order_list.size());
//String m1="hello";
byte[] message = m1.getBytes();
BigInteger k = BigInteger.probablePrime(bitlength,r);
Sym_Key.add(k);
byte[] encrypted=(((new BigInteger(message)).multiply(k.modPow(public_key1,Mod))).toByteArray());
String encryptedmessage=new String(encrypted);
System.out.println(encryptedmessage);
//}
}
void Unblindkey()
{
  System.out.println("unblindkey");
}
void Secret_Split()
{
System.out.println("Secret_Split");
}
void Bit_commit()
{
  System.out.println("Bitcommit");
}
void printval()
{
  for(int x:list)
  {
    System.out.println(x);
  }
}
public Customer(String name,int SSN)
{
Identity=name+Integer.toString(SSN);
Random rand = new Random();
random_orders=rand.nextInt(10);
}
}
