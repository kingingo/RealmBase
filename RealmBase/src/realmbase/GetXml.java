package realmbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import lombok.Getter;

public class GetXml {
	@Getter
	private static final HashMap<String, String> packetMap = new HashMap<String, String>();
	@Getter
	private static final HashMap<String, Byte> packetMapName = new HashMap<String, Byte>();
	@Getter
	private static final HashMap<Integer, String> questsMap = new HashMap<Integer, String>();
	@Getter
	private static final HashMap<Integer, String> playersMap = new HashMap<Integer, String>();
	@Getter
	private static final HashMap<Integer, String> portalsMap = new HashMap<Integer, String>();
	private static final int XML_OBJECTS = 1;
	private static final int XML_PACKETS = 2;
	
	public static void parseXMLData(){
		File file = new File("xml/");
		if (!file.isDirectory()) {
			file.mkdir();
		}
		try {
			parseXMLtoMap("Packet", XML_PACKETS, "xml/packets.xml");
			parseXMLtoMap("Object", XML_OBJECTS, "xml/objects.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void parseXMLtoMap(String elementTagName, int xmlType, String localFilePath) throws Exception {
		File file = new File(localFilePath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		InputStream in = new FileInputStream(file);
		Document doc = dBuilder.parse(in);
		in.close();
		doc.getDocumentElement().normalize();
		NodeList nodeList = doc.getElementsByTagName(elementTagName);
		xmlToMap(nodeList, xmlType);
	}
	
	private static void xmlToMap(NodeList node, int xmlType) {
		NodeList nodeList = null;
		for (int j = 0; j < node.getLength(); j++) {
			Element el = (Element) node.item(j);
			if (xmlType == XML_PACKETS) {
				String name = el.getAttribute("id").replace(" ", "").toUpperCase();
				String id = el.getAttribute("type");
				packetMap.put(id, name);
				packetMapName.put(name, Byte.valueOf(id));
			}else if(xmlType == XML_OBJECTS){
				if (el.getElementsByTagName("Quest").getLength() > 0) {
					questsMap.put(Integer.decode(el.getAttribute("type")), el.getAttribute("id"));
				}else if (el.getElementsByTagName("Player").getLength() > 0) {
					playersMap.put(Integer.decode(el.getAttribute("type")), el.getAttribute("id"));
				}else if ((nodeList = el.getElementsByTagName("Class")).getLength() > 0) {
					if(nodeList.item(0).getTextContent().equalsIgnoreCase("Portal")){
						portalsMap.put(Integer.decode(el.getAttribute("type")), el.getAttribute("id"));
					}
				}
			}
		}
	}
	
}
