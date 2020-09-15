package realmbase.listener;

import java.util.HashMap;
import java.util.Iterator;

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
import realmbase.event.events.EntityCreateEvent;
import realmbase.event.events.EntityRemoveEvent;
import realmbase.event.events.EntityUpdateEvent;
import realmbase.event.events.PacketReceiveEvent;
import realmbase.event.events.PortalNewEvent;
import realmbase.event.events.PortalUpdateEvent;
import realmbase.packets.Packet;
import realmbase.packets.server.Create_SuccessPacket;
import realmbase.packets.server.FailurePacket;
import realmbase.packets.server.NewTickPacket;
import realmbase.packets.server.UpdatePacket;
import realmbase.xml.GetXml;
import realmbase.xml.datas.EnemyData;
public class PacketListener implements EventListener{

	@Getter
	private static final HashMap<Client,HashMap<Integer,EntityData>> entities = new HashMap<Client, HashMap<Integer,EntityData>>();
	
	public PacketListener(){
		EventManager.register(this);
	}
	
	public static void clear(Client client){
		if(entities.containsKey(client)) 
			for(EntityData e : entities.get(client).values())
				EventManager.callEvent(new EntityRemoveEvent(e));
		
		entities.remove(client);
	}
	
	public static PlayerData getPlayerData(Client client, int id){
		if(entities.containsKey(client)){
			EntityData data;
			Object[] datas = entities.get(client).values().toArray();
			for(int i = 0; i < datas.length; i++){
				data = (EntityData) datas[i];
				if(data instanceof PlayerData && 
						id == data.getStatus().getObjectId())return ((PlayerData)data);
			}
		}
		return null;
	}
	
	public static PlayerData getPlayerData(Client client, String name){
		if(entities.containsKey(client)){
			EntityData data;
			Object[] datas = entities.get(client).values().toArray();
			for(int i = 0; i < datas.length; i++){
				data = (EntityData) datas[i];
				if(data instanceof PlayerData && 
						data.getName().equalsIgnoreCase(name))return ((PlayerData)data);
			}
		}
		return null;
	}
	
	public static HashMap<Integer,EntityData> getClone(Client client){
		return (HashMap<Integer,EntityData>) entities.get(client).clone();
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
		
		if(!entities.containsKey(client))entities.put(client, new HashMap<Integer,EntityData>());
		
		if(packet.getId() == GetXml.packetMapName.get("UPDATE")){
			UpdatePacket upacket = (UpdatePacket)packet;
			for(int i = 0; i < upacket.getNewObjs().length ; i++){
				EntityData e = upacket.getNewObjs()[i];
				EventManager.callEvent(new EntityCreateEvent(e));
				
				if(GetXml.packetMapName.containsKey(Integer.valueOf(e.getObjectType()))
						&& ((EnemyData)GetXml.objectMap.get(Integer.valueOf(e.getObjectType()))).player){
					PlayerData player = (PlayerData) e;
					entities.get(client).put(e.getStatus().getObjectId(), player);
					RealmBase.println("add Player Status "+player.getName());
					
				}else if(GetXml.objectMap.containsKey(Integer.valueOf(e.getObjectType()))
						&& ((EnemyData)GetXml.objectMap.get(Integer.valueOf(e.getObjectType()))).portal){
					PortalData portal = (PortalData) e;
					entities.get(client).put(e.getStatus().getObjectId(), portal);
					EventManager.callEvent(new PortalNewEvent(portal,client));
				}else{
					entities.get(client).put(e.getStatus().getObjectId(), e);
				}
			}
			
			for(int i = 0; i < upacket.getDrops().length ; i++) {
				EventManager.callEvent(new EntityRemoveEvent(entities.get(client).get(upacket.getDrops()[i])));
				entities.get(client).remove(upacket.getDrops()[i]);
			}
		}else if(packet.getId() == GetXml.packetMapName.get("NEWTICK")){
			NewTickPacket tpacket = (NewTickPacket)packet;
			
			Status e;
			HashMap<Integer,EntityData> list;
			for(int i = 0; i < tpacket.getStatuses().length ; i++){
				e = tpacket.getStatuses()[i];
				list = get(client);
				
				if(list.containsKey(e.getObjectId())){
					EntityData data = list.get(e.getObjectId());
					
					data.setStatus(e);
					EventManager.callEvent(new EntityUpdateEvent(data));
					
					if(data instanceof PortalData){
						((PortalData) data).loadStat();
						EventManager.callEvent(new PortalUpdateEvent(((PortalData) data),client));
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
