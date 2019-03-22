package thefloydman.linkingbooks.block.properties;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.state.EnumProperty;

public class ColorProperty extends EnumProperty<EnumDyeColor> {

	protected ColorProperty(String name, Collection<EnumDyeColor> values) {
		super(name, EnumDyeColor.class, values);
	}

	/**
	 * Create a new ColorProperty with all colors that match the given Predicate
	 */
	public static ColorProperty create(String name, Predicate<EnumDyeColor> filter) {
		return create(name, Arrays.stream(EnumDyeColor.values()).filter(filter).collect(Collectors.toList()));
	}

	public static ColorProperty create(String p_196962_0_, EnumDyeColor... p_196962_1_) {
		return create(p_196962_0_, Lists.newArrayList(p_196962_1_));
	}

	/**
	 * Create a new PropertyDirection for the given direction values
	 */
	public static ColorProperty create(String name, Collection<EnumDyeColor> values) {
		return new ColorProperty(name, values);
	}

}
