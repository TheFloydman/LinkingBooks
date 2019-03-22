package thefloydman.linkingbooks.network;

import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import thefloydman.linkingbooks.network.packets.PacketChangeDimension;
import thefloydman.linkingbooks.network.packets.PacketPanelImageToServer;
import thefloydman.linkingbooks.util.Reference;

public class LinkingBooksPacketHandler {

	private static final String PROTOCOL_VERSION = Integer.toString(1);
	private static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder.named(Reference.modRL("main_channel"))
			.clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals)
			.networkProtocolVersion(() -> PROTOCOL_VERSION).simpleChannel();

	public static void register() {
		int index = 0;

		HANDLER.registerMessage(index++, PacketChangeDimension.class, PacketChangeDimension::encode,
				PacketChangeDimension::decode, PacketChangeDimension.Handler::handle);

		HANDLER.registerMessage(index++, PacketPanelImageToServer.class, PacketPanelImageToServer::encode,
				PacketPanelImageToServer::decode, PacketPanelImageToServer.Handler::handle);
	}

	public static void sendLinkRequest(final int dimension, final int x, final int y, final int z, final float rotation,
			final boolean bookInHand) {
		HANDLER.sendToServer(new PacketChangeDimension(dimension, x, y, z, rotation, bookInHand));
	}

	public static void sendPanelImageToServer(NativeImage image, String filename) {
		HANDLER.sendToServer(new PacketPanelImageToServer(image, filename));
	}

}
