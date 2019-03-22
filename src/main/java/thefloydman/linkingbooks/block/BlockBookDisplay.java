package thefloydman.linkingbooks.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import thefloydman.linkingbooks.block.properties.ColorProperty;
import thefloydman.linkingbooks.item.ItemLinkingBookWritten;
import thefloydman.linkingbooks.tileentity.TileEntityBookDisplay;
import thefloydman.linkingbooks.util.BlockNames;
import thefloydman.linkingbooks.util.LinkingBookUtils;

public class BlockBookDisplay extends BlockContainer {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty HAS_BOOK = BooleanProperty.create("has_book");
	public static final ColorProperty BOOK_COLOR = ColorProperty.create("book_color", EnumDyeColor.WHITE, EnumDyeColor.BLACK,
			EnumDyeColor.BLUE, EnumDyeColor.BROWN, EnumDyeColor.CYAN, EnumDyeColor.GRAY, EnumDyeColor.GREEN,
			EnumDyeColor.LIGHT_BLUE, EnumDyeColor.LIGHT_GRAY, EnumDyeColor.LIME, EnumDyeColor.MAGENTA,
			EnumDyeColor.ORANGE, EnumDyeColor.PINK, EnumDyeColor.PURPLE, EnumDyeColor.RED, EnumDyeColor.YELLOW);

	private static final VoxelShape SHAPE_NORTH = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.3D, 13.0D);
	private static final VoxelShape SHAPE_EAST = Block.makeCuboidShape(3.0D, 0.0D, 0.0D, 16.0D, 8.3D, 16.0D);
	private static final VoxelShape SHAPE_SOUTH = Block.makeCuboidShape(0.0D, 0.0D, 3.0D, 16.0D, 8.3D, 16.0D);
	private static final VoxelShape SHAPE_WEST = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 13.0D, 8.3D, 16.0D);

	public BlockBookDisplay(Properties properties) {
		super(properties);
		this.setRegistryName(BlockNames.BOOK_DISPLAY);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, EnumFacing.NORTH).with(HAS_BOOK, false)
				.with(BOOK_COLOR, EnumDyeColor.GREEN));
	}

	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		EnumFacing facing = context.getPlacementHorizontalFacing().getOpposite();
		return this.getDefaultState().with(FACING, facing);
	}

	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		switch (state.get(FACING)) {
		case NORTH:
			return SHAPE_NORTH;
		case EAST:
			return SHAPE_EAST;
		case SOUTH:
			return SHAPE_SOUTH;
		case WEST:
			return SHAPE_WEST;
		default:
			return SHAPE_NORTH;
		}
	}

	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
		builder.add(FACING, HAS_BOOK, BOOK_COLOR);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public TileEntity createTileEntity(IBlockState state, IBlockReader world) {
		return new TileEntityBookDisplay();
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isSolid(IBlockState state) {
		return false;
	}

	@Override
	public void onReplaced(IBlockState state, World world, BlockPos pos, IBlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			TileEntityBookDisplay tileEntity = (TileEntityBookDisplay) world.getTileEntity(pos);
			if (tileEntity instanceof TileEntityBookDisplay) {
				tileEntity.dropItems();
			}

			super.onReplaced(state, world, pos, newState, isMoving);
		}
	}

	@Override
	public boolean onBlockActivated(IBlockState state, World world, BlockPos pos, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {

		((TileEntityBookDisplay) world.getTileEntity(pos)).setFacing(state.get(FACING));

		if (world.isRemote) {
			return true;
		} else {
			TileEntityBookDisplay tileEntity = (TileEntityBookDisplay) world.getTileEntity(pos);
			if (tileEntity == null) {
				return true;
			}
			ItemStack tileBook = tileEntity.getBook();
			if (tileBook.isEmpty()) {
				if (player.getHeldItem(hand).getItem() instanceof ItemLinkingBookWritten) {
					tileEntity.setBook(player.getHeldItem(hand));
					world.setBlockState(pos,
							state.with(HAS_BOOK, true).with(BOOK_COLOR,
									EnumDyeColor.byTranslationKey(
											((ItemLinkingBookWritten) (player.getHeldItem(hand).getItem()))
													.getRegistryName().getPath().substring(21))));
					player.setHeldItem(hand, ItemStack.EMPTY);
				}
			} else {
				if (player.isSneaking() && player.getHeldItem(hand).isEmpty()) {
					player.setHeldItem(hand, tileBook);
					tileEntity.setBook(ItemStack.EMPTY);
					world.setBlockState(pos, state.with(HAS_BOOK, false));
				} else {
					NBTTagCompound tag = tileBook.getTag().getCompound("LinkingBooksInfo");
					NetworkHooks.openGui((EntityPlayerMP) player, (ItemLinkingBookWritten) tileBook.getItem(),
							buf -> LinkingBookUtils.createGuiPacketBuffer(tag, buf, false));
				}
			}
			return true;
		}
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new TileEntityBookDisplay();
	}

}
