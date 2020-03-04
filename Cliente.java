import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Cliente
{ public static void main(String[] arg)
  { double numero;
    int c, total = (int)(10*Math.random());
    Socket s = null;
    try
    { System.out.println("Conectando...");
      s = new Socket("192.168.89.149",6789);
      DataInputStream in = new DataInputStream(s.getInputStream());
      DataOutputStream out = new DataOutputStream(s.getOutputStream());
      System.out.println("Conectado. Enviando "+total+" números...");
      for(c=0; c<total; c++){
//       numero = 100.0*Math.random();
        Scanner sc1 = new Scanner(System.in); 
        numero =  sc1.nextDouble();
        System.out.println("Enviando "+numero);
        out.writeDouble(numero*3.14);
      }
      out.flush();
      out.writeDouble(-1.0);
      System.out.println("Somatório = "+(in.readDouble()/3.14));
      }
      catch(Exception e)
      { System.out.println("Erro: "+e.getMessage()); }
      finally
      { try{if(s!=null) s.close();}catch(Exception e2){}    }
      System.out.println("Conexão encerrada");
      try{System.in.read();}catch(Exception e3){}
  }
}