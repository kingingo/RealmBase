package realmbase.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class Tile implements IData {
	
	public short x;
	public short y;
	public int type;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.x = in.readShort();
		this.y = in.readShort();
		this.type = in.readUnsignedShort();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeShort(this.x);
		out.writeShort(this.y);
		out.writeShort(this.type);
	}

}
