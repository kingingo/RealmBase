package realmbase.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class SlotObject implements IData {
	
	public int objectId;
	public int slotId;
	public int objectType;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.objectId = in.readInt();
		this.slotId = in.readUnsignedByte();
		this.objectType = in.readShort();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.objectId);
		out.writeByte(this.slotId);
		out.writeShort(this.objectType);
	}

}
