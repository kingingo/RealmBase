package realmbase.packets.server;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.data.Location;
import realmbase.packets.Packet;

@Getter
@Setter
public class GoToPacket extends Packet{

	private int objectId;
	private Location pos = new Location();

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.objectId = in.readInt();
		this.pos.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		 out.writeInt(this.objectId);
		 this.pos.writeToOutput(out);
	}

	public String toString(){
		return formatToString(new String[]{
				"ObjectId: "+this.objectId,
				"Loc: "+pos.x+"/"+pos.y});
	}
}
