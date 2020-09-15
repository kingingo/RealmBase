package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.packets.Packet;


public class OtherHitPacket extends Packet {
	
	public int time;
	public int bulletId;
	public int objectId;
	public int targetId;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.time = in.readInt();
		this.bulletId = in.readUnsignedByte();
		this.objectId = in.readInt();
		this.targetId = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.time);
		out.writeByte(this.bulletId);
		out.writeInt(this.objectId);
		out.writeInt(this.targetId);
	}

}
