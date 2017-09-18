package realmbase.event.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import realmbase.data.portal.PortalData;
import realmbase.event.Event;
import realmbase.packets.Packet;

@AllArgsConstructor
@Getter
public class PortalNewEvent extends Event{
	private PortalData portal;
}
