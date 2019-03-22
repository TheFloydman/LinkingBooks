package thefloydman.linkingbooks.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import thefloydman.linkingbooks.client.container.ContainerLinkingBook;
import thefloydman.linkingbooks.network.LinkingBooksPacketHandler;
import thefloydman.linkingbooks.util.Reference;

public class GuiLinkingBook extends GuiContainer {

	private static final ResourceLocation BOOK_TEXTURE_LEFT = Reference.modRL("textures/gui/linking_book_left.png");
	private static final ResourceLocation BOOK_TEXTURE_RIGHT = Reference.modRL("textures/gui/linking_book_right.png");
	private final EntityPlayer player = Minecraft.getInstance().player;
	protected int bookLeftWidth;
	protected int bookRightWidth;
	protected int bookLeftHeight;
	protected int bookRightHeight;
	protected int bookTotalWidth;
	protected int bookTotalHeight;
	protected int linkingPanelX;
	protected int linkingPanelY;
	protected int linkingPanelWidth;
	protected int linkingPanelHeight;
	protected int left;
	protected int top;
	protected int destDim;
	protected int destX;
	protected int destY;
	protected int destZ;
	protected float destRot;
	protected boolean bookInHand;

	public GuiLinkingBook(PacketBuffer linkData) {
		super(new ContainerLinkingBook(Minecraft.getInstance().player));
		this.destDim = linkData.readInt();
		this.destX = linkData.readInt();
		this.destY = linkData.readInt();
		this.destZ = linkData.readInt();
		this.destRot = linkData.readFloat();
		this.bookInHand = linkData.readBoolean();

		this.bookLeftWidth = 144;
		this.bookRightWidth = 144;
		this.bookLeftHeight = 180;
		this.bookRightHeight = 180;
		this.bookTotalWidth = 288;
		this.bookTotalHeight = 180;
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.render(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.left = (this.width - this.bookTotalWidth) / 2;
		this.top = (this.height - this.bookTotalHeight) / 2;
		this.linkingPanelX = this.left + this.bookLeftWidth + 35;
		this.linkingPanelY = this.top + 35;
		this.linkingPanelWidth = 72;
		this.linkingPanelHeight = 48;
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(BOOK_TEXTURE_LEFT);
		this.drawTexturedModalRect(this.left, this.top, 0, 0, this.bookLeftWidth, this.bookLeftHeight);
		this.mc.getTextureManager().bindTexture(BOOK_TEXTURE_RIGHT);
		this.drawTexturedModalRect(this.left + this.bookLeftWidth, this.top, 0, 0, this.bookRightWidth,
				this.bookRightHeight);
		if (this.player.dimension.getId() == this.destDim) {
			this.drawTexturedModalRect(this.linkingPanelX, this.linkingPanelY, 144, 0, this.linkingPanelWidth,
					this.linkingPanelHeight);
		} else {
			this.drawTexturedModalRect(this.linkingPanelX, this.linkingPanelY, 144, 48, this.linkingPanelWidth,
					this.linkingPanelHeight);
		}
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int state) {
		if (this.destDim != this.player.dimension.getId()) {
			if (mouseOnLinkingPanel(mouseX, mouseY)) {
				LinkingBooksPacketHandler.sendLinkRequest(this.destDim, this.destX, this.destY, this.destZ,
						this.destRot, this.bookInHand);
				this.close();
			}
		}
		return super.mouseReleased(mouseX, mouseY, state);
	}

	protected boolean mouseOnLinkingPanel(final double x, final double y) {
		if (x >= this.linkingPanelX && x <= this.linkingPanelX + this.linkingPanelWidth && y >= this.linkingPanelY
				&& y <= this.linkingPanelY + this.linkingPanelHeight) {
			return true;
		}
		return false;
	}

}
