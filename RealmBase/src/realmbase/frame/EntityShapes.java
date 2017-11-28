package realmbase.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import realmbase.data.EntityData;
import realmbase.data.Location;
import realmbase.data.PlayerData;
import realmbase.event.EventHandler;
import realmbase.event.EventListener;
import realmbase.event.EventManager;
import realmbase.event.events.EntityCreateEvent;
import realmbase.event.events.EntityRemoveEvent;
import realmbase.event.events.EntityUpdateEvent;

public class EntityShapes extends JPanel implements EventListener{
	
	private ClientsFrame frame;
	private ArrayList<EntityData> entities;
	
	public EntityShapes(ClientsFrame frame) {
		this.frame=frame;
		this.entities=new ArrayList<>();
		this.frame.add(this);
		EventManager.register(this);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Location pos;
        for(int i = 0; i < entities.size(); i++) {
        	pos=entities.get(i).getStatus().getPosition();

        	if(pos.x!=0 && pos.y!=0) {
        		g.setColor(Color.BLUE);
            	g.fillOval((int)pos.x, (int)pos.y, 10, 10);
            	
        	}
        }
    }
	
	@EventHandler
	public void update(EntityUpdateEvent ev) {
		if(this.entities.contains(ev.getEntity())) {
			frame.repaint();
		}
	}
	
	@EventHandler
	public void delete(EntityRemoveEvent ev) {
		this.entities.remove(ev.getEntity());
	}
	
	@EventHandler
	public void create(EntityCreateEvent ev) {
		if(!this.entities.contains(ev.getEntity())) {
			if(ev.getEntity() instanceof PlayerData) {
				this.entities.add(ev.getEntity());
			}
		}
	}
}

