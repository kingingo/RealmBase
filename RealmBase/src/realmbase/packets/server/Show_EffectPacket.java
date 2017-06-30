package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.data.Location;
import realmbase.packets.Packet;



public class Show_EffectPacket extends Packet {
	
	public int effectType;
	public int targetObjectId;
	public Location pos1 = new Location();
	public Location pos2 = new Location();
	public int color;
    public float duration;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.effectType = in.readUnsignedByte();
		this.targetObjectId = in.readInt();
		this.pos1.parseFromInput(in);
		this.pos2.parseFromInput(in);
		this.color = in.readInt();
		this.duration = in.readFloat();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeByte(this.effectType);
		out.writeInt(this.targetObjectId);
		this.pos1.writeToOutput(out);
		this.pos2.writeToOutput(out);
		out.writeInt(this.color);
		out.writeFloat(this.duration);
	}

	public String toString(){
		return formatToString(new String[]{
				"EffectType: "+this.effectType,
				"TargetId: "+this.targetObjectId,
				"Pos1: "+this.pos1.x+"/"+this.pos1.y,
				"Pos2: "+this.pos2.x+"/"+this.pos2.y,
				"Color: "+this.color,
				"Duation: "+this.duration
		});
	}
}
