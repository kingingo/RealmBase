package realmbase.listener;

import java.util.HashMap;

import realmbase.Client;
import realmbase.GetXml;
import realmbase.RealmBase;
import realmbase.data.EntityData;
import realmbase.data.PlayerData;
import realmbase.data.PortalData;
import realmbase.data.Status;
import realmbase.data.Type;
import realmbase.packets.Packet;
import realmbase.packets.server.New_TickPacket;
import realmbase.packets.server.QuestObjIdPacket;
import realmbase.packets.server.UpdatePacket;

public class ObjectListener implements PacketListener{

	private static HashMap<Client,HashMap<Integer,PlayerData>> players = new HashMap<Client, HashMap<Integer, PlayerData>>();
	private static HashMap<Client,HashMap<Integer,EntityData>> quests = new HashMap<Client, HashMap<Integer,EntityData>>();
	private static HashMap<Client,HashMap<Integer,PortalData>> portals = new HashMap<Client, HashMap<Integer,PortalData>>();
	
	public ObjectListener() {
		PacketManager.addListener(this);
	}
	
	public static PlayerData getPlayerData(Client client, String name){
		if(players.containsKey(client))
			for(PlayerData data : players.get(client).values())
				if(data.getName().equalsIgnoreCase(name))return data;
		return null;
	}
	
	public static EntityData getObject(Client client, int objectId){
		if(players.get(client).containsKey(objectId))return players.get(client).get(objectId);
		if(quests.get(client).containsKey(objectId))return quests.get(client).get(objectId);
		if(portals.get(client).containsKey(objectId))return portals.get(client).get(objectId);
		return null;
	}
	
	@Override
	public boolean onReceive(Client client, Packet packet, Type from) {
		if(!players.containsKey(client))players.put(client, new HashMap<>());
		if(!quests.containsKey(client))quests.put(client, new HashMap<>());
		if(!portals.containsKey(client))portals.put(client, new HashMap<>());
		
		if(packet.getId() == GetXml.getPacketMapName().get("UPDATE")){
			UpdatePacket upacket = (UpdatePacket)packet;
			for(int i = 0; i < upacket.getNewObjs().length ; i++){
				EntityData e = upacket.getNewObjs()[i];
				
				if(GetXml.getPlayersMap().containsKey(Integer.valueOf(e.getObjectType()))){
					PlayerData player = (PlayerData) e;
					player.loadStatData();
					RealmBase.println("Load: "+player.getStatus().getObjectId()+" "+player.getName());
					players.get(client).put(e.getStatus().getObjectId(), player);
				}else if(GetXml.getPortalsMap().containsKey(Integer.valueOf(e.getObjectType()))){
					portals.get(client).put(e.getStatus().getObjectId(), (PortalData) e);
				}else if(GetXml.getQuestsMap().containsKey(Integer.valueOf(e.getObjectType()))){
					quests.get(client).put(e.getStatus().getObjectId(), e);
				}
			}
			
			for(int i = 0; i < upacket.getDrops().length ; i++){
				int objectId = upacket.getDrops()[i];
				
				players.get(client).remove(objectId);
				portals.get(client).remove(objectId);
				quests.get(client).remove(objectId);
			}
		}else if(packet.getId() == GetXml.getPacketMapName().get("NEW_TICK")){
			New_TickPacket tpacket = (New_TickPacket)packet;
			
			for(int i = 0; i < tpacket.getStatuses().length ; i++){
				Status e = tpacket.getStatuses()[i];
				
				for(Integer objectId : players.get(client).keySet()){
					if(objectId == e.getObjectId()){
						players.get(client).get(objectId).getStatus().getPosition().x=e.getPosition().x;
						players.get(client).get(objectId).getStatus().getPosition().y=e.getPosition().y;
						break;
					}
				}
			}
		}else if(packet.getId() == GetXml.getPacketMapName().get("QUESTOBJID")){
			QuestObjIdPacket qpacket = (QuestObjIdPacket)packet;
			
			if(quests.get(client).containsKey(qpacket.getObjectId())){
				EntityData e = quests.get(client).get(qpacket.getObjectId());
			}
		}
		return false;
	}

	@Override
	public boolean onSend(Client client, Packet packet, Type to) {
		return false;
	}

}
