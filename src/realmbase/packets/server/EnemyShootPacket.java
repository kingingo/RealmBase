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
public class EnemyShootPacket extends Packet {
	
	private int bulletId;
	private int ownerId;
	private int containerType;
	private Location startingPos = new Location();
	private float angle;
	private float angleInc;
	private short damage;
	private int numShots;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.bulletId = in.readUnsignedByte();
		this.ownerId = in.readInt();
		this.containerType = in.readUnsignedByte();
		this.startingPos.parseFromInput(in);
		this.angle = in.readFloat();
		this.damage = in.readShort();
		try {
			this.numShots = in.readUnsignedByte();
			this.angleInc = in.readFloat();
		} catch (IOException e) {
			this.numShots = 1;
			this.angleInc = 0;
		}
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeByte(this.bulletId);
		out.writeInt(this.ownerId);
		out.writeByte(this.containerType);
		this.startingPos.writeToOutput(out);
		out.writeFloat(this.angle);
		out.writeShort(this.damage);
		if (this.numShots != 1 || this.angleInc != 0) {
			out.writeByte(this.numShots);
			out.writeFloat(this.angleInc);
		}
	}

	public String toString(){
		return formatToString(new String[]{
				"BulletId: "+this.bulletId,
				"OwnerId: "+this.ownerId,
				"ContainerType: "+this.containerType,
				"Pos: "+this.startingPos.x+"/"+this.startingPos.y,
				"Angle: "+this.angle,
				"Damage: "+this.damage,
				"numShots: "+this.numShots,
				"AngleInc: "+this.angleInc
		});
	}
}
