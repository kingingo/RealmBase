package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.packets.Packet;

@Getter
@Setter
public class Global_NotificationPacket extends Packet {
	
	private int type;
	private String text;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.type = in.readInt();
		this.text = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.type);
		out.writeUTF(this.text);
	}

}
