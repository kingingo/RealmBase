package realmbase;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import lombok.Getter;
import realmbase.data.AccountData;
import realmbase.data.AccountData.Char;

public class GetUrl {
	@Getter
	private static final HashMap<String, InetSocketAddress> serverAdresses = new HashMap<String, InetSocketAddress>();
	private static final String LINK = "http://realmofthemadgodhrd.appspot.com/char/list";
	
	public static AccountData loadAccount(String username,String password){
		try {
	        NodeList descNodes = openConnection(username, password);
	        AccountData account = new AccountData();
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
	        return account;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void loadServer() {
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
