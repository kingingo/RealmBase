package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.data.Location;
import realmbase.packets.Packet;


public class PlayerShootPacket extends Packet {
	
	public int time;
	public int bulletId;
	public int containerType;
	public Location startingPos = new Location();
	public float angle;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.time = in.readInt();
		this.bulletId = in.readUnsignedByte();
		this.containerType = in.readUnsignedShort();
		this.startingPos.parseFromInput(in);
		this.angle = in.readFloat();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.time);
		out.writeByte(this.bulletId);
		out.writeShort(this.containerType);
		this.startingPos.writeToOutput(out);
		out.writeFloat(this.angle);
	}

}
