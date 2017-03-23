import java.awt.Frame;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JOptionPane;
 
public class Client
{
 
    private static Socket socket;
    private static int speed;
 
         
    public Client(String ip, String po, int sp){
    	try{ 
    		int port = Integer.parseInt(po);
    		String address = ip;
    		//InetAddress address= InetAddress.getByName("localhost");
    		socket = new Socket(address, port);
    		speed=sp;
    		sendConfigMessage(speed);
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		System.exit(0);
    		    		
    	}
    	}
    
    public static void sendMessage(String number){
    	try{
    		OutputStream os = socket.getOutputStream();
    		OutputStreamWriter osw = new OutputStreamWriter(os);
    		BufferedWriter bw = new BufferedWriter(osw);
    		
    		 String sendMessage = number + "\n" ;
             bw.write(sendMessage);
             bw.flush();
             
             System.out.println("Message sent to the server : "+sendMessage);
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    } //END OF sendMessage function
    
    public static void sendConfigMessage(int config){
    	
    	try{
    		
    		OutputStream os = socket.getOutputStream();
    		OutputStreamWriter osw = new OutputStreamWriter(os);
    		BufferedWriter bw = new BufferedWriter(osw);
    		
    		String speed;
    		if(config<1000){
    			speed="0"+ config;
    		}else if (config>9999){
    			speed="9999";
    		}else{
    			speed=Integer.toString(config);
    		}
    		String message= "c" + speed;
    		
    		//sendMessage(message);		
    		
    		 String sendMessage ="111"+ message + "\n" ;
             bw.write(sendMessage);
             bw.flush();
    		
    		
    			
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }	//END OF sendConfigMessage function
    
    
public static void sendChangeSpeedMessage(int config){
    	
    	try{
    		
    		OutputStream os = socket.getOutputStream();
    		OutputStreamWriter osw = new OutputStreamWriter(os);
    		BufferedWriter bw = new BufferedWriter(osw);
    		
    		String speed;
    		if(config<1000){
    			speed="0"+ config;
    		}else if (config>9999){
    			speed="9999";
    		}else{
    			speed=Integer.toString(config);
    		}
    		String message= "c" + speed;
    		
    		//sendMessage(message);		
    		
    		 String sendMessage =message + "\n" ;
             bw.write(sendMessage);
             bw.flush();
    		
    		
    			
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    }	//END OF sendConfigMessage function
    
    public static void closeSocket(){
    	  
              //Closing the socket
              try
              {
                  socket.close();
              }
              catch(Exception e)
              {
                  e.printStackTrace();
              }
          
    }
}// END OF Client CLASS