package realmbase.event.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import realmbase.Client;
import realmbase.data.Type;
import realmbase.event.Cancellable;
import realmbase.event.Event;
import realmbase.packets.Packet;

@AllArgsConstructor
@Getter
@Setter
public class PacketSendEvent extends Event implements Cancellable{
	private Packet packet;
	private Type type;
	private Client client;
	private boolean cancelled=false;
}
