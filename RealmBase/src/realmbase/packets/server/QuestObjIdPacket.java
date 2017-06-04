package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.packets.Packet;

@Getter
@Setter
public class QuestObjIdPacket extends Packet {
	
	private int objectId;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.objectId = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.objectId);
	}

	public String toString(){
		return formatToString(new String[]{"ObjId: "+this.objectId});
	}
}
