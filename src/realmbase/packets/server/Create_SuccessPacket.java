package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.packets.Packet;

@Getter
@Setter
public class Create_SuccessPacket extends Packet {
	
	private int objectId;
	private int charId;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.objectId = in.readInt();
		this.charId = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.objectId);
		out.writeInt(this.charId);
	}

	public String toString(){
		return formatToString(new String[]{
				"ObjId: "+this.objectId,
				"CharId: "+this.charId
		});
	}
}
