package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import realmbase.data.Location;
import realmbase.packets.Packet;

public class ServerPlayerShootPacket extends Packet {
	
	public int bulletId;
	public int ownerId;
	public int containerType;
	public Location startingPos = new Location();
	public float angle;
	public short damage;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.bulletId = in.readUnsignedByte();
		this.ownerId = in.readInt();
		this.containerType = in.readInt();
		this.startingPos.parseFromInput(in);
		this.angle = in.readFloat();
		this.damage = in.readShort();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeByte(this.bulletId);
		out.writeInt(this.ownerId);
		out.writeInt(this.containerType);
		this.startingPos.writeToOutput(out);
		out.writeFloat(this.angle);
		out.writeShort(this.damage);
	}

}
