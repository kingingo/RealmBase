package realmbase.data;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

public class StatData implements IData {
	
	public int id;
	public int intValue;
	public String stringValue;
	
	public boolean isUTFData() {
		switch (id) {
			case 31:
			case 62:
			case 82:
			case 38:
			case 54: {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		id = in.readUnsignedByte();
		if (isUTFData()) {
			stringValue = in.readUTF();
		} else {
			intValue = in.readInt();
		}
	}
	
	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeByte(id);
		if (isUTFData()) {
			out.writeUTF(stringValue);
		} else {
			out.writeInt(intValue);
		}
	}
	
}
