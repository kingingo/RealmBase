package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import realmbase.packets.Packet;

public class TradeChangedPacket extends Packet {
	
	public boolean[] offer = new boolean[0];

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.offer = new boolean[in.readShort()];
		for (int i = 0; i < this.offer.length; i++) {
			this.offer[i] = in.readBoolean();
		}
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeShort(this.offer.length);
		for (boolean b: this.offer) {
			out.writeBoolean(b);
		}
	}

}
