package realmbase.data.portal;

import lombok.Getter;
import lombok.Setter;
import realmbase.RealmBase;
import realmbase.data.EntityData;
import realmbase.data.Location;
import realmbase.data.StatData;
import realmbase.data.Status;
import realmbase.xml.GetXml;
import realmbase.xml.datas.EnemyData;

@Getter
@Setter
public class PortalData extends EntityData{
	private int population;
	private PortalType type = PortalType.UNKOWN;
	
	public PortalData() {}
	
	public PortalData(short objectType, int population, Status status, String name){
		super(objectType,name,status);
	}
	
	public void loadStat(){
//		RealmBase.println("Portal: "+objectType);
//		if(GetXml.objectMap.containsKey(objectType)){
//			RealmBase.println("Portal: "+GetXml.objectMap.get(objectType).portal );
//			RealmBase.println("Portal: "+GetXml.objectMap.get(objectType).id);
//		}
		
		if(GetXml.objectMap.containsKey(objectType) 
				&& ((EnemyData)GetXml.objectMap.get(objectType)).portal){
			
			if(GetXml.objectMap.get(objectType).id.equalsIgnoreCase("Nexus Portal")){
				this.type=PortalType.NEXUS;
				for(StatData s : getStatus().getData()){
					if(s.id == 31){
						String value = s.stringValue;
						if(value.contains("NexusPortal")){
//							RealmBase.println("Value: "+value);
							value=value.replaceAll("NexusPortal.", "").replaceAll("\\(", "").replaceAll("/85\\)", "");
							String[] data = value.split(" ");
							setName(data[0]);
							setPopulation( Integer.valueOf(data[1]) );
						}else{
							RealmBase.println("PortalType not found "+value);
						}
					}
				}
			}else if(GetXml.objectMap.get(objectType).id.equalsIgnoreCase("Vault Portal")){
				this.type=PortalType.VAULT;
			}else if(GetXml.objectMap.get(objectType).id.equalsIgnoreCase("Pet Yard Portal")){
				this.type=PortalType.PET_YARD;
			}else if(GetXml.objectMap.get(objectType).id.equalsIgnoreCase("Daily Quest Portal")){
				this.type=PortalType.DAILY_QUEST;
			}
		}
	}
}


