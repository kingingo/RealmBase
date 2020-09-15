package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.packets.Packet;

@Getter
@Setter
public class LoadPacket extends Packet{

	private int charId=1;
	private boolean isFromArena=false;

	public LoadPacket(){}
	
	public LoadPacket(int charId, boolean isFromArena){
		this.charId=charId;
		this.isFromArena=isFromArena;
	}
	
	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.charId=in.readInt();
		this.isFromArena=in.readBoolean();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		 out.writeInt(charId);
		 out.writeBoolean(isFromArena);
	}

	public String toString(){
		return formatToString(new String[]{
				"CharId: "+this.charId,
				"isFromArena: "+this.isFromArena});
	}
}
