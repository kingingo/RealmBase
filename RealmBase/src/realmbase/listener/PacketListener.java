package realmbase.listener;

import realmbase.Client;
import realmbase.data.Type;
import realmbase.packets.Packet;

public interface PacketListener {
	public boolean onReceive(Client client, Packet packet,Type from);
	public boolean onSend(Client client, Packet packet, Type to);
}
