package realmbase.frame;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import lombok.Getter;
import realmbase.RealmBase;
import realmbase.data.Location;
import realmbase.event.EventHandler;
import realmbase.event.EventListener;
import realmbase.event.EventManager;
import realmbase.event.events.ClientMoveEvent;

public class ClientJPanel extends JPanel implements EventListener{

	@Getter
	private int clientId;
	@Getter
	private Location position;
	private Color color;
	
	public ClientJPanel(Color color, int clientId,Location position){
		this.clientId=clientId;
		this.color=color;
		this.position=position;
		EventManager.register(this);
		
		if(ClientsFrame.getFrame()==null)RealmBase.println("NULL!!!! "+color.toString());
		ClientsFrame.getFrame().add(this);
	}
	
	@EventHandler
	public void receive(ClientMoveEvent ev){
		if(ev.getClient().getClientId() == clientId){
			move(ev.getLocation());
			RealmBase.println("MOVE "+ev.getLocation().x+"/"+ev.getLocation().y);
		}
	}
	
	public void delete(){
		RealmBase.println("DELETE!!!");
		ClientsFrame.getFrame().remove(this);
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
		g.setColor(color);
        g.fillOval((int)getPosition().x, (int)getPosition().y, 20, 20); 
	}
	
	public void move(Location position){
		this.position=position;
		ClientsFrame.getFrame().repaint();
	}
	
}
