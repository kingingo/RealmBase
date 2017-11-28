package realmbase.data;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import realmbase.data.IData;
import realmbase.data.portal.PortalData;
import realmbase.frame.ClientJPanel;
import realmbase.xml.GetXml;
import realmbase.xml.datas.EnemyData;

@Getter
@Setter
public class EntityData implements IData{
	protected int objectType;
	protected String name;
	protected Status status = new Status();
	
	public EntityData(){}
	
	public EntityData(int objectType, String name, Status status){
		this.objectType=objectType;
		this.status=status;
		this.name=name;
	}
	
	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.status.parseFromInput(in);
	}
	
	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeShort(this.objectType);
		this.status.writeToOutput(out);
	}
	
	public static EntityData create(DataInputStream in){
		try {
			EntityData e = null;
			
			int objectType = in.readShort();
			if(GetXml.objectMap.containsKey(objectType)){
				if(((EnemyData)GetXml.objectMap.get(objectType)).player){
					e = new PlayerData();
					e.setObjectType(objectType);
					e.parseFromInput(in);
					((PlayerData)e).loadStatData();
				}else if(((EnemyData)GetXml.objectMap.get(objectType)).portal){
					e = new PortalData();
					e.setObjectType(objectType);
					e.parseFromInput(in);
					((PortalData)e).loadStat();
				}
			}
			
			if(e==null){
				e = new EntityData();
				e.setObjectType(objectType);
				e.parseFromInput(in);
			}
			return e;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
