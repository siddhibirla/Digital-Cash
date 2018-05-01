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
import java.io.BufferedWriter;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import javax.crypto.*;
public class Customer
{
public static ArrayList<byte[]> list = new ArrayList<>();
public static ArrayList<String> random = new ArrayList<>();
public static ArrayList<BigInteger> Sym_Key= new ArrayList<>();
private static String Identity;
public static int random_orders;
private static ArrayList<String> order_list = new ArrayList<>();
private static ArrayList<BigInteger> rightarr = new ArrayList<>();
private static ArrayList<BigInteger> leftarr = new ArrayList<>();
private static BigInteger storekey;
public static String UniqueID() // To create Unique ID
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
int Create_Moneyorder(int orderval)
{
for(int i=0;i<random_orders+1;i++)
{
String order_req="";
String ID=UniqueID();
while(random.contains(ID))
{
  ID=UniqueID();
}
Secret_Split();
String to_add=Bit_commit(i);
order_req=ID+"::"+Integer.toString(orderval)+"::"+to_add;
order_list.add(order_req);
//System.out.println(order_req);
random.add(ID);
}
return (random_orders);
}
public void Blinding(BigInteger public_key1,BigInteger Mod) //RSA Blind signature
{
Random r = new Random();
int bitlength=1024;
for(int i=0;i<order_list.size();i++)
{
String m1=order_list.get(i);
//System.out.println(order_list.size());
byte[] message = m1.getBytes();
BigInteger k = BigInteger.probablePrime(bitlength,r);// random keys being generated to encrypt message
while(Sym_Key.contains(k))
{
k = BigInteger.probablePrime(bitlength,r);
}
Sym_Key.add(k);
byte[] encrypted=(((new BigInteger(message)).multiply(k.modPow(public_key1,Mod))).toByteArray());
//String encryptedmessage=new String(encrypted);
//System.out.println(encryptedmessage);
list.add(encrypted);
}
//System.out.println(list.size());
}
ArrayList<BigInteger> Unblindkey(int unblind_var)
{
  storekey=Sym_Key.get(unblind_var);
  String to_set="0";
  byte[] toset = to_set.getBytes();
  BigInteger b1= new BigInteger(toset);
  Sym_Key.set(unblind_var,b1);
  return Sym_Key;
}
void Secret_Split()
{
Random r= new Random();
int bitlength=1024;
byte[] message1 = Identity.getBytes();
BigInteger secretsplit = new BigInteger(message1);
BigInteger left = BigInteger.probablePrime(bitlength,r);
leftarr.add(left);
BigInteger right = secretsplit.xor(left);
rightarr.add(right);
}
String Bit_commit(int to_commit) // To commit hashvalues
{
String to_sendmo="";
for(int i=0;i<10;i++)
{
BigInteger val1=leftarr.get(to_commit);
BigInteger val2=rightarr.get(to_commit);
String left_tocommit=(new String(val1.toByteArray()));
String right_tocommit=(new String(val2.toByteArray()));
to_sendmo=to_sendmo+(left_tocommit.hashCode())+"::"+(right_tocommit.hashCode());
}
return to_sendmo;
}
void printval()
{
  for(int i=0;i<order_list.size();i++)
  {
    System.out.println(order_list.get(i));
  }
}
public ArrayList<byte[]> get_Encrypted()
{
  return list;
}
public Customer(String name,int SSN)
{
Identity=name+Integer.toString(SSN);
Random rand = new Random();
random_orders=(rand.nextInt(10)+2);
}
public Customer()
{
}
}
