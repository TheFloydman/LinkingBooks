package thefloydman.linkingbooks.util;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import thefloydman.linkingbooks.init.LinkingBooksItems;

public final class LinkingBooksCreativeTabs {

	private LinkingBooksCreativeTabs() {
	}

	public static final ItemGroup LINKING_BOOKS = new ItemGroup("linking_books") {

		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack createIcon() {
			return new ItemStack(LinkingBooksItems.linkingBookWrittenGreen);
		}
	};
}