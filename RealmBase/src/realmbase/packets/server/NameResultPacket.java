package realmbase.packets.server;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.packets.Packet;

@Getter
@Setter
public class NameResultPacket extends Packet{

	public boolean success;
	public String errorText;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.success = in.readBoolean();
		this.errorText = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		 out.writeBoolean(this.success);
		 out.writeUTF(errorText);
	}

	public String toString(){
		return formatToString(new String[]{
				"Success: "+success,
				"ErrorText: "+errorText});
	}
}
