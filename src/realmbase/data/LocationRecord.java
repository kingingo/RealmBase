package realmbase.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class LocationRecord extends Location {
	
	public int time;
	
	public LocationRecord() {}
	
	public LocationRecord(int time, float x, float y) {
		this.time=time;
		this.x=x;
		this.y=y;
	}
	
	public LocationRecord(int time, Location pos) {
		this(time,pos.x,pos.y);
	}
	
	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.time = in.readInt();
		super.parseFromInput(in);
	}
	
	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.time);
		super.writeToOutput(out);
	}

}
