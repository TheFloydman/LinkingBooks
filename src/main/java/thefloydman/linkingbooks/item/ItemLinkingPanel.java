package thefloydman.linkingbooks.item;

import net.minecraft.item.Item;
import thefloydman.linkingbooks.util.Reference;

public class ItemLinkingPanel extends Item {

	public ItemLinkingPanel(Item.Properties properties) {
		super(properties);
		this.setRegistryName(Reference.modRL("linking_panel"));
	}

}