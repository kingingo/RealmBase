package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import realmbase.packets.Packet;

public class DamagePacket extends Packet {
	
	public int targetId;
	public int[] effects = new int[0];
	public int damageAmount;
	public boolean kill;
	public int bulletId;
	public int objectId;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.targetId = in.readInt();
		this.effects = new int[in.readUnsignedByte()];
		for (int i = 0; i < this.effects.length; i++) {
			this.effects[i] = in.readUnsignedByte();
		}
		this.damageAmount = in.readUnsignedShort();
		this.kill = in.readBoolean();
		this.bulletId = in.readUnsignedByte();
		this.objectId = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.targetId);
		out.writeByte(this.effects.length);
		for (int effect: this.effects) {
			out.writeByte(effect);
		}
		out.writeShort(this.damageAmount);
		out.writeBoolean(this.kill);
		out.writeByte(this.bulletId);
		out.writeInt(this.objectId);
	}

}
