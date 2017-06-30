package realmbase.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import realmbase.Client;
import realmbase.RealmBase;
import realmbase.data.Callback;
import realmbase.data.EntityData;
import realmbase.data.Location;
import realmbase.data.PlayerData;
import realmbase.list.Sort;
import realmbase.listener.ObjectListener;
import realmbase.xml.GetXml;

public class UtilClient {

	public static ArrayList<Sort<EntityData>> getOrdedEntityList(HashMap<Integer,EntityData> clients, Location position, double distance){
		ArrayList<Sort<EntityData>> list = new ArrayList<>();
		
		if(position!=null){
			float between;
			
			for(EntityData data : clients.values()){
				if(GetXml.objectMap.containsKey(data.getObjectType()) &&
						GetXml.objectMap.get(data.getObjectType()).enemy){
					between = position.distanceTo(data.getStatus().getPosition());
					if(between <= distance){
						list.add(new Sort<EntityData>(data, (int)between ));
					}
				}
			}
		}else{
			for(EntityData data : clients.values()){
				if(GetXml.objectMap.containsKey(data.getObjectType()) &&
						GetXml.objectMap.get(data.getObjectType()).enemy){
					list.add(new Sort<EntityData>(data, (int)1 ));
				}
			}
		}
		
		Collections.sort(list, Sort.ABSTEIGEND());
		
		return list;
	}
	
	public static ArrayList<Sort<PlayerData>> getOrdedList(HashMap<Integer,EntityData> clients, Location position, long distance, Callback<ArrayList<PlayerData>> filter){
		ArrayList<Sort<PlayerData>> list = new ArrayList<>();
		ArrayList<PlayerData> cs = new ArrayList<>();
		for(EntityData e : clients.values())if(e instanceof PlayerData)cs.add( ((PlayerData)e) );
		
		if(filter!=null){
			filter.call(cs, null);
		}
		
		if(position!=null){
			float between;
			for(PlayerData data : cs){
				between = position.distanceTo(data.getStatus().getPosition());
				if(between < distance){
					list.add(new Sort<PlayerData>(data, (int)between ));
				}
			}
		}else{
			for(PlayerData data : cs)
				list.add(new Sort<PlayerData>(data, (int)1 ));
		}
		
		Collections.sort(list, Sort.AUFSTEIGEND());
		
		return list;
	}
}
