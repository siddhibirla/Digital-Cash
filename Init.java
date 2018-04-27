import java.io.*;
import java.util.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.math.BigInteger;
public class Init
{
public static void main(String[] args)
{
boolean t=true;
while(t)
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
      String name="";
      int ssn=0;
      System.out.println("Enter your Name and SSN");
      try
      {
          name=sc.nextLine();
          ssn=sc.nextInt();
          String ssn1=Integer.toString(ssn);
          if(ssn1.length()<9)
          {
          String error="Less than 9 digits in SSN";
          throw new Exception();
          }
      }
      catch(Exception e)
      {
        System.out.println("Enter valid name/SSN");
        ssn=sc.nextInt();
      }
      System.out.println("Enter the money order amount");
      int moneyorder=sc.nextInt();
      Customer c = new Customer(name,ssn);
      Random rand1= new Random();
      int loopvar = rand1.nextInt(10);
      System.out.println(loopvar);
      break;
case "Merchant":
    System.out.println("Merchant option");
    break;
case "Exit":
   t=false;
   break;
default:
  System.out.println("Enter correct option");
  break;
}
}
}
}
