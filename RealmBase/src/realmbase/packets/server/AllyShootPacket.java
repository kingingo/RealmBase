package realmbase.packets.server;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.packets.Packet;

public class AllyShootPacket extends Packet {
	
	public int bulletId;
	public int ownerId;
	public short containerType;
	public float angle;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.bulletId = in.readUnsignedByte();
		this.ownerId = in.readInt();
		this.containerType = in.readShort();
		this.angle = in.readFloat();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeByte(this.bulletId);
		out.writeInt(this.ownerId);
		out.writeShort(this.containerType);
		out.writeFloat(this.angle);
	}

}
