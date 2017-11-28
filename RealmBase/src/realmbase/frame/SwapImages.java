package realmbase.frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

public class SwapImages extends JPanel {
   private static final int TIMER_DELAY = 200;
   private static final String SPRITE_PATH = "http://th02.deviantart.net/"
         + "fs70/PRE/i/2011/169/0/8/blue_player_sprite_sheet_by_resetado-d3j7zba.png";
   public static final int SPRITE_ROWS = 6;
   public static final int SPRITE_COLS = 6;
   public static final int SPRITE_CELLS = 35;

   private JLabel label = new JLabel();
   private List<ImageIcon> iconList = new ArrayList<ImageIcon>();
   private int iconIndex = 0;

   public SwapImages() throws IOException {
      URL imgUrl = new URL(SPRITE_PATH);
      BufferedImage mainImage = ImageIO.read(imgUrl);

      for (int i = 0; i < SPRITE_CELLS; i++) {
         int row = i / SPRITE_COLS;
         int col = i % SPRITE_COLS;
         int x = (int) (((double) mainImage.getWidth() * col) / SPRITE_COLS);
         int y = (int) ((double) (mainImage.getHeight() * row) / SPRITE_ROWS);
         int w = (int) ((double) mainImage.getWidth() / SPRITE_COLS);
         int h = (int) ((double) mainImage.getHeight() / SPRITE_ROWS);
         BufferedImage img = mainImage.getSubimage(x, y, w, h);
         ImageIcon icon = new ImageIcon(img);
         iconList.add(icon);
      }
      add(label);
      label.setIcon(iconList.get(iconIndex));
      new Timer(TIMER_DELAY, new TimerListener()).start();
   }

   private class TimerListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent arg0) {
         iconIndex++;
         iconIndex %= iconList.size();
         label.setIcon(iconList.get(iconIndex));
      }
   }

   private static void createAndShowGui() {
      SwapImages mainPanel = null;
      try {
         mainPanel = new SwapImages();
      } catch (IOException e) {
         e.printStackTrace();
         System.exit(-1);
      }

      JFrame frame = new JFrame("SwapImages");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
}