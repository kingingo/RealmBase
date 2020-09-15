package realmbase.packets.client;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.packets.Packet;

@Getter
@Setter
public class PongPacket extends Packet{

	private int serial = 0;
	private int time = 0;
	
	public PongPacket(){}
	
	public PongPacket(int serial, int time){
		this.serial = serial;
		this.time = time;
	}

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.serial = in.readInt();
		this.time = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		 out.writeInt(serial);
		 out.writeInt(time);
	}

	public String toString(){
		return formatToString(new String[]{
				"Serial: "+serial,
				"Time: "+time});
	}
}
