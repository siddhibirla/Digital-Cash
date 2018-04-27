import java.io.*;
import java.util.*;
import java.net.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.math.BigInteger;
public class Customer
{
public static ArrayList<Integer> list = new ArrayList<>();
private String Identity;
ArrayList<Integer> list1 = new ArrayList<>();
void Random_generator()
{
Random rand = new Random();
int randomint = rand.nextInt(65535);
list.add(randomint);
}
void Blinding() // Blind signature
{
  for(int v:list)
  {
    Random rand2=new Random();
    int randomint2=rand2.nextInt(65535);
    list1.add(randomint2);
  }
  for(int v1:list1)
  {
    System.out.println(v1);
  }
}
void Unblindkey()
{
  System.out.println("unblindkey");
}
void Secret_Split();
{
  System.out.println("Secret split");
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
System.out.println(Identity);
}
}
