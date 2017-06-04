package realmbase.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.GetXml;
import realmbase.data.IData;

@Getter
@Setter
public class EntityData implements IData{
	protected short objectType;
	protected String name;
	protected Status status = new Status();
	
	public EntityData(){}
	
	public EntityData(short objectType, String name, Status status2){
		this.objectType=objectType;
		this.status=status2;
		this.name=name;
	}
	
	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
//		this.objectType = in.readShort();
		this.status.parseFromInput(in);
	}
	
	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeShort(this.objectType);
		this.status.writeToOutput(out);
	}
	
	public static EntityData create(DataInputStream in){
		try {
			EntityData e;
			
			int objectType = Integer.valueOf(in.readShort());
			if(GetXml.getPlayersMap().containsKey(objectType)){
				e = new PlayerData();
				e.parseFromInput(in);
				((PlayerData)e).loadStatData();
			}else if(GetXml.getPortalsMap().containsKey(objectType)){
				e = new PortalData();
				e.parseFromInput(in);
			}else{
				e = new EntityData();
				e.parseFromInput(in);
			}
			e.setObjectType((short) objectType);
			
			return e;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
