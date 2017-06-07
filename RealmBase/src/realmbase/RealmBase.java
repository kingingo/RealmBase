package realmbase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import realmbase.listener.ObjectListener;
import realmbase.packets.Packet;

public class RealmBase {
	private static File file;
	private static FileOutputStream out;
	public static final SimpleDateFormat DATE_FORMAT_NOW = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void println(String prefix, String message){
		System.out.println(prefix+message);
		try {
			out.write((prefix+message+"\n").getBytes() );
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void println(Client client, String message){
		println("["+DATE_FORMAT_NOW.format(Calendar.getInstance().getTime())+" | "+client.getName()+"]: ", message);
	}
	
	public static void println(String message){
		println("["+DATE_FORMAT_NOW.format(Calendar.getInstance().getTime())+" | RealmBase]: ", message);
	}
	
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
		new ObjectListener();
	}
}
