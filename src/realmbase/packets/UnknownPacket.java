package realmbase.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;

public class UnknownPacket extends Packet {
	
	private int id;
	@Getter
	@Setter
	private byte[] data;

	public UnknownPacket(int id,byte[] data){
		this.id=id;
		this.data=data;
	}
	
	public int getId() {
		return this.id;
	}

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		
	}
	
	protected void setId(int id) {
		this.id = id;
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		
	}

}
