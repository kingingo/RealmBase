package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import realmbase.packets.Packet;

public class InvResultPacket extends Packet {
	
	public int result;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.result = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.result);
	}

}
