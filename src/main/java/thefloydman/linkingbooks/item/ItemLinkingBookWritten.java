package thefloydman.linkingbooks.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import thefloydman.linkingbooks.client.container.ContainerLinkingBook;
import thefloydman.linkingbooks.util.LinkingBookUtils;
import thefloydman.linkingbooks.util.Reference;

public class ItemLinkingBookWritten extends Item implements IInteractionObject {

	public ItemLinkingBookWritten(Item.Properties properties, EnumDyeColor enumDyeColor) {
		super(properties);
		this.setRegistryName(Reference.modRL("linking_book_written_" + enumDyeColor.getTranslationKey()));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {

		NBTTagCompound tag = player.getHeldItem(hand).getTag();

		if (tag == null) {
			Reference.LOGGER.info("Unable to link. Written Linking Book has no NBT data.");
			return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItem(hand));
		}

		final NBTTagCompound subTag = tag.getCompound("LinkingBooksInfo");

		if (subTag == null) {
			Reference.LOGGER.info("Unable to link. Written Linking Book has no mod data.");
			return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItem(hand));
		}

		if (!isNBTValid(subTag)) {
			Reference.LOGGER.info("Unable to link. Written Linking Book is missing some NBT data.");
			return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItem(hand));
		}

		if (!world.isRemote) {
			NetworkHooks.openGui((EntityPlayerMP) player, this, buf -> LinkingBookUtils.createGuiPacketBuffer(subTag, buf, true));
		}

		return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItem(hand));
	}

	protected boolean isNBTValid(NBTTagCompound tag) {

		if (!tag.hasKey("dimension") || !tag.hasKey("x") || !tag.hasKey("y") || !tag.hasKey("z") || !tag.hasKey("rotation")) {
			return false;
		}

		return true;

	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public ITextComponent getCustomName() {
		return null;
	}

	@Override
	public Container createContainer(InventoryPlayer inventory, EntityPlayer player) {
		return new ContainerLinkingBook(player);
	}

	@Override
	public String getGuiID() {
		return Reference.MOD_ID + ":linking_book";
	}

}