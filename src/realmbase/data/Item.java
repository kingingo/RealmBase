package realmbase.data;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;


public class Item implements IData {
	
	public int item;
	public int slotType;
	public boolean tradeable;
	public boolean included;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.item = in.readInt();
		this.slotType = in.readInt();
		this.tradeable = in.readBoolean();
		this.included = in.readBoolean();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.item);
		out.writeInt(this.slotType);
		out.writeBoolean(this.tradeable);
		out.writeBoolean(this.included);
	}

}
