package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.data.Location;
import realmbase.data.LocationRecord;
import realmbase.data.Status;
import realmbase.packets.Packet;

@Getter
@Setter
public class MovePacket extends Packet {
	
	private int tickId;
	private int time;
	private Location newPosition = new Location();
	private LocationRecord[] records = new LocationRecord[0];

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.tickId = in.readInt();
		this.time = in.readInt();
		this.newPosition.parseFromInput(in);
		this.records = new LocationRecord[in.readShort()];
		for (int i = 0; i < this.records.length; i++) {
			LocationRecord record = new LocationRecord();
			record.parseFromInput(in);
			this.records[i] = record;
		}
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.tickId);
		out.writeInt(this.time);
		this.newPosition.writeToOutput(out);
		out.writeShort(this.records.length);
		for (LocationRecord record: this.records) {
			record.writeToOutput(out);
		}
	}
	

	public String recordsToString(){
		String format = "";
		for(LocationRecord r : records){
			format+="    Time: "+r.time+" Pos:"+r.x+"/"+r.y+"\n";
		}
		return format;
	}
	
	public String toString(){
		return formatToString(new String[]{
				"TickId: "+this.tickId,
				"Time: "+this.time,
				"Loc: "+newPosition.x+"/"+newPosition.y,
				"LocationRecord Length: "+records.length,
				recordsToString()
		});
	}

}
