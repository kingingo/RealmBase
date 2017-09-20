package realmbase.listener;

import java.util.HashMap;

import lombok.Getter;
import realmbase.Client;
import realmbase.RealmBase;
import realmbase.data.EntityData;
import realmbase.data.PlayerData;
import realmbase.data.Status;
import realmbase.data.portal.PortalData;
import realmbase.event.EventHandler;
import realmbase.event.EventListener;
import realmbase.event.EventManager;
import realmbase.event.events.PacketReceiveEvent;
import realmbase.event.events.PortalNewEvent;
import realmbase.event.events.PortalUpdateEvent;
import realmbase.packets.Packet;
import realmbase.packets.server.Create_SuccessPacket;
import realmbase.packets.server.FailurePacket;
import realmbase.packets.server.NewTickPacket;
import realmbase.packets.server.UpdatePacket;
import realmbase.xml.GetXml;
public class PacketListener implements EventListener{

	@Getter
	private static HashMap<Client,HashMap<Integer,EntityData>> entities = new HashMap<Client, HashMap<Integer,EntityData>>();
	
	public PacketListener(){
		EventManager.register(this);
	}
	
	public static void clear(Client client){
		entities.remove(client);
	}
	
	public static PlayerData getPlayerData(Client client, int id){
		if(entities.containsKey(client))
			for(EntityData data : entities.get(client).values())
				if(data instanceof PlayerData && 
						id == data.getStatus().getObjectId())return ((PlayerData)data);
		return null;
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
	
	@EventHandler
	public void PacketReceiveEvent(PacketReceiveEvent ev) {
		Client client = ev.getClient();
		Packet packet = ev.getPacket();
		
		if(!entities.containsKey(client))entities.put(client, new HashMap<>());
		
		if(packet.getId() == GetXml.packetMapName.get("UPDATE")){
			UpdatePacket upacket = (UpdatePacket)packet;
			for(int i = 0; i < upacket.getNewObjs().length ; i++){
				EntityData e = upacket.getNewObjs()[i];
				
				if(GetXml.packetMapName.containsKey(Integer.valueOf(e.getObjectType()))
						&& GetXml.objectMap.get(Integer.valueOf(e.getObjectType())).player){
					PlayerData player = (PlayerData) e;
					entities.get(client).put(e.getStatus().getObjectId(), player);
					RealmBase.println("add Player Status "+player.getName());
				}else if(GetXml.objectMap.containsKey(Integer.valueOf(e.getObjectType()))
						&& GetXml.objectMap.get(Integer.valueOf(e.getObjectType())).portal){
					PortalData portal = (PortalData) e;
					entities.get(client).put(e.getStatus().getObjectId(), portal);
					EventManager.callEvent(new PortalNewEvent(portal,client));
				}else{
					entities.get(client).put(e.getStatus().getObjectId(), e);
				}
			}
			
			for(int i = 0; i < upacket.getDrops().length ; i++)
				entities.get(client).remove(upacket.getDrops()[i]);
		}else if(packet.getId() == GetXml.packetMapName.get("NEWTICK")){
			NewTickPacket tpacket = (NewTickPacket)packet;
			
			for(int i = 0; i < tpacket.getStatuses().length ; i++){
				Status e = tpacket.getStatuses()[i];
				
				for(Integer objectId : entities.get(client).keySet()){
					if(objectId == e.getObjectId()){
						EntityData data = entities.get(client).get(objectId);
						data.setStatus(e);
						
						if(data instanceof PortalData){
							((PortalData) data).loadStat();
							EventManager.callEvent(new PortalUpdateEvent(((PortalData) data),client));
						}
						break;
					}
				}
			}
		}else if(packet.getId() == GetXml.packetMapName.get("CREATE_SUCCESS")){
			Create_SuccessPacket cpacket = (Create_SuccessPacket)packet;
			client.setClientId(cpacket.getObjectId());
			RealmBase.println(client,"Connected successfull! "+cpacket.toString());
		}else if(packet.getId() == GetXml.packetMapName.get("FAILURE")){
			FailurePacket fpacket = (FailurePacket)ev.getPacket();
			RealmBase.println(client,"Error Packet: "+fpacket.toString());
		}
//		else if(packet.getId() == GetXml.packetMapName.get("QUESTOBJID")){
//			QuestObjIdPacket qpacket = (QuestObjIdPacket)packet;
//			
//			if(entities.get(client).containsKey(qpacket.getObjectId())){
//				EntityData e = entities.get(client).get(qpacket.getObjectId());
//			}
//		}
	}
}
