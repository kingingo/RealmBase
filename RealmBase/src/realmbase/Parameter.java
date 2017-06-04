package realmbase;

import java.net.InetSocketAddress;

public class Parameter {
	public static String cipherIn = "72c5583cafb6818995cdd74b80";
	public static String cipherOut = "311f80691451c71d09a13a2a6e";
	public static InetSocketAddress remoteHost = GetUrl.getServerAdresses().get("USMidWest".toUpperCase());
	
	public static boolean proxy = false;
	public static String proxyHost = "";
	public static int proxyPort = 2050;
	
	public final static double SPEED_MULTIPLIER = 1d / 67d / 200d;
	public final static double SPEED_BASE = 0.7d / 200d;
	public final static int ATTACK_RATE_BASE = 485;
	public final static double ATTACK_RATE_DEX_MULTIPLIER = 8 + 1 / 3;
	
	public interface Modes {
		public final static int MAP_TESTING = -6;
		public final static int VAULT = -5;
		public final static int TUTORIAL_PT2 = -4;
		public final static int REALM = -3;
		public final static int NORMAL = -2;
		public final static int TUTORIAL_PT1 = -1;
	}
}
