package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import realmbase.packets.Packet;

@Getter
public class PlayerHitPacket extends Packet {
	
	public int bulletId=0;
	public int objectId=0;

	public PlayerHitPacket(){}
	
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
