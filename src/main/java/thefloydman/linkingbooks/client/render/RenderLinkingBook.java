package thefloydman.linkingbooks.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import thefloydman.linkingbooks.client.model.ModelLinkingBook;
import thefloydman.linkingbooks.entity.EntityLinkingBook;
import thefloydman.linkingbooks.util.Reference;

public class RenderLinkingBook extends RenderLiving<EntityLinkingBook> {

	public RenderLinkingBook(RenderManager manager) {
		super(manager, new ModelLinkingBook(), 0.2F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLinkingBook entity) {
		String path = entity.getBook().getItem().getRegistryName().getPath();
		if (path.equals("air") || path == null) {
			path = "linking_book_written_green";
		}
		String name = path.substring(0, 13) + path.substring(21);
		return Reference.modRL("textures/entity/linking_book/" + name + ".png");

	}


}
