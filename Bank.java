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
   to_send = to_select.nextInt(randomord-1); // Limit values to total number of orders
  }
  else
  {
    System.out.println("No money order to process");
  }
  return to_send;
}
public void unblindingmoney()
{

}
public void Doublespendcheck()
{
  System.out.println("Check");
}
}
