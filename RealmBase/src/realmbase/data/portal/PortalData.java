package realmbase.data.portal;

import lombok.Getter;
import lombok.Setter;
import realmbase.GetXml;
import realmbase.data.EntityData;
import realmbase.data.Location;
import realmbase.data.StatData;
import realmbase.data.Status;

@Getter
@Setter
public class PortalData extends EntityData{
	private int population;
	
	public PortalData() {}
	
	public PortalData(short objectType, int population, Status status, String name){
		super(objectType,name,status);
		
		if(GetXml.getPortalsMap().containsKey(objectType)
				&& GetXml.getPortalsMap().get(objectType).equalsIgnoreCase("Nexus Portal")){
			loadStat();
		}
	}
	
	public void loadStat(){
		for(StatData s : getStatus().getData()){
			if(s.id == 31){
				String value = s.stringValue;
				value.replaceAll("NexusPortal.", "").replaceAll("(.", "").replaceAll("/85).", "");
				String[] data = value.split(" ");
				setName(data[0]);
				setPopulation( Integer.valueOf(data[1]) );
			}
		}
	}
}
