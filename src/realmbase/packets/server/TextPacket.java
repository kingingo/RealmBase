package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.packets.Packet;

@Getter
@Setter
public class TextPacket extends Packet{

	private String name;
	private int objectId;
	private int numStars;
	private int bubbleTime;
	private String recipient;
	private String text;
	private String cleanText;

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.name = in.readUTF();
		this.objectId = in.readInt();
		this.numStars = in.readInt();
		this.bubbleTime = in.readUnsignedByte();
		this.recipient = in.readUTF();
		this.text = in.readUTF();
		this.cleanText = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		 out.writeUTF(this.name);
		 out.writeInt(this.objectId);
		 out.writeInt(this.numStars);
		 out.writeByte(this.bubbleTime);
		 out.writeUTF(this.recipient);
		 out.writeUTF(this.text);
		 out.writeUTF(this.cleanText);
	}

	public String toString(){
		return formatToString(new String[]{
				"Name: "+name,
				"ObjID: "+objectId,
				"NumStars: "+numStars,
				"BubbleTime: "+bubbleTime,
				"Recipient: "+recipient,
				"Text: "+text,
				"cleanText: "+cleanText});
	}
}
