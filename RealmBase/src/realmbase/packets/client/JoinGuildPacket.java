package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.packets.Packet;


public class JoinGuildPacket extends Packet {
	
	public String guildName;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.guildName = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeUTF(this.guildName);
	}

}
