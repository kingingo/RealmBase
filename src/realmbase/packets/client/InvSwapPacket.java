package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.data.Location;
import realmbase.data.SlotObject;
import realmbase.packets.Packet;


public class InvSwapPacket extends Packet {
	
	public int time;
	public Location position = new Location();
	public SlotObject slotObject1 = new SlotObject();
	public SlotObject slotObject2 = new SlotObject();

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.time = in.readInt();
		this.position.parseFromInput(in);
		this.slotObject1.parseFromInput(in);
		this.slotObject2.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.time);
		this.position.writeToOutput(out);
		this.slotObject1.writeToOutput(out);
		this.slotObject2.writeToOutput(out);
	}

}
