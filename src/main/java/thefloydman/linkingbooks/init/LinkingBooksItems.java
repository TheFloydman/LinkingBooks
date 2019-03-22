package thefloydman.linkingbooks.init;

import java.util.Random;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import thefloydman.linkingbooks.item.ItemLinkingBookBlank;
import thefloydman.linkingbooks.item.ItemLinkingBookCover;
import thefloydman.linkingbooks.item.ItemLinkingBookWritten;
import thefloydman.linkingbooks.item.ItemLinkingPanel;
import thefloydman.linkingbooks.util.ItemNames;
import thefloydman.linkingbooks.util.LinkingBooksCreativeTabs;
import net.minecraft.item.Item.Properties;

public class LinkingBooksItems {

	public static Properties itemProperties;

	@ObjectHolder(ItemNames.LINKING_BOOK_BLANK_WHITE)
	public static Item linkingBookBlankWhite;

	@ObjectHolder(ItemNames.LINKING_BOOK_BLANK_ORANGE)
	public static Item linkingBookBlankOrange;

	@ObjectHolder(ItemNames.LINKING_BOOK_BLANK_MAGENTA)
	public static Item linkingBookBlankMagenta;

	@ObjectHolder(ItemNames.LINKING_BOOK_BLANK_LIGHT_BLUE)
	public static Item linkingBookBlankLightBlue;

	@ObjectHolder(ItemNames.LINKING_BOOK_BLANK_YELLOW)
	public static Item linkingBookBlankYellow;

	@ObjectHolder(ItemNames.LINKING_BOOK_BLANK_LIME)
	public static Item linkingBookBlankLime;

	@ObjectHolder(ItemNames.LINKING_BOOK_BLANK_PINK)
	public static Item linkingBookBlankPink;

	@ObjectHolder(ItemNames.LINKING_BOOK_BLANK_GRAY)
	public static Item linkingBookBlankGray;

	@ObjectHolder(ItemNames.LINKING_BOOK_BLANK_LIGHT_GRAY)
	public static Item linkingBookBlankLightGray;

	@ObjectHolder(ItemNames.LINKING_BOOK_BLANK_CYAN)
	public static Item linkingBookBlankCyan;

	@ObjectHolder(ItemNames.LINKING_BOOK_BLANK_PURPLE)
	public static Item linkingBookBlankPurple;

	@ObjectHolder(ItemNames.LINKING_BOOK_BLANK_BLUE)
	public static Item linkingBookBlankBlue;

	@ObjectHolder(ItemNames.LINKING_BOOK_BLANK_BROWN)
	public static Item linkingBookBlankBrown;

	@ObjectHolder(ItemNames.LINKING_BOOK_BLANK_GREEN)
	public static Item linkingBookBlankGreen;

	@ObjectHolder(ItemNames.LINKING_BOOK_BLANK_RED)
	public static Item linkingBookBlankRed;

	@ObjectHolder(ItemNames.LINKING_BOOK_BLANK_BLACK)
	public static Item linkingBookBlankBlack;

	@ObjectHolder(ItemNames.LINKING_BOOK_WRITTEN_WHITE)
	public static Item linkingBookWrittenWhite;

	@ObjectHolder(ItemNames.LINKING_BOOK_WRITTEN_ORANGE)
	public static Item linkingBookWrittenOrange;

	@ObjectHolder(ItemNames.LINKING_BOOK_WRITTEN_MAGENTA)
	public static Item linkingBookWrittenMagenta;

	@ObjectHolder(ItemNames.LINKING_BOOK_WRITTEN_LIGHT_BLUE)
	public static Item linkingBookWrittenLightBlue;

	@ObjectHolder(ItemNames.LINKING_BOOK_WRITTEN_YELLOW)
	public static Item linkingBookWrittenYellow;

	@ObjectHolder(ItemNames.LINKING_BOOK_WRITTEN_LIME)
	public static Item linkingBookWrittenLime;

	@ObjectHolder(ItemNames.LINKING_BOOK_WRITTEN_PINK)
	public static Item linkingBookWrittenPink;

