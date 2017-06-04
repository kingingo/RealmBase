package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import realmbase.GetXml;
import realmbase.data.EntityData;
import realmbase.data.Tile;
import realmbase.packets.Packet;

@Getter
@Setter
public class UpdatePacket extends Packet {
	
	private Tile[] tiles = new Tile[0];
	private EntityData[] newObjs = new EntityData[0];
	private int[] drops = new int[0];

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.tiles = new Tile[in.readShort()];
		for (int i = 0; i < this.tiles.length; i++) {
			Tile tile = new Tile();
			tile.parseFromInput(in);
			this.tiles[i] = tile;
		}
		this.newObjs = new EntityData[in.readShort()];
		for (int i = 0; i < this.newObjs.length; i++) {
			EntityData e = EntityData.create(in);
			this.newObjs[i] = e;
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
		for (EntityData obj: this.newObjs) {
			obj.writeToOutput(out);
		}
		out.writeShort(this.drops.length);
		for (int drop: this.drops) {
			out.writeInt(drop);
		}
	}
	
	public String[] EntityDataToString(){
		ArrayList<String> list = new ArrayList<>();
		for(EntityData e : newObjs){
			if(GetXml.getQuestsMap().containsKey(e.getObjectType())){
				list.add("EntityData: "+GetXml.getQuestsMap().get(e.getStatus().getObjectId()));
				list.add("      ObjId: "+e.getStatus().getObjectId());
				list.add("      X: "+e.getStatus().getPosition().x);
				list.add("      Y: "+e.getStatus().getPosition().y);
				list.add("      Length: "+e.getStatus().getData().length);
			}
		}
		
		if(list.isEmpty())return null;
		return list.toArray(new String[list.size()]);
	}
	
	public String toString(){
		String[] entities = EntityDataToString();
		if(entities!=null) return formatToString(entities);
		return "";
	}

}
