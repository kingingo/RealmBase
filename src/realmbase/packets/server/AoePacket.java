package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.data.Location;
import realmbase.packets.Packet;

@Getter
@Setter
public class AoePacket extends Packet{

	public Location pos = new Location();
	public float radius;
	public int damage;
	public int effect;
	public float duration;
	public int origType;


	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.pos.parseFromInput(in);
        this.radius = in.readFloat();
        this.damage = in.readUnsignedShort();
        this.effect = in.readUnsignedByte();
        this.duration = in.readFloat();
        this.origType = in.readUnsignedShort();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		pos.writeToOutput(out);
		out.writeFloat(this.radius);
		out.writeShort(this.damage);
		out.writeByte(this.effect);
		out.writeFloat(this.duration);
		out.writeShort(this.origType);
	}

	public String toString(){
		return formatToString(new String[]{
				"Position: "+this.pos.x+"/"+this.pos.y,
				"Radius: "+this.radius,
				"Damage: "+this.damage,
				"Effect: "+this.effect,
				"Duration: "+this.duration,
				"OrigType: "+this.origType
				});
	}
}
