package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.Parameter;
import realmbase.RealmBase;
import realmbase.encryption.GUID;
import realmbase.packets.Packet;
import realmbase.packets.server.ReconnectPacket;

@Getter
@Setter
public class HelloPacket extends Packet{

	private String buildVersion = Parameter.BUILD_VERSION;
	private int gameId = 0;
	private String guid = "";
	private int random1 = 0;
	private String password = "";
	private int random2 = 0;
	private String secret = "";
	private int keyTime = 0;
	private byte[] key = new byte[0];
	private byte[] mapJson = new byte[0];
	private String entrytag = "";
	private String gameNet = "";
	private String gameNetUserId = "rotmg";
	private String playPlatform = "";
	private String platformToken = "";
	private String userToken = "";
	
	public HelloPacket(){}
	
	public HelloPacket(String username,String password){
		setGuid(GUID.encrypt(username));
		setPassword(GUID.encrypt(password));
	}

	public HelloPacket(ReconnectPacket packet, String username,String password){
		this(username,password);
		this.key=packet.getKey();
		this.keyTime=packet.getKeyTime();
		this.gameId=packet.getGameId();
	}
	
	public void parseFromInput(DataInputStream in) throws IOException {
		this.buildVersion = in.readUTF();
		this.gameId = in.readInt();
		this.guid = in.readUTF();
		this.random1 = in.readInt();
		this.password = in.readUTF();
		this.random2 = in.readInt();
		this.secret = in.readUTF();
		this.keyTime = in.readInt();
		this.key = new byte[in.readShort()];
		in.readFully(this.key);
		this.mapJson = new byte[in.readInt()];
	    in.readFully(this.mapJson);
		this.entrytag = in.readUTF();
		this.gameNet = in.readUTF();
		this.gameNetUserId = in.readUTF();
		this.playPlatform = in.readUTF();
		this.platformToken = in.readUTF();
		this.userToken = in.readUTF();
	}

	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeUTF(this.buildVersion);
        out.writeInt(this.gameId);
        out.writeUTF(this.guid);
        out.writeInt((int)Math.floor(Math.random() * 1000000000));
        out.writeUTF(this.password);
        out.writeInt((int)Math.floor(Math.random() * 1000000000));
        out.writeUTF(this.secret);
        out.writeInt(this.keyTime);
        out.writeShort(this.key.length);
        out.write(this.key);
        out.writeInt(this.mapJson.length);
        out.write(this.mapJson);
        out.writeUTF(this.entrytag);
        out.writeUTF(this.gameNet);
        out.writeUTF(this.gameNetUserId);
        out.writeUTF(this.playPlatform);
        out.writeUTF(this.platformToken);
        out.writeUTF(this.userToken);
	}
	
	public String toString(){
		return formatToString(new String[]{
						"Version: "+this.buildVersion,
						"GameId: "+this.gameId,
						"GUID: "+this.guid,
						"R1: "+random1,
						"Password: "+this.password,
						"R2: "+random2,
						"Secret: "+this.secret,
						"KeyTime: "+this.keyTime,
						"Key: "+this.key,
						"MapJSON: "+this.mapJson,
						"EntryTag: "+this.entrytag,
						"GameNet: "+this.gameNet,
						"GameNetUserId: "+this.gameNetUserId,
						"Platform: "+this.playPlatform,
						"Token: "+this.userToken});
	}
}
