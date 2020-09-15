package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.packets.Packet;


public class ReskinPacket extends Packet {
	
	public int skinID;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.skinID = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.skinID);
	}

}
