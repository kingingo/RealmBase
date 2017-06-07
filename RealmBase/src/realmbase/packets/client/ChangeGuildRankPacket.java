package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.packets.Packet;


public class ChangeGuildRankPacket extends Packet {

	public String name;
	public int guildRank;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.name = in.readUTF();
		this.guildRank = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeUTF(this.name);
		out.writeInt(this.guildRank);
	}

}
