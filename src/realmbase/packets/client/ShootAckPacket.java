package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.packets.Packet;

@Getter
@Setter
public class ShootAckPacket extends Packet {
	
	private int time;

	public ShootAckPacket(){}
	
	public ShootAckPacket(int time){
		this.time=time;
	}
	
	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.time = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.time);
	}

}
