package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.packets.Packet;


public class SetConditionPacket extends Packet {
	
	public int conditionEffect;
	public float conditionDuration;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.conditionEffect = in.readUnsignedByte();
		this.conditionDuration = in.readFloat();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeByte(this.conditionEffect);
		out.writeFloat(this.conditionDuration);
	}

}
