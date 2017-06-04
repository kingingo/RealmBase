package realmbase.packets.client;

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
public class AoeAckPacket extends Packet{

	private int time = 0;
	private Location position = new Location();

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.time = in.readInt();
		this.position.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		 out.writeInt(time);
		 this.position.writeToOutput(out);
	}

	public String toString(){
		return formatToString(new String[]{
				"Time: "+time,
				"Position: "+position.x+"/"+position.y});
	}
}
