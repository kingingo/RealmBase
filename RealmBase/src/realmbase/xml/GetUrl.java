package realmbase.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import lombok.Getter;
import realmbase.configuration.ConfigurationSection;
import realmbase.configuration.InvalidConfigurationException;
import realmbase.configuration.file.YamlConfiguration;
import realmbase.data.AccountData;
import realmbase.data.AccountData.Char;
import realmbase.utils.UtilFile;

public class GetUrl {
	@Getter
	private static final HashMap<String, InetSocketAddress> serverAdresses = new HashMap<String, InetSocketAddress>();
	private static final String LINK = "http://realmofthemadgodhrd.appspot.com/char/list";
	private static final File ACCOUNT_PATH = new File("accountdatas");
	
	public static AccountData loadAccountData(String username){
		if(!ACCOUNT_PATH.exists())ACCOUNT_PATH.mkdirs();
		File file = new File(ACCOUNT_PATH,username);
		
		if(file.exists()){
			try {
				YamlConfiguration config = new YamlConfiguration(file);
				config.load(file);
				
				AccountData data = new AccountData();
				
				if( !((System.currentTimeMillis()-Long.valueOf( UtilFile.creationTime(file).toMillis() )) > 1000*60*60*24*7*2) ){
					data.setName(config.getString("AccountData.name"));
					Set<String> list = config.getConfigurationSection("AccountData.charakter.").getKeys(false);
					ArrayList<AccountData.Char> charList = new ArrayList<>();
					
					data.setCharakters(new Char[list.size()]);
					for(String id : list){
	    	        	Char c = data.new Char();
						c.id=id;
						c.type=config.getInt("AccountData.charakter."+id);
						charList.add(c);
					}
			        data.setCharakters(charList.toArray(new AccountData.Char[charList.size()]));
					
					return data;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static void saveAccountData(String username, AccountData data){
		if(!ACCOUNT_PATH.exists())ACCOUNT_PATH.mkdirs();
		File file = new File(ACCOUNT_PATH,username);
		file.delete();
		YamlConfiguration config = new YamlConfiguration(file);
		
		config.set("AccountData.name", data.getName());
		for(Char c : data.getCharakters()){
			config.set("AccountData.charakter."+c.id, c.type);
		}
		config.save();
	}
	
	public static AccountData loadAccount(String username,String password){
		try {
			AccountData account = loadAccountData(username);
			if(account != null)return account;
			
	        NodeList descNodes = openConnection(username, password);
	        account = new AccountData();
	        ArrayList<AccountData.Char> list = new ArrayList<>();
	        
			NodeList nodeList = null;
	        for(int i=0; i<descNodes.getLength();i++){
	        	Element e = (Element) descNodes.item(i);
	        	
	        	if(account.getName().isEmpty() && (nodeList=e.getElementsByTagName("Account")).getLength()>0){
	        		NodeList nl = e.getElementsByTagName("Account");
	        		
	        		for(int j=0; j<nl.getLength(); j++){
	    	        	Element ej = (Element) nl.item(j);
	    	        	account.setName(ej.getElementsByTagName("Name").item(0).getTextContent());
	    	        	break;
	        		}
	        	}
	        	if((nodeList=e.getElementsByTagName("Char")).getLength()>0){
	        		NodeList nl = e.getElementsByTagName("Char");
	        		
	        		for(int j=0; j<nl.getLength(); j++){
	    	        	Element ej = (Element) nl.item(j);
	    	        	Char obj = account.new Char();
	    	        	
	    	        	obj.type=Integer.valueOf(ej.getElementsByTagName("ObjectType").item(0).getTextContent());
	    	        	obj.id=ej.getAttribute("id");
	    	        	list.add(obj);
	        		}
	        	}
	        }
	        
	        account.setCharakters(list.toArray(new AccountData.Char[list.size()]));
	        saveAccountData(username, account);
	        return account;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void saveServerData(){
		File file = new File("server");
		file.delete();
		YamlConfiguration config = new YamlConfiguration(file);
		
		for(String name : serverAdresses.keySet()){
			config.set("Server."+name, serverAdresses.get(name).getHostString()+":"+serverAdresses.get(name).getPort());
		}
		config.save();
	}
	
	public static boolean loadServerData(){
		File file = new File("server");
		
		if(file.exists()){
			
			try {
				YamlConfiguration config = new YamlConfiguration(file);
				config.load(file);
				
				if( !((System.currentTimeMillis()-Long.valueOf( UtilFile.creationTime(file).toMillis() )) > 1000*60*60*24*7*2) ){
					Set<String> list = config.getConfigurationSection("Server.").getKeys(false);
					
					for(String server : list){
						String ip = config.getString("Server."+server).split(":")[0];
						int port = Integer.valueOf(config.getString("Server."+server).split(":")[1]);
						
	    	        	serverAdresses.put(server, new InetSocketAddress(ip, port));
					}
					return true;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static void loadServer() {
		if(loadServerData())return;
		
		try {
	        NodeList descNodes = openConnection();

			NodeList nodeList = null;
	        for(int i=0; i<descNodes.getLength();i++){
	        	Element e = (Element) descNodes.item(i);
	        	
	        	if((nodeList=e.getElementsByTagName("Server")).getLength()>0){
	        		NodeList nl = e.getElementsByTagName("Server");
	        		
	        		for(int j=0; j<nl.getLength(); j++){
	    	        	Element ej = (Element) nl.item(j);
	    	        	
	    	        	serverAdresses.put(ej.getElementsByTagName("Name").item(0).getTextContent().toUpperCase(), new InetSocketAddress(ej.getElementsByTagName("DNS").item(0).getTextContent(), 2050));
	        		}
	        		
	        		saveServerData();
	        	}
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static NodeList openConnection(){
		return openConnection("","");
	}
	
	
	private static NodeList openConnection(String username,String password){
		try {
			URL url = new URL( (username.isEmpty() ? LINK : LINK+"?guid="+username+"&password="+password) );
			URLConnection connection = url.openConnection();
			Document doc = parseXML(connection.getInputStream());
	        NodeList descNodes = doc.getElementsByTagName("Chars");
	        
	        return descNodes;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Document parseXML(InputStream stream) throws Exception{
		DocumentBuilderFactory objDocumentBuilderFactory = null;
		DocumentBuilder objDocumentBuilder = null;
		Document doc = null;
		
		try{
			objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
		    objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
		    doc = objDocumentBuilder.parse(stream);
		}catch(Exception ex){
			throw ex;
		}
		return doc;
	}
}
