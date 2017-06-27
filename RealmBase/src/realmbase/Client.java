package realmbase;

import static realmbase.Client.bufferLength;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;
import lombok.Getter;
import lombok.Setter;
import realmbase.data.Callback;
import realmbase.data.Type;
import realmbase.encryption.RC4;
import realmbase.listener.ObjectListener;
import realmbase.listener.PacketManager;
import realmbase.packets.Packet;
import realmbase.packets.client.HelloPacket;

public class Client {
	protected static final int bufferLength = 65536 * 10;
	
	@Getter
	@Setter
	protected Socket remoteSocket;
	@Getter
	@Setter
	protected long remoteNoDataTime = System.currentTimeMillis();
	@Getter
	@Setter
	protected long connectTime = System.currentTimeMillis();
	@Setter
	@Getter
	protected int clientId = 0;
	@Setter
	@Getter
	protected String name = "";
	@Setter
	protected RC4 remoteRecvRC4;
	@Setter
	protected RC4 remoteSendRC4;
	protected byte[] remoteBuffer = new byte[bufferLength];
	protected int remoteBufferIndex = 0;

	public boolean connect(InetSocketAddress socketAddress, Callback<Client> callback) {
		if (remoteSocket != null) {
			return false;
		}
		
		Client client = this;
		new Thread(new Runnable() {

			@Override
			public void run() {
				client.setRemoteRecvRC4(new RC4(Parameter.cipherIn)); 
				client.setRemoteSendRC4(new RC4(Parameter.cipherOut));
				
				ObjectListener.clear(client);
				Socket remoteSocket;
				if (Parameter.proxy) {
					RealmBase.println("PROXY");
					SocketAddress proxyAddress = new InetSocketAddress(Parameter.proxyHost, Parameter.proxyPort);
					Proxy proxy = new Proxy(Proxy.Type.SOCKS, proxyAddress);
					Authenticator.setDefault(Parameter.proxyAuth);
					remoteSocket = new Socket(proxy);
				} else {
					RealmBase.println("NO PROXY");
					remoteSocket = new Socket();
				}
				
				try {
					SocketAddress remoteAddress;
					if (InetAddress.getByName(socketAddress.getHostString()).isLoopbackAddress()) {
						RealmBase.println("1");
						remoteAddress = new InetSocketAddress(Parameter.remoteHost.getHostString(), socketAddress.getPort() == -1 ? Parameter.remoteHost.getPort() : socketAddress.getPort());
					} else {
						RealmBase.println("2");
						remoteAddress = new InetSocketAddress(socketAddress.getHostString(), socketAddress.getPort() == -1 ? Parameter.remoteHost.getPort() : socketAddress.getPort());
					}
					
					RealmBase.println("remoteSocket: " + (remoteSocket==null) );
					RealmBase.println("remoteAddress: " + (remoteAddress==null) );
					RealmBase.println("Connected wtih "+socketAddress.getHostString()+":"+socketAddress.getPort());
					
					remoteSocket.connect(remoteAddress, 10000);
					client.remoteNoDataTime = System.currentTimeMillis();
					client.connectTime = System.currentTimeMillis();
					client.remoteSocket = remoteSocket;
					callback.call(client, null);
				} catch (IOException e) {
					callback.call(client, e);
				}
			}
		
		}).start();
		return true;
	}
	
	public void sendPacketToServer(Packet packet){
		if(this.remoteSocket!=null && this.remoteSocket.isConnected()){
			boolean cancel = PacketManager.send(this, packet, Type.SERVER);
			
			if(!cancel){
				byte[] packetBytes = packet.toByteArray();
				try {
					this.remoteSendRC4.cipher(packetBytes);
					int packetLength = packetBytes.length + 5;
					DataOutputStream out = new DataOutputStream(this.remoteSocket.getOutputStream());
					out.writeInt(packetLength);
					out.writeByte(packet.getId());
					out.write(packetBytes);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			RealmBase.println("Packet could not send "+packet.getPacketName());
		}
	}
	
	public void sendPacketToServer(int packetId, byte[] packetBytes){
		sendPacketToServer(Packet.create(packetId, packetBytes));
	}
	
	public void disconnect() {
		if (this.remoteSocket != null) {
			try {
				this.remoteSocket.close();
				
				RealmBase.println("verbindung geschlossen "+this.clientId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.remoteSocket = null;
		}
	}
}
