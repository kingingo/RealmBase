package realmbase.event.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import realmbase.Client;
import realmbase.data.Location;
import realmbase.event.Event;

@Getter
@AllArgsConstructor
public class ClientMoveEvent extends Event{
	private int clientId;
	private Location location;
	
}
