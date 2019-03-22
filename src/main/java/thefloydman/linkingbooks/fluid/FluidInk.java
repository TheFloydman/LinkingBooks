package thefloydman.linkingbooks.fluid;

import net.minecraft.block.state.IBlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorldReaderBase;

public class FluidInk extends Fluid {

	@Override
	public BlockRenderLayer getRenderLayer() {
		return null;
	}

	@Override
	public Item getFilledBucket() {
		return null;
	}

	@Override
	protected boolean canOtherFlowInto(IFluidState state, Fluid fluidIn, EnumFacing direction) {
		return false;
	}

	@Override
	protected Vec3d getFlow(IWorldReaderBase worldIn, BlockPos pos, IFluidState state) {
		return null;
	}

	@Override
	public int getTickRate(IWorldReaderBase p_205569_1_) {
		return 0;
	}

	@Override
	protected float getExplosionResistance() {
		return 0;
	}

	@Override
	public float getHeight(IFluidState state) {
		return 0;
	}

	@Override
	protected IBlockState getBlockState(IFluidState state) {
		return null;
	}

	@Override
	public boolean isSource(IFluidState state) {
		return false;
	}

	@Override
	public int getLevel(IFluidState p_207192_1_) {
		return 0;
	}
}
