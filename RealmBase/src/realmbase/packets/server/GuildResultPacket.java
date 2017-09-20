package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import realmbase.packets.Packet;

public class GuildResultPacket extends Packet {
	
	public boolean success;
	public String errorText;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.success = in.readBoolean();
		this.errorText = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeBoolean(this.success);
		out.writeUTF(this.errorText);
	}
	
	

}
