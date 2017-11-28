package realmbase.frame;

import java.awt.Color;

import javax.swing.JFrame;

import lombok.Getter;
import realmbase.data.Location;

public class ClientsFrame extends JFrame{

	public ClientsFrame(){
		super("ROTMG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,600);
		
		new ClientShapes(this);
		new EntityShapes(this);
		setVisible(true);
	}
}
