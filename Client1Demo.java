import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

class Client1{
	public ServerSocketChannel S_Socket_Channel;
	public SocketChannel Socket_Channel;
	public static int PORT_NO=2400;
	public ByteBuffer buffer=ByteBuffer.allocate(400);
	public BufferedReader read=new BufferedReader(new InputStreamReader(System.in));
	String msg=null;
	void Start(){
		try {
			S_Socket_Channel=ServerSocketChannel.open();
			S_Socket_Channel.socket().bind(new InetSocketAddress(PORT_NO));
			System.out.println("Waiting for Client2 to connect");
			
					
			    
			    Socket_Channel=S_Socket_Channel.accept();
			    System.out.println("Client::"+Socket_Channel.getLocalAddress().toString());
			
			
			
			do{
				send();
				if(msg.equals("done"))
					break;
				receive();
				
			}while(msg!="done");
			Socket_Channel.close();
			S_Socket_Channel.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				Socket_Channel.close();
				read.close();
			} catch (Exception e) {
	
				e.printStackTrace();
			}
		}
		
	}
	void send(){
		try {
			System.out.print("Client1>> Enter Text to send To Client2>> ");
			msg=read.readLine();
			System.out.print("Client1>> "+msg);
			buffer.put(msg.getBytes());
			buffer.flip();
			Socket_Channel.write(buffer);
			buffer.flip();
			buffer.clear();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	void receive(){
		try {
			Socket_Channel.read(buffer);
			buffer.flip();
			System.out.print("\nClient>> ");
			for(int i=0;i<buffer.limit();i++){
			System.out.print((char)buffer.get(i));
			}
			System.out.println();
			buffer.clear();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
public class Client1Demo {

	public static void main(String[] args) {
	Client1 ser=new Client1();
	
	    ser.Start();
	
		
	}

}
