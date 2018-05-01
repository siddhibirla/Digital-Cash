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
public void challenge()
{
String to_challenge=UniqueID1();
}
}
