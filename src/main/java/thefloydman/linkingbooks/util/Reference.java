package thefloydman.linkingbooks.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.util.ResourceLocation;

public class Reference {

	public static final String MOD_ID = "linkingbooks";
	public static final Logger LOGGER = LogManager.getLogger();
	public static int tick = 0;
	private static String imageDirectoryServer = null;
	private static String imageDirectoryClient = null;

	public static ResourceLocation modRL(String str) {
		return new ResourceLocation(MOD_ID, str);
	}

	public static String getServerImageDirectory() {
		return imageDirectoryServer;
	}

	public static void setServerImageDirectory(String str) {
		imageDirectoryServer = str;
	}

	public static String getClientImageDirectory() {
		return imageDirectoryClient;
	}

	public static void setClientImageDirectory(String str) {
		imageDirectoryClient = str;
	}
}
