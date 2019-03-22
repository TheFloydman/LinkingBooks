package thefloydman.linkingbooks.init;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.types.Type;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.datafix.TypeReferences;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.ObjectHolder;
import thefloydman.linkingbooks.util.TileEntityNames;

public class LinkingBooksTileEntityTypes {

	@ObjectHolder(TileEntityNames.BOOK_DISPLAY)
	public static TileEntityType<?> bookDisplay;

	public static <T extends TileEntityType<?>> T registerTileEntityType(IForgeRegistry<TileEntityType<?>> registry,
			T tileEntityType, String name) {
		register(registry, tileEntityType, new ResourceLocation(name));
		return tileEntityType;
	}

	protected static <T extends IForgeRegistryEntry<T>> T register(IForgeRegistry<T> registry, T thing,
			ResourceLocation name) {
		thing.setRegistryName(name);
		registry.register(thing);
		return thing;
	}

	public static <T extends TileEntity> TileEntityType<T> register(String id, TileEntityType.Builder<T> builder) {
		Type<?> type = null;

		try {
			type = DataFixesManager.getDataFixer().getSchema(DataFixUtils.makeKey(1519))
					.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
		} catch (IllegalArgumentException illegalstateexception) {
			if (SharedConstants.developmentMode) {
				throw illegalstateexception;
			}
		}

		TileEntityType<T> tileEntityType = builder.build(type);
		return tileEntityType;
	}

}
