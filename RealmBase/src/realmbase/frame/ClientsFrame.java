package realmbase.frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class ClientsFrame extends JFrame{

	public ClientsFrame(){
		super("ROTMG");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,900);
		add(new RJPanel());
		setVisible(true);
		
		new Timer(30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RJPanel.panel.move();
                RJPanel.panel.repaint();
            }
        }).start();
	}
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(900, 900);
    }
	
}
