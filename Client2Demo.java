import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

class Client2{
	public SocketChannel Socket_Channel;
	public static int PORT_NO=2400;
	public ByteBuffer buffer=ByteBuffer.allocate(400);
	public BufferedReader read=new BufferedReader(new InputStreamReader(System.in));
	String msg="Hai";
	void start(){
		try {
			Socket_Channel=SocketChannel.open();
			Socket_Channel.connect(new InetSocketAddress("localhost",PORT_NO));
			System.out.println("Client>> Connected to Server");
			do{
				receive();
				if(msg.equals("done"))
					break;
				send();
				
			}while(msg!="done");
			
			
		} catch (IOException e) {
			 
			e.printStackTrace();
		}finally{
			try {
				Socket_Channel.close();
				read.close();
			} catch (IOException e) {
				 
				e.printStackTrace();
			}
		}
		
	}
	void send(){
		try {
			System.out.print("Client2>> Enter Text to send To Client1>> ");
			
			msg=read.readLine();
			System.out.print("Client2>> "+msg);
			buffer.put(msg.getBytes());
			buffer.flip();
			Socket_Channel.write(buffer);
			buffer.flip();
			buffer.clear();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	void receive(){
		try {
			Socket_Channel.read(buffer);
			buffer.flip();
			System.out.print("\nClient1>> ");
			for(int i=0;i<buffer.limit();i++){
			System.out.print((char)buffer.get(i));
			}
			System.out.println();
			buffer.clear();
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
	}
}
public class Client2Demo {

	
	public static void main(String[] args) {
		Client2 cl=new Client2();
		cl.start();
	}

}


