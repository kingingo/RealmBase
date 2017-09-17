package realmbase.packets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.reflections.Reflections;

import realmbase.RealmBase;
import realmbase.data.IData;
import realmbase.xml.GetXml;

public abstract class Packet implements IData{
	private static final HashMap<String,Class<? extends Packet>> packets = new HashMap<String, Class<? extends Packet>>();
	
	public static void loadPackets() {
		Reflections reflections = new Reflections( "realmbase.packets" );
		List<Class<? extends Packet>> moduleClasses = new ArrayList<>( reflections.getSubTypesOf( Packet.class ) );
		
		for ( Class<? extends Packet> clazz : moduleClasses ){
			if(clazz == UnknownPacket.class)continue;
			if(GetXml.packetMapName.containsKey(clazz.getSimpleName().substring(0, clazz.getSimpleName().indexOf("Packet")).toUpperCase())){
				int packetId = GetXml.packetMapName.get(clazz.getSimpleName().substring(0, clazz.getSimpleName().indexOf("Packet")).toUpperCase());
				packets.put(packetId+"", clazz);
			}
		}
	}
	
	public void parseFromInput(byte[] bytes) throws IOException {
		this.parseFromInput(new DataInputStream( new ByteArrayInputStream(bytes) ));
	}
	
	public int getId(){
		return GetXml.packetMapName.get(getPacketName());
	}
	
	public byte[] toByteArray(){
		if(this instanceof UnknownPacket)return ((UnknownPacket)this).getData();
		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream( baos );
			writeToOutput(out);
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}
	
	public String getPacketName(){
		String simpleName = this.getClass().getSimpleName();
		simpleName = simpleName.substring(0, simpleName.indexOf("Packet"));
		return simpleName.toUpperCase();
	}
	
	public String formatToString(String[] detailes){
		String format = "Packet: "+getPacketName()+"\n";
		for(String detail : detailes)format+="     "+detail+"\n";
		return format;
	}
	
	public static Packet create(int packetId, byte[] data){
		if(packets.isEmpty())loadPackets();
		Class<? extends Packet> packetClass = packets.get(packetId+"");
		
		if(packetClass!=null){
			try {
				Packet packet = packetClass.newInstance();
				packet.parseFromInput(data);
				int byteLength = packet.toByteArray().length;
				if(byteLength != data.length){
					RealmBase.println("Die Packet laenge  von "+packet.getPacketName()+" stimmt nicht "+byteLength+" != "+data.length);
				}
				return packet;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return new UnknownPacket(packetId,data);
	}
}
