package realmbase.event.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import realmbase.Client;
import realmbase.data.EntityData;
import realmbase.data.Location;
import realmbase.event.Event;

@Getter
@AllArgsConstructor
public class EntityRemoveEvent extends Event{
	private EntityData entity;
}
