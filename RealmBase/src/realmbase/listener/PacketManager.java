package realmbase.listener;

import java.util.ArrayList;

import realmbase.Client;
import realmbase.data.Type;
import realmbase.packets.Packet;

public class PacketManager {
	private static ArrayList<PacketListener> listeners = new ArrayList<>();
	
	public static boolean receive(Client client, Packet packet,Type from){
		boolean cancel = false;
		for(int i = 0; i < listeners.size(); i++)
			if(listeners.get(i).onReceive(client,packet, from))cancel=true;
			
		return cancel;
	}
	
	public static boolean send(Client client, Packet packet,Type to){
		boolean cancel = false;
		for(int i = 0; i < listeners.size(); i++)
			if(listeners.get(i).onSend(client,packet, to))cancel=true;
			
		return cancel;
	}
	
	public static void addListener(PacketListener listener){
		listeners.add(listener);
	}
	
	public static void removeListener(PacketListener listener){
		listeners.remove(listener);
	}
}
