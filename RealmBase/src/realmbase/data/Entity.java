package realmbase.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Entity implements IData {
	
	public short objectType;
	public String name;
	public Status status = new Status();
	
	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.objectType = in.readShort();
		this.status.parseFromInput(in);
	}
	
	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeShort(this.objectType);
		this.status.writeToOutput(out);
	}
	
}
