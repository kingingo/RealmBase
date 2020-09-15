package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.packets.Packet;

@Getter
@Setter
public class ReconnectPacket extends Packet{

	private String name;
	private String host;
	private int port;
	private int gameId;
	private int keyTime;
	private boolean isFromArena;
	private byte[] key = new byte[0];

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.name = in.readUTF();
		this.host = in.readUTF();
		this.port = in.readInt();
		this.gameId = in.readInt();
		this.keyTime = in.readInt();
		this.isFromArena = in.readBoolean();
		this.key = new byte[in.readShort()];
		in.readFully(key);
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeUTF(this.name);
		out.writeUTF(this.host);
		out.writeInt(this.port);
		out.writeInt(this.gameId);
		out.writeInt(this.keyTime);
		out.writeBoolean(this.isFromArena);
		out.writeShort(this.key.length);
		out.write(this.key);
	}

	public String toString(){
		return formatToString(new String[]{
				"Name: "+name,
				"Host: "+host,
				"Port: "+port,
				"GameId: "+this.gameId,
				"KeyTime: "+this.keyTime,
				"IsFromArea: "+this.isFromArena,
				"Key:"+ key});
	}
}
