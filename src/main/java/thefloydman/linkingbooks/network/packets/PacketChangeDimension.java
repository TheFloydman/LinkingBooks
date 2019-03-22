package thefloydman.linkingbooks.network.packets;

import java.util.function.Supplier;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import thefloydman.linkingbooks.world.LinkingBooksTeleporter;
import net.minecraft.world.dimension.DimensionType;

public class PacketChangeDimension {

	private final int dimension;
	private final int x;
	private final int y;
	private final int z;
	private final float rotation;
	private final boolean bookInHand;

	public PacketChangeDimension(final int dimension, final int x, final int y, final int z, final float rotation,
			final boolean bookInHand) {
		this.dimension = dimension;
		this.x = x;
		this.y = y;
		this.z = z;
		this.rotation = rotation;
		this.bookInHand = bookInHand;
	}

	public static void encode(PacketChangeDimension msg, PacketBuffer buf) {
		buf.writeInt(msg.dimension).writeInt(msg.x).writeInt(msg.y).writeInt(msg.z).writeFloat(msg.rotation)
				.writeBoolean(msg.bookInHand);
	}

	public static PacketChangeDimension decode(PacketBuffer buf) {
		return new PacketChangeDimension(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt(), buf.readFloat(),
				buf.readBoolean());
	}

	public static class Handler {
		public static void handle(final PacketChangeDimension msg, Supplier<NetworkEvent.Context> ctx) {
			if (msg.bookInHand == true) {
				ctx.get().enqueueWork(() -> ctx.get().getSender().dropItem(false));
			}
			ctx.get().enqueueWork(() -> ctx.get().getSender().changeDimension(DimensionType.getById(msg.dimension),
					new LinkingBooksTeleporter(new BlockPos(msg.x, msg.y, msg.z), msg.rotation)));
			ctx.get().setPacketHandled(true);
		}
	}

}
