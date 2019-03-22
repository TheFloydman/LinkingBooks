package thefloydman.linkingbooks.client.model;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLinkingBook extends ModelBase {
	public ModelRenderer cover;
	public ModelRenderer page;

	public ModelLinkingBook() {
		this.textureWidth = 36;
		this.textureHeight = 15;
		this.cover = new ModelRenderer(this, 0, 0);
		this.cover.setRotationPoint(-5.5F, 23.5F, -3.5F);
		this.cover.addBox(0.0F, 0.0F, 0.0F, 11, 1, 7, 0.0F);
		this.page = new ModelRenderer(this, 0, 8);
		this.page.setRotationPoint(-5.0F, 23.0F, -3.0F);
		this.page.addBox(0.0F, 0.0F, 0.0F, 10, 1, 6, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GlStateManager.pushMatrix();
		GlStateManager.translatef(this.cover.offsetX, this.cover.offsetY, this.cover.offsetZ);
		GlStateManager.translatef(this.cover.rotationPointX * f5, this.cover.rotationPointY * f5,
				this.cover.rotationPointZ * f5);
		GlStateManager.scaled(1.0D, 0.5D, 1.0D);
		GlStateManager.translatef(-this.cover.offsetX, -this.cover.offsetY, -this.cover.offsetZ);
		GlStateManager.translatef(-this.cover.rotationPointX * f5, -this.cover.rotationPointY * f5,
				-this.cover.rotationPointZ * f5);
		this.cover.render(f5);
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		GlStateManager.translatef(this.page.offsetX, this.page.offsetY, this.page.offsetZ);
		GlStateManager.translatef(this.page.rotationPointX * f5, this.page.rotationPointY * f5,
				this.page.rotationPointZ * f5);
		GlStateManager.scaled(1.0D, 0.5D, 1.0D);
		GlStateManager.translatef(-this.page.offsetX, -this.page.offsetY, -this.page.offsetZ);
		GlStateManager.translatef(-this.page.rotationPointX * f5, -this.page.rotationPointY * f5,
				-this.page.rotationPointZ * f5);
		this.page.render(f5);
		GlStateManager.popMatrix();
	}
}
