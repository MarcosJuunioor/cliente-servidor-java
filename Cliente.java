import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Cliente{ 
	public static void main(String[] arg){ 
		String palavra;
	    int c, total = (int)(10*Math.random());
	    Socket s = null;
	    
	    try{ 
	    	System.out.println("Conectando...");
	    	s = new Socket("192.168.89.149",6789);
	    	DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			System.out.println("Conectado. Enviando "+total+" números...");
			
			for(c=0; c<total; c++){
				//       numero = 100.0*Math.random();
				Scanner sc1 = new Scanner(System.in); 
				palavra =  sc1.next();
				System.out.println("Enviando "+palavra);
				palavra = codificar(palavra);
				out.writeUTF(palavra);
			}
			out.flush();
			out.writeUTF("fim");
			System.out.println("Frase = "+in.readUTF());
	    }
	    catch(Exception e){ 
	    	System.out.println("Erro: "+e.getMessage()); 
	    }
	    finally{ 
	    	try{
	    		if(s!=null) s.close();
	    	}catch(Exception e2){}    
    	}
	    System.out.println("Conexão encerrada");
	    try{
	    	System.in.read();
    	}catch(Exception e3){}
	}
 
	public static String codificar(String mens){
		int chave = 3;
		char ascii;
		char x, y;
		String retorno = "";
	
		for (int i = 0; i < mens.length(); i++) {
	         	//Tratamento Letras minusculas  
	            if (mens.charAt(i) >= 97 && mens.charAt(i) <= 122) {//letrans minusculas de acordo com a tabela ASCII
	                if ((int) (mens.charAt(i) + chave) > 122) {
	                    x = (char) (mens.charAt(i) + chave);
	                    y = (char) (x - 122);
	                    ascii = (char) (96 + y);
	                    retorno += ascii + " ";
	                } else {
	                    ascii = (char) (mens.charAt(i) + chave);
	                    retorno += ascii + " ";
	                }
	            }            
	
	            //Tratamento Letras maiusculas
	            if (mens.charAt(i) >= 65 && mens.charAt(i) <= 90) {
	                if (mens.charAt(i) + chave > 90) {
	                    x = (char) (mens.charAt(i) + chave);
	                    y = (char) (x - 90);
	                    ascii = (char) (64 + y);
	                    retorno += ascii + " ";
	                } else {
	                    ascii = (char) (mens.charAt(i) + chave);
	                    retorno += ascii + " ";
	                }
	            }
		}
	
		return retorno;
	}

	public static String decodificar(int chave, String mens) {
            char ascii;
            char x, y;
            String retorno = "";

            for (int i = 0; i < mens.length(); i++) {
            	//Tratamento Letras minusculas  
                if (mens.charAt(i) >= 97 && mens.charAt(i) <= 122) {//letrans minusculas de acordo com a tabela ASCII
                    if ((int) (mens.charAt(i) - chave) < 97) {
                    	x = (char) (mens.charAt(i) - chave);
                    	ascii = (char)(x + 26);
                        retorno += ascii + " ";
                    } else {
                        ascii = (char) (mens.charAt(i) - chave);
                        retorno += ascii + " ";
                    }
                }            

                //Tratamento Letras mausculas
                if (mens.charAt(i) >= 65 && mens.charAt(i) <= 90) {
                    if (mens.charAt(i) - chave < 65) {
                        x = (char) (mens.charAt(i) - chave);
                        ascii = (char)(x+26);
                        retorno += ascii + " ";
                    } else {
                        ascii = (char) (mens.charAt(i) - chave);
                        retorno += ascii + " ";
                    }
                }            
	      }
            return retorno;
	}
}
