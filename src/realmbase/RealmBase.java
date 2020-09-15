package realmbase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import realmbase.frame.ClientsFrame;
import realmbase.listener.PacketListener;
import realmbase.packets.Packet;
import realmbase.xml.GetUrl;
import realmbase.xml.GetXml;

public class RealmBase {
	private static File file;
	private static FileOutputStream out;
	public static final SimpleDateFormat DATE_FORMAT_NOW = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void println(String prefix, String message){
		System.out.println("["+prefix+"]: "+message);
	}
	
	public static void println(Client client, String message){
		println("["+DATE_FORMAT_NOW.format(Calendar.getInstance().getTime())+" | "+client.getName()+"("+client.getClientId()+")]: ", message);
	}
	
	public static void println(String message){
		println("["+DATE_FORMAT_NOW.format(Calendar.getInstance().getTime())+" | RealmBase]: ", message);
	}
	
//	public static void main(String[] args) {
//		
//		SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//
//        		ClientsFrame frame = new ClientsFrame();
//        		ClientJPanel c;
//        		frame.add(c = new ClientJPanel(Color.RED, 1, new Location(50,50)));
//        		new Timer(30, new ActionListener() {
//					
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						c.move(c.getPosition().clone().add(new Location(2,0)));
//					}
//				}).start();
//            }
//        });
//		
//	}
	
	public static void init(){
		try {
			file = new File("log.txt");
			out = new FileOutputStream(file);
			
			if (!file.exists()) {
			    file.createNewFile();
			}
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		GetXml.parseXMLData();
		GetUrl.loadServer();
		Packet.loadPackets();
		new PacketListener();
//		new ClientsFrame();
		
		PrintStream previous = System.out;   
	    OutputStream outputStreamCombiner =  new OutputStreamCombiner(Arrays.asList(previous, out)); 
	    PrintStream custom = new PrintStream(outputStreamCombiner);
	    System.setOut(custom);
	    System.setErr(custom);
	}
	
	private static class OutputStreamCombiner extends OutputStream {
        private List<OutputStream> outputStreams;

        public OutputStreamCombiner(List<OutputStream> outputStreams) {
            this.outputStreams = outputStreams;
        }

        public void write(int b) throws IOException {
            for (OutputStream os : outputStreams) {
                os.write(b);
            }
        }

        public void flush() throws IOException {
            for (OutputStream os : outputStreams) {
                os.flush();
            }
        }

        public void close() throws IOException {
            for (OutputStream os : outputStreams) {
                os.close();
            }
        }
    }
}

