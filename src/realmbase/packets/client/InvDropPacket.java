package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.data.SlotObject;
import realmbase.packets.Packet;


public class InvDropPacket extends Packet {
	
	public SlotObject slotObject = new SlotObject();

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.slotObject.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		this.slotObject.writeToOutput(out);
	}

}
