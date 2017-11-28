package realmbase.frame;

import java.awt.Color;

import javax.swing.JFrame;

import lombok.Getter;
import realmbase.data.Location;

public class ClientsFrame extends JFrame{
	@Getter
	private static ClientsFrame frame;

	public ClientsFrame(){
		super("ROTMG");
		this.frame=this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,600);
		setVisible(true);
		add(new ClientJPanel(Color.BLACK, 10000, new Location(50, 50)));
	}
	
}
