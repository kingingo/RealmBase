package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.packets.Packet;

public class NotificationPacket extends Packet {
	
	public int objectId;
	public String message;
	public int color;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.objectId = in.readInt();
		this.message = in.readUTF();
		this.color = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.objectId);
		out.writeUTF(this.message);
		out.writeInt(this.color);
	}

	public String toString(){
		return formatToString(new String[]{
				"ObjId: "+this.objectId,
				"MSG: "+this.message,
				"Color: "+this.color
		});
	}
}
