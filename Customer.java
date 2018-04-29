import java.io.*;
import java.util.*;
import java.net.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.math.BigInteger;
public class Customer
{
public static ArrayList<Integer> list = new ArrayList<>();
public static ArrayList<String> random = new ArrayList<>();
public static ArrayList<BigInteger> Sym_Key= new ArrayList<>();
private String Identity;
public int random_orders;
ArrayList<String> order_list = new ArrayList<>();
String UniqueID() // To create Unique ID
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
System.out.println(order_req);
random.add(ID);
}
}
void Blinding() //RSA Blind signature
{
Random r = new Random();
int bitlength=1024;
for(int i=0;i<order_list.size();i++)
{
String message=order_list.get(i);
BigInteger k = BigInteger.probablePrime(bitlength,r);
Sym_Key.add(k);
//byte[] encrypted=(((new BigInteger(message)).multiply(k.modPow(e,n))).toByteArray());
//String encryptedmessage=new String(encrypted);
}
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
