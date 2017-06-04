package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.packets.Packet;

@Setter
@Getter
public class MapInfoPacket extends Packet{

	private int width;
	private int height;
	private String name;
	private String displayName;
	private int fp;
	private int background;
	private int difficulty;
	private boolean allowPlayerTeleport;
	private boolean showDisplays;
	private String[] clientXML;
	private String[] extraXML;

	public void parseFromInput(DataInputStream in) throws IOException {
		this.width = in.readInt();
		this.height = in.readInt();
		this.name = in.readUTF();
		this.displayName = in.readUTF();
		this.fp = in.readInt();
		this.background = in.readInt();
		this.difficulty = in.readInt();
		this.allowPlayerTeleport = in.readBoolean();
		this.showDisplays = in.readBoolean();
		this.clientXML = new String[in.readShort()];
		for (int i = 0; i < this.clientXML.length; i++) {
			byte[] utf = new byte[in.readInt()];
			in.readFully(utf);
			this.clientXML[i] = new String(utf, "UTF-8");
		}
		this.extraXML = new String[in.readShort()];
		for (int i = 0; i < this.extraXML.length; i++) {
			byte[] utf = new byte[in.readInt()];
			in.readFully(utf);
			this.extraXML[i] = new String(utf, "UTF-8");
		}
	}

	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeInt(this.width);
		out.writeInt(this.height);
		out.writeUTF(this.name);
		out.writeUTF(this.displayName);
		out.writeInt(this.fp);
		out.writeInt(this.background);
		out.writeInt(this.difficulty);
		out.writeBoolean(this.allowPlayerTeleport);
		out.writeBoolean(this.showDisplays);
		out.writeShort(this.clientXML.length);
		for (String xml: this.clientXML) {
			byte[] utf = xml.getBytes("UTF-8");
			out.writeInt(utf.length);
			out.write(utf);
		}
		out.writeShort(this.extraXML.length);
		for (String xml: this.extraXML) {
			byte[] utf = xml.getBytes("UTF-8");
			out.writeInt(utf.length);
			out.write(utf);
		}
	}

	public String toString(){
		return formatToString(new String[]{
				"Width: "+this.width,
				"Height: "+this.height,
				"Name: "+this.name,
				"Displayname: "+this.displayName,
				"FP: "+this.fp,
				"Background: "+this.background,
				"Difficulty: "+this.difficulty,
				"AllowPlayerTP: "+this.allowPlayerTeleport,
				"ShowDisplays: "+this.showDisplays,
				formatToString(this.clientXML),
				formatToString(this.extraXML)
		});
	}
}
