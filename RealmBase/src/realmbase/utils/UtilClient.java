package realmbase.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import realmbase.Client;
import realmbase.data.Callback;
import realmbase.data.Location;
import realmbase.data.PlayerData;
import realmbase.list.Sort;

public class UtilClient {

	public static ArrayList<Sort<PlayerData>> getOrdedList(HashMap<Integer,PlayerData> clients, Location position, long distance, Callback<ArrayList<PlayerData>> filter){
		ArrayList<Sort<PlayerData>> list = new ArrayList<>();
		ArrayList<PlayerData> cs = new ArrayList<>();
		cs.addAll(clients.values());
		
		if(filter!=null){
			filter.call(cs, null);
		}
		
		if(position!=null){
			float between;
			for(PlayerData data : cs){
				between = position.distanceTo(data.getStatus().getPosition());
				if(between > distance){
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
