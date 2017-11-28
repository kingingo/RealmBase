package realmbase.frame;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import lombok.Getter;
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
	
	public ClientJPanel(int clientId,Location position){
		this.clientId=clientId;
		this.position=position;
		EventManager.register(this);
		ClientsFrame.getFrame().add(this);
	}
	
	@EventHandler
	public void receive(ClientMoveEvent ev){
		if(ev.getClientId() == clientId){
			move(ev.getLocation());
		}
	}
	
	public void delete(){
		ClientsFrame.getFrame().remove(this);
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
		g.setColor(Color.RED);
        g.fillOval((int)getPosition().x, (int)getPosition().y, 20, 20); 
	}
	
	public void move(Location position){
		this.position=position;
		ClientsFrame.getFrame().repaint();
	}
	
}
