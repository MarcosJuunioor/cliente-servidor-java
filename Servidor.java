import java.io.*; 
import java.net.*; 
import java.util.*; 
public class Servidor{ 
	public static void main(String[] args ) { 
		ServerSocket s;
	    try{ 
	    	s = new ServerSocket(6789);
	    	System.out.println("Servidor iniciado na porta 6789");
	      
	    	while(true){ 
	    		Socket cliente = s.accept();
	    		System.out.println("Conexão estabelecida "+
			                   "("+cliente+")");
	    		DataInputStream in = new DataInputStream(cliente.getInputStream());
	    		DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
	    		String palavra, frase = "";
	    		palavra = in.readUTF();
			  
	    		while(!palavra.equals("fim")){ 
	    			palavra = decodificar(3, palavra);
	    			frase += palavra;
	    			System.out.println("Palavra recebida: "+palavra+
			                     " \t - parcial = "+frase);
	    			palavra = in.readUTF();
	    		}
	    		frase = codificar(frase);
	    		out.writeUTF(frase);
	    		cliente.close();
	    		System.out.println("Conexão encerrada.");
	    	}
	    }
	    catch(Exception e){ 
	    	System.out.println("Erro: "+e.getMessage());
	    }
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