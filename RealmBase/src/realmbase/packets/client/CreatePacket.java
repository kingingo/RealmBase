package realmbase.packets.client;

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
public class CreatePacket extends Packet{

	private int charakterType = 0;
	private int skinType = 0;
	
	public CreatePacket(){}
	
	public CreatePacket(int charakterType){
		this(charakterType,0);
	}
	
	public CreatePacket(int charakterType, int skinType){
		this.charakterType = charakterType;
		this.skinType = skinType;
	}

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.charakterType = in.readShort();
		this.skinType = in.readShort();
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		 out.writeShort(charakterType);
		 out.writeShort(skinType);
	}

	public String toString(){
		return formatToString(new String[]{
				"CharakterType: "+charakterType,
				"SkinType: "+skinType});
	}
}
