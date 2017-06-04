package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.packets.Packet;

@Getter
@Setter
public class UpdateAckPacket extends Packet {

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
	}

}
