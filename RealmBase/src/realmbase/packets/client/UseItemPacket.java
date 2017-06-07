package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.data.Location;
import realmbase.data.SlotObject;
import realmbase.packets.Packet;


public class UseItemPacket extends Packet {
	
	public int time;
	public SlotObject slotObject = new SlotObject();
	public Location itemUsePos = new Location();
	public int useType;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.time = in.readInt();
		this.slotObject.parseFromInput(in);
		this.itemUsePos.parseFromInput(in);
		this.useType = in.readUnsignedByte();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.time);
		this.slotObject.writeToOutput(out);
		this.itemUsePos.writeToOutput(out);
		out.writeByte(this.useType);
	}

}
