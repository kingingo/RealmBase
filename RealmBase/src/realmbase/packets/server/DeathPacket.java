package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.data.BitmapData;
import realmbase.packets.Packet;


public class DeathPacket extends Packet {
	
	public String accountId;
	public int charId;
	public String killedBy;
	public int zombieId;
	public int zombieType;
	public boolean isZombie;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.accountId = in.readUTF();
		this.charId = in.readInt();
		this.killedBy = in.readUTF();
		this.zombieId = in.readInt();
		this.zombieType = in.readInt();
		this.isZombie = this.zombieId != -1;
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeUTF(this.accountId);
		out.writeInt(this.charId);
		out.writeUTF(this.killedBy);
		out.writeInt(this.zombieId);
		out.writeInt(this.zombieType);
	}

}
