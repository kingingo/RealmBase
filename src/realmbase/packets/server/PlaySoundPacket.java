package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import realmbase.packets.Packet;

public class PlaySoundPacket extends Packet {
	
	public int ownerId;
	public int soundId;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.ownerId = in.readInt();
		this.soundId = in.readUnsignedByte();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.ownerId);
		out.writeByte(this.soundId);
	}

}
