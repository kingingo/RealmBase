package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.packets.Packet;

@Getter
@Setter
public class AccountListPacket extends Packet {
	
	private int accountListId;
	private String[] accountIds = new String[0];
	private int lockAction = -1;
	
	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.accountListId = in.readInt();
		this.accountIds = new String[in.readShort()];
		for (int i = 0; i < accountIds.length; i++) {
			accountIds[i] = in.readUTF();
		}
		lockAction = in.readInt();
	}
	
	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(accountListId);
		out.writeShort(accountIds.length);
		for (String accountId : accountIds) {
			out.writeUTF(accountId);
		}
		out.writeInt(lockAction);
	}
	
}
