package thefloydman.linkingbooks.event;

import java.io.File;
import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.network.NetworkHooks;
import thefloydman.linkingbooks.client.render.RenderLinkingBook;
import thefloydman.linkingbooks.entity.EntityLinkingBook;
import thefloydman.linkingbooks.init.LinkingBooksItems;
import thefloydman.linkingbooks.item.ItemLinkingBookWritten;
import thefloydman.linkingbooks.util.LinkingBookUtils;
import thefloydman.linkingbooks.util.Reference;

@EventBusSubscriber(modid = Reference.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class EventHandlerForge {

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityLinkingBook.class, RenderLinkingBook::new);
	}

	@OnlyIn(Dist.DEDICATED_SERVER)
	@SubscribeEvent
	public static void onItemToss(ItemTossEvent event) {
		ItemStack bookStack = event.getEntityItem().getItem();
		if (bookStack.getItem() instanceof ItemLinkingBookWritten) {
			event.setCanceled(true);
			EntityPlayer player = event.getPlayer();
			World world = event.getEntity().world;
			EntityLinkingBook entity = new EntityLinkingBook(world);
			entity.setBook(bookStack);
			Vec3d lookVec = player.getLookVec();
			entity.setPosition(player.posX + lookVec.x, player.posY + 1.75D + lookVec.y, player.posZ + lookVec.z);
			// entity.setVelocity(lookVec.x / 4, lookVec.y / 4, lookVec.z / 4);
			entity.addVelocity(lookVec.x / 4, lookVec.y / 4, lookVec.z / 4);
			world.spawnEntity(entity);
			event.getEntityItem().remove();
		}
	}

	@SubscribeEvent
	public static void onWorldLoad(WorldEvent.Load event) throws IOException {
		World world = (World) event.getWorld();
		if (Reference.getServerImageDirectory() == null) {
			File file = world.getSaveHandler().getWorldDirectory();
			file = new File(file, "../../linkingbooks/panel_images");
			try {
				file.mkdirs();
				Reference.setServerImageDirectory(file.getCanonicalPath());
			} catch (IOException e) {
				Reference.LOGGER.info("Could not resolve linking panel image directory on server.");
				e.printStackTrace();
			}
		}
	}

	@OnlyIn(Dist.DEDICATED_SERVER)
	@SubscribeEvent
	public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
		Entity entityTarget = event.getTarget();
		if (!(entityTarget instanceof EntityLinkingBook)) {
			return;
		}
		EntityPlayer entityPlayer = event.getEntityPlayer();
		EntityLinkingBook entityBook = (EntityLinkingBook) entityTarget;
		if (event.getHand().equals(EnumHand.MAIN_HAND)) {
			ItemStack bookStack = entityBook.getBook();
			if (entityPlayer.isSneaking()) {
				entityPlayer.addItemStackToInventory(bookStack);
				// Syncs player inventory to client.
				((EntityPlayerMP) (entityPlayer)).sendContainerToPlayer(entityPlayer.inventoryContainer);
				event.getTarget().remove();
			} else {
				NBTTagCompound subTag = bookStack.getTag().getCompound("LinkingBooksInfo");
				NetworkHooks.openGui((EntityPlayerMP) entityPlayer,
						(ItemLinkingBookWritten) LinkingBooksItems.linkingBookWrittenWhite,
						buf -> LinkingBookUtils.createGuiPacketBuffer(subTag, buf, false));
			}
		}
	}

	@SubscribeEvent
	public static void onLivingEntityFall(LivingFallEvent event) {

	}

}
