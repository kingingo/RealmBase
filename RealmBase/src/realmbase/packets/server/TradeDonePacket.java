package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import realmbase.packets.Packet;

public class TradeDonePacket extends Packet {
	
	public int code;
	public String description;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.code = in.readInt();
		this.description = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.code);
		out.writeUTF(this.description);
	}

}
