package realmbase.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class RJPanel extends JPanel{
	public static RJPanel panel;
	private int y = 900;
    boolean down = false;
	
	public RJPanel() {
		this.panel=this;
	}	
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
		g.setColor(Color.RED);
        g.fillOval(50, y, 20, 20); 
	}
	
	public void move(){
            if (y <= 50) {
                 down = true;
             }

             if (down) {
                 y += 8;
             } else {
                 y -= 8;
             }
         
	}
	
}
