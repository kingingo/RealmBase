package realmbase.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Status implements IData {
	
	private int objectId;
	private Location position = new Location();
	private StatData[] data = new StatData[0];

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.objectId = in.readInt();
		this.position.parseFromInput(in);
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
		this.position.writeToOutput(out);
		out.writeShort(this.data.length);
		for (StatData statData: this.data) {
			statData.writeToOutput(out);
		}
	}

}