	@ObjectHolder(ItemNames.LINKING_BOOK_WRITTEN_GRAY)
	public static Item linkingBookWrittenGray;

	@ObjectHolder(ItemNames.LINKING_BOOK_WRITTEN_LIGHT_GRAY)
	public static Item linkingBookWrittenLightGray;

	@ObjectHolder(ItemNames.LINKING_BOOK_WRITTEN_CYAN)
	public static Item linkingBookWrittenCyan;

	@ObjectHolder(ItemNames.LINKING_BOOK_WRITTEN_PURPLE)
	public static Item linkingBookWrittenPurple;

	@ObjectHolder(ItemNames.LINKING_BOOK_WRITTEN_BLUE)
	public static Item linkingBookWrittenBlue;

	@ObjectHolder(ItemNames.LINKING_BOOK_WRITTEN_BROWN)
	public static Item linkingBookWrittenBrown;

	@ObjectHolder(ItemNames.LINKING_BOOK_WRITTEN_GREEN)
	public static Item linkingBookWrittenGreen;

	@ObjectHolder(ItemNames.LINKING_BOOK_WRITTEN_RED)
	public static Item linkingBookWrittenRed;

	@ObjectHolder(ItemNames.LINKING_BOOK_WRITTEN_BLACK)
	public static Item linkingBookWrittenBlack;

	@ObjectHolder(ItemNames.LINKING_BOOK_COVER_WHITE)
	public static Item linkingBookCoverWhite;

	@ObjectHolder(ItemNames.LINKING_BOOK_COVER_ORANGE)
	public static Item linkingBookCoverOrange;

	@ObjectHolder(ItemNames.LINKING_BOOK_COVER_MAGENTA)
	public static Item linkingBookCoverMagenta;

	@ObjectHolder(ItemNames.LINKING_BOOK_COVER_LIGHT_BLUE)
	public static Item linkingBookCoverLightBlue;

	@ObjectHolder(ItemNames.LINKING_BOOK_COVER_YELLOW)
	public static Item linkingBookCoverYellow;

	@ObjectHolder(ItemNames.LINKING_BOOK_COVER_LIME)
	public static Item linkingBookCoverLime;

	@ObjectHolder(ItemNames.LINKING_BOOK_COVER_PINK)
	public static Item linkingBookCoverPink;

	@ObjectHolder(ItemNames.LINKING_BOOK_COVER_GRAY)
	public static Item linkingBookCoverGray;

	@ObjectHolder(ItemNames.LINKING_BOOK_COVER_LIGHT_GRAY)
	public static Item linkingBookCoverLightGray;

	@ObjectHolder(ItemNames.LINKING_BOOK_COVER_CYAN)
	public static Item linkingBookCoverCyan;

	@ObjectHolder(ItemNames.LINKING_BOOK_COVER_PURPLE)
	public static Item linkingBookCoverPurple;

	@ObjectHolder(ItemNames.LINKING_BOOK_COVER_BLUE)
	public static Item linkingBookCoverBlue;

	@ObjectHolder(ItemNames.LINKING_BOOK_COVER_BROWN)
	public static Item linkingBookCoverBrown;

	@ObjectHolder(ItemNames.LINKING_BOOK_COVER_GREEN)
	public static Item linkingBookCoverGreen;

	@ObjectHolder(ItemNames.LINKING_BOOK_COVER_RED)
	public static Item linkingBookCoverRed;

	@ObjectHolder(ItemNames.LINKING_BOOK_COVER_BLACK)
	public static Item linkingBookCoverBlack;

	@ObjectHolder(ItemNames.LINKING_PANEL)
	public static Item linkingPanel;

