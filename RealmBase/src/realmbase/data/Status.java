package realmbase.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class Status implements IData {
	
	public int objectId;
	public Location pos = new Location();
	public StatData[] data = new StatData[0];

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.objectId = in.readInt();
		this.pos.parseFromInput(in);
		this.data = new StatData[in.readShort()];
		for (int i = 0; i < this.data.length; i++) {
			StatData statData = new StatData();
			statData.parseFromInput(in);
			this.data[i] = statData;
		}
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.objectId);
		this.pos.writeToOutput(out);
		out.writeShort(this.data.length);
		for (StatData statData: this.data) {
			statData.writeToOutput(out);
		}
	}

}
