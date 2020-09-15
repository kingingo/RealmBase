package realmbase.event.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import realmbase.Client;
import realmbase.data.portal.PortalData;
import realmbase.event.Event;

@AllArgsConstructor
@Getter
public class PortalNewEvent extends Event{
	private PortalData portal;
	private Client client;
}
