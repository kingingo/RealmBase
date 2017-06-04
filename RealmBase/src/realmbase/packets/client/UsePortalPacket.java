package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.packets.Packet;

@Getter
@Setter
public class UsePortalPacket extends Packet{

	private int objectId;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.objectId = in.readInt();
	}

	@Override	
	public void writeToOutput(DataOutputStream out) throws IOException {
		 out.writeInt(objectId);
	}

	public String toString(){
		return formatToString(new String[]{
				"ObjectId: "+this.objectId});
	}
}
