package realmbase.listener;

import java.util.HashMap;

import lombok.Getter;
import realmbase.Client;
import realmbase.RealmBase;
import realmbase.data.EntityData;
import realmbase.data.PlayerData;
import realmbase.data.StatData;
import realmbase.data.Status;
import realmbase.data.Type;
import realmbase.data.portal.PortalData;
import realmbase.packets.Packet;
import realmbase.packets.server.New_TickPacket;
import realmbase.packets.server.QuestObjIdPacket;
import realmbase.packets.server.UpdatePacket;
import realmbase.xml.GetXml;
public class ObjectListener implements PacketListener{

	@Getter
	private static HashMap<Client,HashMap<Integer,EntityData>> entities = new HashMap<Client, HashMap<Integer,EntityData>>();
	
	public ObjectListener() {
		PacketManager.addListener(this);
	}
	
	public static void clear(Client client){
		entities.remove(client);
	}
	
	public static PlayerData getPlayerData(Client client, String name){
		if(entities.containsKey(client))
			for(EntityData data : entities.get(client).values())
				if(data instanceof PlayerData && 
						data.getName().equalsIgnoreCase(name))return ((PlayerData)data);
		return null;
	}
	
	public static HashMap<Integer,EntityData> get(Client client){
		return entities.get(client);
	}
	
	public static EntityData getObject(Client client, int objectId){
		if(entities.get(client).containsKey(objectId))return entities.get(client).get(objectId);
		return null;
	}
	
	@Override
	public boolean onReceive(Client client, Packet packet, Type from) {
		if(!entities.containsKey(client))entities.put(client, new HashMap<>());
		
		if(packet.getId() == GetXml.packetMapName.get("UPDATE")){
			UpdatePacket upacket = (UpdatePacket)packet;
			for(int i = 0; i < upacket.getNewObjs().length ; i++){
				EntityData e = upacket.getNewObjs()[i];
				
				if(GetXml.packetMapName.containsKey(Integer.valueOf(e.getObjectType()))
						&& GetXml.objectMap.get(Integer.valueOf(e.getObjectType())).player){
					PlayerData player = (PlayerData) e;
					entities.get(client).put(e.getStatus().getObjectId(), player);
				}else if(GetXml.objectMap.containsKey(Integer.valueOf(e.getObjectType()))
						&& GetXml.objectMap.get(Integer.valueOf(e.getObjectType())).portal){
					PortalData portal = (PortalData) e;
					entities.get(client).put(e.getStatus().getObjectId(), portal);
				}else{
					entities.get(client).put(e.getStatus().getObjectId(), e);
				}
			}
			
			for(int i = 0; i < upacket.getDrops().length ; i++)
				entities.get(client).remove(upacket.getDrops()[i]);
		}else if(packet.getId() == GetXml.packetMapName.get("NEW_TICK")){
			New_TickPacket tpacket = (New_TickPacket)packet;
			
			for(int i = 0; i < tpacket.getStatuses().length ; i++){
				Status e = tpacket.getStatuses()[i];
				
				for(Integer objectId : entities.get(client).keySet()){
					if(objectId == e.getObjectId()){
						entities.get(client).get(objectId).getStatus().getPosition().x=e.getPosition().x;
						entities.get(client).get(objectId).getStatus().getPosition().y=e.getPosition().y;
						break;
					}
				}
			}
		}
//		else if(packet.getId() == GetXml.packetMapName.get("QUESTOBJID")){
//			QuestObjIdPacket qpacket = (QuestObjIdPacket)packet;
//			
//			if(entities.get(client).containsKey(qpacket.getObjectId())){
//				EntityData e = entities.get(client).get(qpacket.getObjectId());
//			}
//		}
		return false;
	}

	@Override
	public boolean onSend(Client client, Packet packet, Type to) {
		return false;
	}

}
