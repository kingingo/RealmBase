package realmbase;

import java.net.InetSocketAddress;

import realmbase.data.Location;
import realmbase.proxy.ProxyAuth;
import realmbase.xml.GetUrl;

public class Parameter {
	//Version ID: http://www.realmofthemadgod.com/version.txt
	//SWF File: https://realmofthemadgodhrd.appspot.com/AssembleeGameClientVERSIONID.swf
	//          https://realmofthemadgodhrd.appspot.com/AssembleeGameClient1501601788.swf
	
	public static String cipherIn = "72c5583cafb6818995cdd74b80";
	public static String cipherOut = "311f80691451c71d09a13a2a6e";
	public static InetSocketAddress remoteHost = GetUrl.getServerAdresses().get("EuNorth2".toUpperCase());
	
	public static boolean proxy = false;
	public static String proxyHost = "";
	public static int proxyPort = 80;
	public static ProxyAuth proxyAuth  = new ProxyAuth("", "");
	
	public final static String BUILD_VERSION = "X19.1.0";
	public final static Location PORTAL_POS = new Location( (float) 106.94497 , (float) 131.91075);
	public final static double SPEED_MULTIPLIER = 7.462686567164179E-5;
	public final static double SPEED_BASE = 0.0034999999999999996;
	public final static int ATTACK_RATE_BASE = 485;
	public final static double ATTACK_RATE_DEX_MULTIPLIER = 8 + 1 / 3;
	
	public final static int WIZARD = 782;
	
	public interface Modes {
		public final static int MAP_TESTING = -6;
		public final static int VAULT = -5;
		public final static int TUTORIAL_PT2 = -4;
		public final static int REALM = -3;
		public final static int NORMAL = -2;
		public final static int TUTORIAL_PT1 = -1;
	}
}
