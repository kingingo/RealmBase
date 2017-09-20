package realmbase.event.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import realmbase.Client;
import realmbase.data.Location;
import realmbase.event.Event;
import realmbase.xml.datas.ProjectileData;

@AllArgsConstructor
@Getter
public class AllyShootEvent extends Event{
	private int bulletId=0; 
	private int ownerId=0; 
	private int containerType=0; 
	private Location startingPos=null; 
	private float angle=0; 
	private ProjectileData projectileData = null;
	private Client client;
}
