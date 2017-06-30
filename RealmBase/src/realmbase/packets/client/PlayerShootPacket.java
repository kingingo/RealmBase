package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.data.Location;
import realmbase.packets.Packet;

@Getter
@Setter
public class PlayerShootPacket extends Packet {
	
	private int time;
	private int bulletId;
	private int containerType;
	private Location startingPos = new Location();
	private float angle;

	public PlayerShootPacket(){}
	
	public PlayerShootPacket(int time,int bulletId,int containerType,Location startingPos, float angle) {
		this.time=time;
		this.bulletId=bulletId;
		this.containerType=containerType;
		this.startingPos=startingPos;
		this.angle=angle;
	}
	
	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.time = in.readInt();
		this.bulletId = in.readUnsignedByte();
		this.containerType = in.readUnsignedShort();
		this.startingPos.parseFromInput(in);
		this.angle = in.readFloat();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.time);
		out.writeByte(this.bulletId);
		out.writeShort(this.containerType);
		this.startingPos.writeToOutput(out);
		out.writeFloat(this.angle);
	}
	
	public String toString(){
		return formatToString(new String[]{
				"time: "+time,
				"bulletId: "+bulletId,
				"containerType: "+containerType,
				"Pos: "+startingPos.x+"/"+startingPos.y,
				"angle: "+angle
		});
	}
}
