package Servidor;

import java.io.*;
import java.net.*;

public class ServidorJogoDaForca{
	ServerSocket providerSocket;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;

	void run()
	{
		try{
			providerSocket = new ServerSocket(2004, 10);
			connection = providerSocket.accept();
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());
			do{
				try{
					message = (String)in.readObject();
					if (message.equals()){
						sendMessage();
						break;
					} else if(message.equals()){
						break;
					}
				}
				catch(Exception ex){
					sendMessage("NOK"+ ex);
					break;
				}
			}while(true);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			try{
				in.close();
				out.close();
				providerSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		ServidorJogoDaForca server = new ServidorJogoDaForca();
		while(true){
			server.run();
		}
	}
}
