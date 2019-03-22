package thefloydman.linkingbooks.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import thefloydman.linkingbooks.entity.EntityLinkingBook;

public class LinkingBooksEntityTypes<T extends Entity> {

	public static final EntityType<?> LINKING_BOOK = EntityType.Builder
			.create(EntityLinkingBook.class, EntityLinkingBook::new)
			.tracker(/* render distance */128, /* ticks between updates */1, true).build("")
			.setRegistryName("linking_book");

}
