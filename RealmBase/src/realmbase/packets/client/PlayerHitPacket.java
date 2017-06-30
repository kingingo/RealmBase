package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.packets.Packet;


public class PlayerHitPacket extends Packet {
	
	public int bulletId;
	public int objectId;

	public PlayerHitPacket(int bulletId, int objectId) {
		this.bulletId=bulletId;
		this.objectId=objectId;
	}
	
	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.bulletId = in.readUnsignedByte();
		this.objectId = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeByte(this.bulletId);
		out.writeInt(this.objectId);
	}

}
