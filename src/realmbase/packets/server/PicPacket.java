package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import realmbase.data.BitmapData;
import realmbase.packets.Packet;

public class PicPacket extends Packet {
	
	public BitmapData bitmapData = new BitmapData();

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.bitmapData.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		this.bitmapData.writeToOutput(out);
	}

}
