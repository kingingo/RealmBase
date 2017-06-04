package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.packets.Packet;

@Getter
@Setter
public class PlayerTextPacket extends Packet{

	private String text;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.text = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		 out.writeUTF(text);
	}

	public String toString(){
		return formatToString(new String[]{
				"Text: "+text});
	}
}
