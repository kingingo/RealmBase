package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.packets.Packet;


public class EnemyHitPacket extends Packet {
	
	public int time;
	public int bulletId;
	public int targetId;
	public boolean kill;
	
	public EnemyHitPacket() {}
	
	public EnemyHitPacket(int time, int bulletId, int targetId, boolean kill) {
		this.time=time;
		this.bulletId=bulletId;
		this.targetId=targetId;
		this.kill=kill;
	}

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.time = in.readInt();
		this.bulletId = in.readUnsignedByte();
		this.targetId = in.readInt();
		this.kill = in.readBoolean();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.time);
		out.writeByte(this.bulletId);
		out.writeInt(this.targetId);
		out.writeBoolean(this.kill);
	}

	public String toString(){
		return formatToString(new String[]{
				"Time: "+this.time,
				"BulletId: "+this.bulletId,
				"TargetId: "+this.targetId,
				"Kill: "+this.kill
		});
	}
}
