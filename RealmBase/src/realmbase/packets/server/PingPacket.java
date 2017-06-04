package realmbase.packets.server;

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
public class PingPacket extends Packet{

	private int serial;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.serial = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		 out.writeInt(serial);
	}

	public String toString(){
		return formatToString(new String[]{
				"Serial: "+serial});
	}
}
