package thefloydman.linkingbooks.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import thefloydman.linkingbooks.util.BlockNames;

public class ItemBookDisplay extends ItemBlock {

	public ItemBookDisplay(Block blockIn, Properties builder) {
		super(blockIn, builder);
		this.setRegistryName(BlockNames.BOOK_DISPLAY);
	}

}
