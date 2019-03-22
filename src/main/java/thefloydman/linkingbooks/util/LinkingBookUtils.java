package thefloydman.linkingbooks.util;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.registries.ForgeRegistries;

public class LinkingBookUtils {

	/**
	 * Returns a PacketBuffer that can be used when making a call to open a Linking
	 * Book GUI.
	 * 
	 * @param nbt
	 *            The NBTTagCompound containing all the linking information. Usually
	 *            called "LinkingBooksInfo".
	 * @param buf
	 *            The PacketBuffer that will be written to.
	 * @return A PacketBuffer with all the linking information.
	 */
	public static PacketBuffer createGuiPacketBuffer(NBTTagCompound nbt, PacketBuffer buf, boolean inHand) {
		buf.writeInt(nbt.getInt("dimension"));
		buf.writeInt(nbt.getInt("x"));
		buf.writeInt(nbt.getInt("y"));
		buf.writeInt(nbt.getInt("z"));
		buf.writeFloat(nbt.getFloat("rotation"));
		buf.writeBoolean(inHand);
		return buf;
	}

	public static NBTTagCompound createLinkingBookTag(@Nonnull int dimension, @Nonnull int x, @Nonnull int y,
			@Nonnull int z, @Nonnull float rotation) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInt("dimension", dimension);
		tag.setInt("x", x);
		tag.setInt("y", y);
		tag.setInt("z", z);
		tag.setFloat("rotation", rotation);
		return tag;
	}

	public static NBTTagCompound createLinkingBookTag(@Nonnull EntityPlayer player) {

		int dimension = player.dimension.getId();
		BlockPos pos = player.getPosition();
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		float rotation = player.rotationYaw;

		return createLinkingBookTag(dimension, x, y, z, rotation);
	}

	public static ItemStack createWrittenLinkingBook(EntityPlayer player, EnumDyeColor color) {
		ItemStack bookStack = new ItemStack(
				ForgeRegistries.ITEMS.getValue(Reference.modRL("linking_book_written_" + color.getTranslationKey())));
		NBTTagCompound tag = bookStack.getOrCreateTag();
		NBTTagCompound modTag = createLinkingBookTag(player);
		tag.setTag("LinkingBooksInfo", modTag);
		bookStack.setTag(tag);
		return bookStack;
	}

}
