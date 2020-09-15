package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.RealmBase;
import realmbase.packets.Packet;


public class RequestTradePacket extends Packet {
	
	public String name;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.name = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeUTF(this.name);
	}
	
	public String toString(){
		return formatToString(new String[]{
				"Name: "+name
		});
	}
}
