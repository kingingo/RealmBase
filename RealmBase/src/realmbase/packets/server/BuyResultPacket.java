package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import realmbase.packets.Packet;

public class BuyResultPacket extends Packet {
	
	public int result;
	public String resultString;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.result = in.readInt();
		this.resultString = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.result);
		out.writeUTF(this.resultString);
	}

}
