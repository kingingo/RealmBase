package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.packets.Packet;


public class BuyPacket extends Packet {
	
	public int objectId;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.objectId = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.objectId);
	}

}
