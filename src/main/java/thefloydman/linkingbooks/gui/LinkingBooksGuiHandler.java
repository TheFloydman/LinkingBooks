package thefloydman.linkingbooks.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.network.FMLPlayMessages;
import thefloydman.linkingbooks.client.gui.GuiLinkingBook;
import thefloydman.linkingbooks.util.Reference;

public class LinkingBooksGuiHandler {

	public static Container getServerGuiElement(FMLPlayMessages.OpenContainer msg) {
		return null;
	}

	public static GuiScreen getClientGuiElement(FMLPlayMessages.OpenContainer msg) {
		
		if (msg.getId().equals(Reference.modRL("linking_book"))) {
			return new GuiLinkingBook(msg.getAdditionalData());
		}
		return null;
	}

}
