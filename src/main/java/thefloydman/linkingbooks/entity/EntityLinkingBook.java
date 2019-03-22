package thefloydman.linkingbooks.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityLinkingBook extends EntityLiving {

	private static final DataParameter<ItemStack> BOOK_STACK = EntityDataManager.createKey(EntityLinkingBook.class,
			DataSerializers.ITEM_STACK);

	public EntityLinkingBook(World world) {
		super(EntityType.getById("linkingbooks:linking_book"), world);
		this.setSize(0.625F, 0.0625F);
	}

	@Override
	public void knockBack(Entity entity, float strength, double xRatio, double zRatio) {
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(BOOK_STACK, ItemStack.EMPTY);
	}

	@Override
	public boolean canDespawn() {
		return false;
	}

	@Override
	public boolean isNoDespawnRequired() {
		return true;
	}

	public void setBook(ItemStack bookStack) {
		this.dataManager.set(BOOK_STACK, bookStack);
	}

	@Override
	public void writeAdditional(NBTTagCompound compound) {
		super.writeAdditional(compound);
		NBTTagCompound bookCompound = new NBTTagCompound();
		if (!this.getBook().isEmpty()) {
			this.getBook().write(bookCompound);
		}
		compound.setTag("linking_book", bookCompound);
	}

	@Override
	public void readAdditional(NBTTagCompound compound) {
		super.readAdditional(compound);
		if (compound.contains("linking_book", 10)) {
			this.setBook(ItemStack.read(compound.getCompound("linking_book")));
		}
	}

	public ItemStack getBook() {
		return this.dataManager.get(BOOK_STACK);
	}

	@Override
	public boolean canBeLeashedTo(EntityPlayer player) {
		return false;
	}

}
