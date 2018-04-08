import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public class Init
{
public static void main(String[] args)
{
System.out.println("Select Option :");
System.out.println("1. Customer");
System.out.println("2. Merchant");
System.out.println("3.Exit");
Scanner sc=new Scanner(System.in);
String option=sc.nextLine();
switch(option)
{
case "Customer":
      System.out.println("Enter your Name and SSN");
      try
      {
         String name=sc.nextLine();
         int ssn=sc.nextInt();
      }
      catch(Exception e)
      {
        System.out.println("Enter valid name/SSN");
      }
      System.out.println("Enter the money order amount");
      int moneyorder=sc.nextInt();
      Customer c = new Customer();
      Random rand1= new Random();
      int loopvar = rand1.nextInt(10);
      System.out.println(loopvar);
      break;
case "Merchant":
    System.out.println("Merchant option");
    break;
case "Exit":
   break;
default:
  System.out.println("Enter correct option");
  break;
}

//for(int i=0;i<loopvar;i++)
//{
//c.Random_generator();
//}
}
}
