package thefloydman.linkingbooks.network.packets;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.function.Supplier;

import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import thefloydman.linkingbooks.util.PanelImageHelper;

public class PacketPanelImageToServer {

	private final NativeImage image;
	private final String filename;

	public PacketPanelImageToServer(final NativeImage image, final String filename) {
		this.image = image;
		this.filename = filename;
	}

	public static void encode(PacketPanelImageToServer msg, PacketBuffer buf) {
		int[] intArray = msg.image.makePixelArray();
		ByteBuffer byteBuffer = ByteBuffer.allocate(intArray.length * 4);
		for (int i = 0; i < intArray.length; i++) {
			byteBuffer.putInt(intArray[i]);
		}
		byte[] byteArray = byteBuffer.array();
		buf.writeByteArray(byteArray).writeString(msg.filename);
	}

	public static PacketPanelImageToServer decode(PacketBuffer buf) {
		byte[] byteArray = buf.readByteArray();
		ByteBuffer byteBuffer = ByteBuffer.allocate(byteArray.length);
		for (int i = 0; i < byteArray.length; i++) {
			byteBuffer.put(byteArray[i]);
		}
		NativeImage nativeImage = null;
		try {
			nativeImage = NativeImage.read(byteBuffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new PacketPanelImageToServer(nativeImage, buf.readString(1024));
	}

	public static class Handler {
		public static void handle(final PacketPanelImageToServer msg, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> PanelImageHelper.savePanelImageToServer(msg.image, msg.filename));
			ctx.get().setPacketHandled(true);
		}
	}

}
