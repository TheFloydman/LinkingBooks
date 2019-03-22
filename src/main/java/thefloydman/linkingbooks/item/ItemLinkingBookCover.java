package thefloydman.linkingbooks.item;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import thefloydman.linkingbooks.util.Reference;

public class ItemLinkingBookCover extends Item {

	public ItemLinkingBookCover(Item.Properties properties, EnumDyeColor enumColor) {
		super(properties);
		this.setRegistryName(Reference.modRL("linking_book_cover_" + enumColor.getTranslationKey()));
	}

}