	public static void registerItems(final RegistryEvent.Register<Item> event) {

		IForgeRegistry<Item> itemRegistry = event.getRegistry();

		itemProperties = new Properties().group(LinkingBooksCreativeTabs.LINKING_BOOKS).maxStackSize(16).setNoRepair();
		itemRegistry.register(new ItemLinkingBookCover(itemProperties, EnumDyeColor.WHITE));
		itemRegistry.register(new ItemLinkingBookCover(itemProperties, EnumDyeColor.ORANGE));
		itemRegistry.register(new ItemLinkingBookCover(itemProperties, EnumDyeColor.MAGENTA));
		itemRegistry.register(new ItemLinkingBookCover(itemProperties, EnumDyeColor.LIGHT_BLUE));
		itemRegistry.register(new ItemLinkingBookCover(itemProperties, EnumDyeColor.YELLOW));
		itemRegistry.register(new ItemLinkingBookCover(itemProperties, EnumDyeColor.LIME));
		itemRegistry.register(new ItemLinkingBookCover(itemProperties, EnumDyeColor.PINK));
		itemRegistry.register(new ItemLinkingBookCover(itemProperties, EnumDyeColor.GRAY));
		itemRegistry.register(new ItemLinkingBookCover(itemProperties, EnumDyeColor.LIGHT_GRAY));
		itemRegistry.register(new ItemLinkingBookCover(itemProperties, EnumDyeColor.CYAN));
		itemRegistry.register(new ItemLinkingBookCover(itemProperties, EnumDyeColor.PURPLE));
		itemRegistry.register(new ItemLinkingBookCover(itemProperties, EnumDyeColor.BLUE));
		itemRegistry.register(new ItemLinkingBookCover(itemProperties, EnumDyeColor.BROWN));
		itemRegistry.register(new ItemLinkingBookCover(itemProperties, EnumDyeColor.GREEN));
		itemRegistry.register(new ItemLinkingBookCover(itemProperties, EnumDyeColor.RED));
		itemRegistry.register(new ItemLinkingBookCover(itemProperties, EnumDyeColor.BLACK));

		itemProperties = new Properties().group(LinkingBooksCreativeTabs.LINKING_BOOKS).maxStackSize(1).setNoRepair();
		itemRegistry.register(new ItemLinkingBookBlank(itemProperties, EnumDyeColor.WHITE));
		itemRegistry.register(new ItemLinkingBookBlank(itemProperties, EnumDyeColor.ORANGE));
		itemRegistry.register(new ItemLinkingBookBlank(itemProperties, EnumDyeColor.MAGENTA));
		itemRegistry.register(new ItemLinkingBookBlank(itemProperties, EnumDyeColor.LIGHT_BLUE));
		itemRegistry.register(new ItemLinkingBookBlank(itemProperties, EnumDyeColor.YELLOW));
		itemRegistry.register(new ItemLinkingBookBlank(itemProperties, EnumDyeColor.LIME));
		itemRegistry.register(new ItemLinkingBookBlank(itemProperties, EnumDyeColor.PINK));
		itemRegistry.register(new ItemLinkingBookBlank(itemProperties, EnumDyeColor.GRAY));
		itemRegistry.register(new ItemLinkingBookBlank(itemProperties, EnumDyeColor.LIGHT_GRAY));
		itemRegistry.register(new ItemLinkingBookBlank(itemProperties, EnumDyeColor.CYAN));
		itemRegistry.register(new ItemLinkingBookBlank(itemProperties, EnumDyeColor.PURPLE));
		itemRegistry.register(new ItemLinkingBookBlank(itemProperties, EnumDyeColor.BLUE));
		itemRegistry.register(new ItemLinkingBookBlank(itemProperties, EnumDyeColor.BROWN));
		itemRegistry.register(new ItemLinkingBookBlank(itemProperties, EnumDyeColor.GREEN));
		itemRegistry.register(new ItemLinkingBookBlank(itemProperties, EnumDyeColor.RED));
		itemRegistry.register(new ItemLinkingBookBlank(itemProperties, EnumDyeColor.BLACK));

		itemProperties = new Properties().maxStackSize(1).setNoRepair();
		itemRegistry.register(new ItemLinkingBookWritten(itemProperties, EnumDyeColor.WHITE));
		itemRegistry.register(new ItemLinkingBookWritten(itemProperties, EnumDyeColor.ORANGE));
		itemRegistry.register(new ItemLinkingBookWritten(itemProperties, EnumDyeColor.MAGENTA));
		itemRegistry.register(new ItemLinkingBookWritten(itemProperties, EnumDyeColor.LIGHT_BLUE));
		itemRegistry.register(new ItemLinkingBookWritten(itemProperties, EnumDyeColor.YELLOW));
		itemRegistry.register(new ItemLinkingBookWritten(itemProperties, EnumDyeColor.LIME));
		itemRegistry.register(new ItemLinkingBookWritten(itemProperties, EnumDyeColor.PINK));
		itemRegistry.register(new ItemLinkingBookWritten(itemProperties, EnumDyeColor.GRAY));
		itemRegistry.register(new ItemLinkingBookWritten(itemProperties, EnumDyeColor.LIGHT_GRAY));
		itemRegistry.register(new ItemLinkingBookWritten(itemProperties, EnumDyeColor.CYAN));
		itemRegistry.register(new ItemLinkingBookWritten(itemProperties, EnumDyeColor.PURPLE));
		itemRegistry.register(new ItemLinkingBookWritten(itemProperties, EnumDyeColor.BLUE));
		itemRegistry.register(new ItemLinkingBookWritten(itemProperties, EnumDyeColor.BROWN));
		itemRegistry.register(new ItemLinkingBookWritten(itemProperties, EnumDyeColor.GREEN));
		itemRegistry.register(new ItemLinkingBookWritten(itemProperties, EnumDyeColor.RED));
		itemRegistry.register(new ItemLinkingBookWritten(itemProperties, EnumDyeColor.BLACK));

		itemProperties = new Properties().group(LinkingBooksCreativeTabs.LINKING_BOOKS).maxStackSize(64).setNoRepair();
		itemRegistry.register(new ItemLinkingPanel(itemProperties));

	}

