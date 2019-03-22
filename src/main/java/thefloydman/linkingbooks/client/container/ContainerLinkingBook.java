package thefloydman.linkingbooks.client.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerLinkingBook extends Container {
	
	public ContainerLinkingBook(EntityPlayer player) {
		
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

}
