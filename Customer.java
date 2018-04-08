import java.io.*;
import java.util.*;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
public class Customer
{
public static ArrayList<Integer> list = new ArrayList<>();
ArrayList<Integer> list1 = new ArrayList<>();
void Random_generator()
{
Random rand = new Random();
int randomint = rand.nextInt(65535);
list.add(randomint);
}
void Encrypt() // Blind signature
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
void printval()
{
  for(int x:list)
  {
    System.out.println(x);
  }
}
public static void main(String[] args)
{
System.out.println("digital cash");
System.out.println("Enter the amount of the Digital cash order");
Random rand1 = new Random();
int random2= rand1.nextInt(10);
Customer call = new Customer();
for(int i=0;i<random2;i++)
{
  call.Random_generator();
}
call.printval();
call.Encrypt();
}
}
