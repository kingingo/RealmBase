package realmbase.data;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
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
		this.objectType = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.objectId);
		out.writeByte(this.slotId);
		out.writeInt(this.objectType);
	}

	public String toString(){
		return "ObjId: "+this.objectId+"\nSlotId: "+this.slotId+"\nObjType: "+this.objectType;
	}
}
