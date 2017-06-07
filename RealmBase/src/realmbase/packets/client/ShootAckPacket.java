package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.packets.Packet;


public class ShootAckPacket extends Packet {
	
	public int time;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.time = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.time);
	}

}
