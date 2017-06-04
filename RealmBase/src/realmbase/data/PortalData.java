package realmbase.data;

import lombok.Getter;
import lombok.Setter;
import realmbase.data.Location;

@Getter
@Setter
public class PortalData extends EntityData{
	private int population;
	
	public PortalData() {}
	
	public PortalData(short objectType, int population, Status status, String name){
		super(objectType,name,status);
	}
}
