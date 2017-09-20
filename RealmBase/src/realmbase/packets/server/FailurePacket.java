package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.packets.Packet;

@Getter
@Setter
public class FailurePacket extends Packet {
	
	private int errorId;
	private String errorDescription;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.errorId = in.readInt();
		this.errorDescription = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.errorId);
		out.writeUTF(this.errorDescription);
	}

	public String toString(){
		return formatToString(new String[]{
				"ErrorId: "+errorId,
				"Description: "+errorDescription});
	}
}
