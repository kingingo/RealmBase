package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.packets.Packet;


public class EditAccountListPacket extends Packet {
	
	public int accountListId;
	public boolean add;
	public int objectId;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.accountListId = in.readInt();
		this.add = in.readBoolean();
		this.objectId = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.accountListId);
		out.writeBoolean(this.add);
		out.writeInt(this.objectId);
	}

}
