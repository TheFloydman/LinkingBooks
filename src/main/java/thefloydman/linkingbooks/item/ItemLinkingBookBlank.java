package thefloydman.linkingbooks.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import thefloydman.linkingbooks.util.LinkingBookUtils;
import thefloydman.linkingbooks.util.Reference;

public class ItemLinkingBookBlank extends Item {

	protected EnumDyeColor color;

	public ItemLinkingBookBlank(Item.Properties properties, EnumDyeColor enumColor) {
		super(properties);
		this.color = enumColor;
		this.setRegistryName(Reference.modRL("linking_book_blank_" + this.color.getTranslationKey()));
	}

	// When the unwritten linking book is used in-hand, it turns into a written
	// linking book that records the player's position and rotation at that time.
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {

		ItemStack handStack = player.getHeldItem(hand);

		if (world.isRemote() || handStack.getCount() > 1) {
			return ActionResult.newResult(EnumActionResult.SUCCESS, handStack);
		}

		ItemStack bookStack = LinkingBookUtils.createWrittenLinkingBook(player, this.color);
		// String filename =
		// PanelImageHelper.generateFilename(player.getUniqueID().toString());
		// modTag.setString("filename", filename);
		/*
		 * if (world.isRemote) { Minecraft mc = Minecraft.getInstance(); GameSettings
		 * settings = mc.gameSettings; MainWindow window = mc.mainWindow;
		 * window.setupOverlayRendering(); settings.hideGUI = !settings.hideGUI;
		 * NativeImage image =
		 * PanelImageHelper.createPanelImage(Minecraft.getInstance().getFramebuffer());
		 * settings.hideGUI = !settings.hideGUI;
		 * PanelImageHelper.savePanelImageToClient(image, filename);
		 * LinkingBooksPacketHandler.sendPanelImageToServer(image, filename); }
		 */

		return ActionResult.newResult(EnumActionResult.SUCCESS, bookStack);
	}

}