	public static Item getRandomWrittenLinkingBook(Random rand) {
		switch (rand.nextInt(16)) {
		case 0:
			return linkingBookWrittenWhite;
		case 1:
			return linkingBookWrittenOrange;
		case 2:
			return linkingBookWrittenMagenta;
		case 3:
			return linkingBookWrittenLightBlue;
		case 4:
			return linkingBookWrittenYellow;
		case 5:
			return linkingBookWrittenLime;
		case 6:
			return linkingBookWrittenPink;
		case 7:
			return linkingBookWrittenGray;
		case 8:
			return linkingBookWrittenLightGray;
		case 9:
			return linkingBookWrittenCyan;
		case 10:
			return linkingBookWrittenPurple;
		case 11:
			return linkingBookWrittenBlue;
		case 12:
			return linkingBookWrittenBrown;
		case 13:
			return linkingBookWrittenGreen;
		case 14:
			return linkingBookWrittenRed;
		case 15:
			return linkingBookWrittenBlack;
		default:
			return linkingBookWrittenGreen;
		}
	}

	public static Item getRandomBlankLinkingBook(Random rand) {
		switch (rand.nextInt(16)) {
		case 0:
			return linkingBookBlankWhite;
		case 1:
			return linkingBookBlankOrange;
		case 2:
			return linkingBookBlankMagenta;
		case 3:
			return linkingBookBlankLightBlue;
		case 4:
			return linkingBookBlankYellow;
		case 5:
			return linkingBookBlankLime;
		case 6:
			return linkingBookBlankPink;
		case 7:
			return linkingBookBlankGray;
		case 8:
			return linkingBookBlankLightGray;
		case 9:
			return linkingBookBlankCyan;
		case 10:
			return linkingBookBlankPurple;
		case 11:
			return linkingBookBlankBlue;
		case 12:
			return linkingBookBlankBrown;
		case 13:
			return linkingBookBlankGreen;
		case 14:
			return linkingBookBlankRed;
		case 15:
			return linkingBookBlankBlack;
		default:
			return linkingBookBlankGreen;
		}
	}
}
