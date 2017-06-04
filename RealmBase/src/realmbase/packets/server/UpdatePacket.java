package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import realmbase.GetXml;
import realmbase.data.Entity;
import realmbase.data.Tile;
import realmbase.packets.Packet;

@Getter
@Setter
public class UpdatePacket extends Packet {
	
	private Tile[] tiles = new Tile[0];
	private Entity[] newObjs = new Entity[0];
	private int[] drops = new int[0];

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.tiles = new Tile[in.readShort()];
		for (int i = 0; i < this.tiles.length; i++) {
			Tile tile = new Tile();
			tile.parseFromInput(in);
			this.tiles[i] = tile;
		}
		this.newObjs = new Entity[in.readShort()];
		for (int i = 0; i < this.newObjs.length; i++) {
			Entity Entity = new Entity();
			Entity.parseFromInput(in);
			this.newObjs[i] = Entity;
		}
		this.drops = new int[in.readShort()];
		for (int i = 0; i < this.drops.length; i++) {
			this.drops[i] = in.readInt();
		}
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeShort(this.tiles.length);
		for (Tile tile: this.tiles) {
			tile.writeToOutput(out);
		}
		out.writeShort(this.newObjs.length);
		for (Entity obj: this.newObjs) {
			obj.writeToOutput(out);
		}
		out.writeShort(this.drops.length);
		for (int drop: this.drops) {
			out.writeInt(drop);
		}
	}
	
	public String[] entityToString(){
		ArrayList<String> list = new ArrayList<>();
		for(Entity e : newObjs){
			if(GetXml.getQuestsMap().containsKey(e.objectType)){
				list.add("Entity: "+GetXml.getQuestsMap().get(e.objectType));
				list.add("      ObjId: "+e.status.objectId);
				list.add("      X: "+e.status.pos.x);
				list.add("      Y: "+e.status.pos.y);
				list.add("      Length: "+e.status.data.length);
			}
		}
		
		if(list.isEmpty())return null;
		return list.toArray(new String[list.size()]);
	}
	
	public String toString(){
		String[] entities = entityToString();
		if(entities!=null) return formatToString(entities);
		return "";
	}

}
