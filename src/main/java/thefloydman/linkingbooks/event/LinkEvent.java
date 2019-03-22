package thefloydman.linkingbooks.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.eventbus.api.Event;

/**
 * Fired after a link request has been made but before the player has changed
 * dimensions.
 * 
 * @author Floydman
 */
public class LinkEvent extends Event {

	private final EntityPlayer player;
	private final int originDimension;
	private final int destinationDimension;
	private final BlockPos destinationPos;
	private final float destinationRotation;

	public LinkEvent(EntityPlayer entityPlayer, int originDim, int destDim, BlockPos pos, float rotation) {
		this.player = entityPlayer;
		this.originDimension = originDim;
		this.destinationDimension = destDim;
		this.destinationPos = pos;
		this.destinationRotation = rotation;
	}

	public EntityPlayer getPlayer() {
		return player;
	}

	public int getOriginDimension() {
		return originDimension;
	}

	public int getDestinationDimension() {
		return destinationDimension;
	}

	public BlockPos getDestinationPos() {
		return destinationPos;
	}

	public float getDestinationRotation() {
		return destinationRotation;
	}

}
