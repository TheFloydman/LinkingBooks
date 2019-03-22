package thefloydman.linkingbooks.tileentity;

import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import thefloydman.linkingbooks.entity.EntityLinkingBook;
import thefloydman.linkingbooks.init.LinkingBooksTileEntityTypes;
import thefloydman.linkingbooks.item.ItemLinkingBookWritten;

public class TileEntityBookDisplay extends TileEntity {

	protected EnumFacing facing = EnumFacing.NORTH;
	protected NonNullList<ItemStack> contents = NonNullList.withSize(1, ItemStack.EMPTY);

	public TileEntityBookDisplay() {
		super(LinkingBooksTileEntityTypes.bookDisplay);
	}

	public TileEntityBookDisplay(TileEntityType<?> tileEntityType) {
		super(tileEntityType);
	}

	@Override
	public NBTTagCompound write(NBTTagCompound nbt) {
		super.write(nbt);
		ItemStackHelper.saveAllItems(nbt, this.contents);
		return nbt;
	}

	@Override
	public void read(NBTTagCompound nbt) {
		super.read(nbt);
		this.contents = NonNullList.withSize(1, ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(nbt, this.contents);
	}

	public ItemStack getBook() {
		return contents.get(0);
	}

	public void setBook(ItemStack book) {
		this.contents.set(0, book);
		this.markDirty();
	}

	public void dropItems() {
		ItemStack bookStack = this.getBook();
		if (!this.getWorld().isRemote()) {
			if (bookStack.getItem() instanceof ItemLinkingBookWritten) {
				NBTTagCompound stackTag = bookStack.getTag().getCompound("LinkingBooksInfo");
				EntityLinkingBook entity = new EntityLinkingBook((World) world);
				NBTTagCompound entityTag = entity.getEntityData();
				entityTag.setTag("LinkingBooksInfo", stackTag);
				float rotation;
				switch (this.facing) {
				case NORTH:
					rotation = 180.0F;
					break;
				case EAST:
					rotation = 270.0F;
					break;
				case SOUTH:
					rotation = 0.0F;
					break;
				case WEST:
					rotation = 90.0F;
					break;
				default:
					rotation = 0.0F;
					break;
				}
				entity.setPositionAndRotation(this.getPos().getX() + 0.5D, this.getPos().getY() + 0.5D,
						this.getPos().getZ() + 0.5D, rotation, 0.0F);
				entity.setCustomName(bookStack.getDisplayName());
				this.getWorld().spawnEntity(entity);
			}
		}
	}
	
	public void setFacing(EnumFacing facing) {
		this.facing = facing;
	}
	
	public EnumFacing getFacing() {
		return this.facing;
	}

}
