package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.packets.Packet;

public class FilePacket extends Packet {

	public String name;
	public byte[] bytes = new byte[0];

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.name = in.readUTF();
		this.bytes = new byte[in.readInt()];
		in.readFully(this.bytes);
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeUTF(this.name);
		out.writeInt(this.bytes.length);
		out.write(this.bytes);
	}

}
