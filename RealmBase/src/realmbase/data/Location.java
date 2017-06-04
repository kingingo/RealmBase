package realmbase.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.data.IData;


public class Location implements IData {
	
	public float x, y;
	
	public Location(){}
	
	public Location(float x, float y) {
		this.x=x;
		this.y=y;
	}

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.x = in.readFloat();
		this.y = in.readFloat();
	}
	
	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeFloat(this.x);
		out.writeFloat(this.y);
	}
	
	public float distanceSquaredTo(Location location) {
		float dx = location.x - this.x;
		float dy = location.y - this.y;
		return dx * dx + dy * dy;
	}
	
	public float getAngleTo(Location location) {
		return (float) (180-Math.atan2(location.x - this.x, location.y - this.y)*180/Math.PI);
	}
	
	public float distanceTo(Location location) {
		return (float) Math.sqrt(this.distanceSquaredTo(location));
	}
	
	public Location clone() {
		return new Location(x, y);
	}
}
