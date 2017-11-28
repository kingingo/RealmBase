package realmbase.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import javax.swing.JPanel;
import realmbase.Client;
import realmbase.RealmBase;
import realmbase.data.Location;
import realmbase.event.EventHandler;
import realmbase.event.EventListener;
import realmbase.event.EventManager;
import realmbase.event.events.ClientDisconnectEvent;
import realmbase.event.events.ClientMoveEvent;

public class ClientShapes extends JPanel implements EventListener{
	private static ClientShapes instance;
	
	public static void add(Client client) {
		instance.clients.put(client,new Location());
	}
	
	private ClientsFrame frame;
	private HashMap<Client,Location> clients;
	
	public ClientShapes(ClientsFrame frame) {
		this.frame=frame;
		this.clients=new HashMap<Client,Location>();
		this.frame.add(this);
		EventManager.register(this);
		this.instance=this;
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Location pos;
        for(Client client : clients.keySet()) {
        	pos=clients.get(client);

        	if(pos.x!=0 && pos.y!=0) {
        		g.setColor(Color.RED);
            	g.fillOval((int)pos.x, (int)pos.y, 20, 20);
            	
        	}
        }
    }
	
	@EventHandler
	public void disconnect(ClientDisconnectEvent ev) {
		this.clients.remove(ev.getClient());
	}
	
	@EventHandler
	public void move(ClientMoveEvent ev) {
		if(this.clients.containsKey(ev.getClient())) {
			this.clients.get(ev.getClient()).set(ev.getLocation());
			this.frame.repaint();
		}
	}
	
}
