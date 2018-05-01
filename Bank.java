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
public class Bank
{
public static int randomord;
private static BigInteger privatekey;
public Bank(int random_toselect, BigInteger pvkey)
{
randomord=random_toselect;
privatekey=pvkey;
}
public int to_selectorder()
{
  int to_send=0;
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
public void unblindingmoney(ArrayList<BigInteger> a1, ArrayList<byte[]> a2,BigInteger e1, BigInteger n1)
{
for(int i=0;i<a1.size();i++)
{
  BigInteger inverse=(a1.get(i)).modPow(e1,n1);
  byte[] decrypted = (((new BigInteger(a2.get(i))).divide(inverse)).toByteArray());
  System.out.println("-------------------");
  System.out.println(new String(decrypted));
  System.out.println("-------------------");

}
}
public void Doublespendcheck()
{
  System.out.println("Check");
}
}
