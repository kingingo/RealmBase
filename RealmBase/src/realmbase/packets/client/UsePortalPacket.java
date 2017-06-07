package realmbase.packets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.data.portal.PortalData;
import realmbase.packets.Packet;

@Getter
@Setter
public class UsePortalPacket extends Packet{

	private int objectId;

	public UsePortalPacket(){}

	public UsePortalPacket(PortalData portal){
		this(portal.getStatus().getObjectId());
	}
	
	public UsePortalPacket(int objectId){
		this.objectId=objectId;
	}
	
	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.objectId = in.readInt();
	}

	@Override	
	public void writeToOutput(DataOutputStream out) throws IOException {
		 out.writeInt(objectId);
	}

	public String toString(){
		return formatToString(new String[]{
				"ObjectId: "+this.objectId});
	}
}
