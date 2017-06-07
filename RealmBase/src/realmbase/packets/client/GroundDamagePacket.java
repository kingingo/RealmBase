package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.data.Location;
import realmbase.packets.Packet;

public class GroundDamagePacket extends Packet {
	
	public int time;
	public Location position = new Location();

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.time = in.readInt();
		this.position.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.time);
		this.position.writeToOutput(out);
	}

}
