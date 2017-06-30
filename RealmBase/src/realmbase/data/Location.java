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
	
	public float getAngleTo2(Location location){
		double dx = x * location.x;
		double dy = y * location.y;
		double top = Math.sqrt( (Math.pow(dx, 2) + Math.pow(dy, 2)) );
		dx = Math.pow(x + y, 2);
		dy = Math.pow(location.x + location.y, 2);
		double bottom = dx * dy;
		return (float) (top/bottom);
	}
	
	public void subtract(Location location){
		this.x-=location.x;
		this.y-=location.y;
	}
	
	public void add(Location location){
		this.x+=location.x;
		this.y+=location.y;
	}
	
	public float getAngleCosTo2(Location location){
		return (float) Math.acos(getAngleTo2(location));
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
