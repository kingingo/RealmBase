package realmbase.packets.server;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import realmbase.data.Status;
import realmbase.packets.Packet;

@Getter
@Setter
public class NewTickPacket extends Packet {
	
	private int tickId;
	private int tickTime;
	private Status[] statuses = new Status[0];

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.tickId = in.readInt();
		this.tickTime = in.readInt();
		this.statuses = new Status[in.readShort()];
		for (int i = 0; i < this.statuses.length; i++) {
			Status status = new Status();
			status.parseFromInput(in);
			this.statuses[i] = status;
		}
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.tickId);
		out.writeInt(this.tickTime);
		out.writeShort(this.statuses.length);
		for (Status status: this.statuses) {
			status.writeToOutput(out);
		}
	}
	
	public String statusesToString(){
		String format = "";
		for(Status s : statuses){
			format+="    ID: "+s.getObjectId()+" Pos:"+s.getPosition().x+"/"+s.getPosition().y+" Length:"+s.getData().length+"\n";
		}
		return format;
	}
	
	public String toString(){
		return formatToString(new String[]{
				"TickId: "+this.tickId,
				"TickTime: "+this.tickTime,
				"Status Length: "+statuses.length,
				statusesToString()
		});
	}
}
