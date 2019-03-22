package thefloydman.linkingbooks.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import thefloydman.linkingbooks.block.BlockBookDisplay;
import thefloydman.linkingbooks.item.ItemBookDisplay;
import thefloydman.linkingbooks.util.BlockNames;
import thefloydman.linkingbooks.util.LinkingBooksCreativeTabs;

public class LinkingBooksBlocks {

	public static Block.Properties blockProperties;
	public static Item.Properties itemProperties;

	@ObjectHolder(BlockNames.BOOK_DISPLAY)
	public static Block blockBookDisplay;

	@ObjectHolder(BlockNames.BOOK_DISPLAY)
	public static ItemBlock itemBlockBookDisplay;

	public static void registerBlocks(Register<Block> event) {

		IForgeRegistry<Block> blockRegistry = event.getRegistry();

		blockProperties = Block.Properties.create(Material.WOOD).sound(SoundType.WOOD);
		blockRegistry.register(new BlockBookDisplay(blockProperties));

	}

	public static void registerItems(final RegistryEvent.Register<Item> event) {

		IForgeRegistry<Item> itemRegistry = event.getRegistry();

		itemProperties = new Item.Properties().group(LinkingBooksCreativeTabs.LINKING_BOOKS).maxStackSize(16);
		itemRegistry.register(new ItemBookDisplay(LinkingBooksBlocks.blockBookDisplay, itemProperties));

	}

}
