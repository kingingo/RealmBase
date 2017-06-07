package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import realmbase.packets.Packet;

public class ClientStatPacket extends Packet {
	
	public String name;
	public int value;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.name = in.readUTF();
		this.value = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeUTF(this.name);
		out.writeInt(this.value);
	}

}
