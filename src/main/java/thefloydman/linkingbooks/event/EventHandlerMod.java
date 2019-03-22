package thefloydman.linkingbooks.event;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import thefloydman.linkingbooks.init.LinkingBooksBlocks;
import thefloydman.linkingbooks.init.LinkingBooksEntityTypes;
import thefloydman.linkingbooks.init.LinkingBooksFluids;
import thefloydman.linkingbooks.init.LinkingBooksItems;
import thefloydman.linkingbooks.init.LinkingBooksTileEntityTypes;
import thefloydman.linkingbooks.tileentity.TileEntityBookDisplay;
import thefloydman.linkingbooks.util.Reference;
import thefloydman.linkingbooks.util.TileEntityNames;

@EventBusSubscriber(modid = Reference.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class EventHandlerMod {

	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		LinkingBooksBlocks.registerBlocks(event);
		LinkingBooksFluids.registerFluids();
	}

	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		LinkingBooksItems.registerItems(event);
		LinkingBooksBlocks.registerItems(event);
	}

	@SubscribeEvent
	public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
		LinkingBooksTileEntityTypes.registerTileEntityType(event.getRegistry(),
				LinkingBooksTileEntityTypes.register("book_display", TileEntityType.Builder.create(TileEntityBookDisplay::new)),
				TileEntityNames.BOOK_DISPLAY);
	}

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().register(LinkingBooksEntityTypes.LINKING_BOOK);
	}

}
