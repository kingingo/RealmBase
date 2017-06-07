package realmbase.packets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import realmbase.data.Item;
import realmbase.packets.Packet;



public class TradeStartPacket extends Packet {
	
	public Item[] myItems = new Item[0];
	public String yourName;
	public Item[] yourItems = new Item[0];

	@Override
	public void parseFromInput(DataInputStream in) throws IOException {
		this.myItems = new Item[in.readShort()];
		for (int i = 0; i < this.myItems.length; i++) {
			Item item = new Item();
			item.parseFromInput(in);
			this.myItems[i] = item;
		}
		this.yourName = in.readUTF();
		this.yourItems = new Item[in.readShort()];
		for (int i = 0; i < this.yourItems.length; i++) {
			Item item = new Item();
			item.parseFromInput(in);
			this.yourItems[i] = item;
		}
	}

	@Override
	public void writeToOutput(DataOutputStream out) throws IOException {
		out.writeShort(this.myItems.length);
		for (Item item: this.myItems) {
			item.writeToOutput(out);
		}
		out.writeUTF(this.yourName);
		out.writeShort(this.yourItems.length);
		for (Item item: this.yourItems) {
			item.writeToOutput(out);
		}
	}

}
