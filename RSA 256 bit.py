import sys
from PrimeGenerator import PrimeGenerator
import BitVector
class security:
    def gcd(self,a,b):
        self.a=a
        self.b=b
        if(b==0):
          return a
        else:
         return self.gcd(b,a%b)
    def encrypt(self,file,wfile):
        print("Encrypting")
        list=[]
        e = 65537
        list1=[]
        self.file=file
        self.wfile=wfile
        print(file)
        f = open(file,'rb')#reading from message file
        o = open(wfile,'w+')
        w3 = open("MyFile.txt",'w+')
        while True:
              generator = PrimeGenerator( bits = 128, debug = 0, emod = 65537)
              prime = generator.findPrime()
              generator1 = PrimeGenerator( bits = 128, debug = 0, emod = 65537)
              prime1 = generator1.findPrime()
              prime3=prime>>126
              prime4=prime1>>126
              if(prime!=prime1): #to make sure two primes are not the same
                 gcd1=self.gcd(prime-1,e)
                 gcd2=self.gcd(prime1-1,e)
                 if((gcd1==1) and (gcd2==1)):# to check if gcd((prime-1),e)=1
                     if((prime3==3) and (prime4==3)): #checking if left two bits are 1
                        break
        n=prime*prime1
        w3.write("{} ".format(str(prime)))
        w3.write(str(prime1))
        while True:
            buf=f.read(16)
            if buf:
                if(len(buf)<16):
                  while(len(buf)<16):
                       buf=buf+'\n'
                list.append(buf)
            else:
               break
        f.close()
        for x in range(0,len(list)):
            t=list[x]
            list[x]=t.zfill(32)
        for u in range(0,(len(list))):
            print(list[u])
        t=''.join(list)
        print(t)
        r=''
        for u in range(0,(len(list))):
            y1=list[u]
            list3=[]
            for r2 in range(0,len(list[u])):
                  list3.append((str(pow(ord(y1[r2]),e,n)))+' ')
            t5=' '.join(list3)
            o.write(t5)
    def decrypt(self,file,wfile):
        self.file=file
        self.wfile=wfile
        print(file)
        f1=open(file,'rb')
        o1=open(wfile,'w')
        o3=open("MyFile.txt",'r+')
        lines=''
        list2=[]
        list3=[]
        list5=[]
        lines=f1.readlines()
        lines1=o3.readlines()
        print("Decrypting")
        for line1 in lines1:
            list5=line1.strip().split()
        print(list5)
        prime3=int(list5[0])
        prime4=int(list5[1])
        m5=(prime3-1)*(prime4-1)
        m6=prime3*prime4
        bv_modulus = BitVector.BitVector( intVal= m5 )
        bv = BitVector.BitVector( intVal= 65537 )
        result =bv.multiplicative_inverse( bv_modulus ) #calculating the value of the private key
        result=str(result)
        result=int(result,2) #private key
        for line in lines:
            list3=line.strip().split()
        for t2 in range(0,len(list3)):
            list2.append(chr(pow(int(list3[t2]),result,m6)))
        for t5 in range(0,len(list2)):
            list2[t5]=list2[t5].lstrip("0")
        str4=''.join(list2)
        o1.write(str4)
def main():
        sys.argv
        condition = sys.argv[1]
        file=sys.argv[2]
        wfile=sys.argv[3]
        if condition=='-e':
           x=security()
           x.encrypt(file,wfile)
        elif condition=='-d':
           y=security()
           y.decrypt(file,wfile)
        else:
           print("Enter the right command -e or -d ")#error in the command
if __name__=='__main__':main()
