package thefloydman.linkingbooks.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.resources.SimpleResource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PanelImageHelper {
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");

	@OnlyIn(Dist.CLIENT)
	public static NativeImage createPanelImage(Framebuffer framebuffer) {
		//framebuffer.framebufferTextureHeight = 200;
		//framebuffer.framebufferTextureWidth = 300;
		//final int width = 300;
		//final int height = 200;
		int width = Minecraft.getInstance().mainWindow.getWidth();
		int height = Minecraft.getInstance().mainWindow.getHeight();
		if (OpenGlHelper.isFramebufferEnabled()) {
			width = framebuffer.framebufferWidth;
			height = framebuffer.framebufferHeight;
		}

		NativeImage image = new NativeImage(width, height, false);
		if (OpenGlHelper.isFramebufferEnabled()) {
			GlStateManager.bindTexture(framebuffer.framebufferTexture);
			image.downloadFromTexture(0, true);
		} else {
			image.downloadFromFramebuffer(true);
		}

		image.flip();
		return image;
	}

	public static void savePanelImageToServer(NativeImage image, String filename) {
		File file = new File(Reference.getServerImageDirectory());
		try {
			Reference.LOGGER.info(file.getCanonicalPath());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		file.mkdir();
		final File file2 = new File(Reference.getServerImageDirectory(), filename);
		SimpleResource.RESOURCE_IO_EXECUTOR.execute(() -> {
			try {
				image.write(file2);
			} catch (IOException e) {
				Reference.LOGGER.info("Could not save linking panel image to server.");
				e.printStackTrace();
			} finally {
				image.close();
			}

		});
	}

	public static void savePanelImageToClient(NativeImage image, String filename) {
		File file = new File(Reference.getClientImageDirectory());
		file.mkdir();
		final File file2 = new File(Reference.getClientImageDirectory(), filename);
		if (!file2.exists()) {
			SimpleResource.RESOURCE_IO_EXECUTOR.execute(() -> {
				try {
					file2.createNewFile();
					image.write(file2);
				} catch (IOException e) {
					Reference.LOGGER.info("Could not save linking panel image to client.");
					e.printStackTrace();
				} finally {
					image.close();
				}

			});
		} else {
			Reference.LOGGER.info("Could not save linking panel image to client. File already exists.");
		}
	}

	public static String generateFilename(final String uuid) {
		return uuid + "-" + DATE_FORMAT.format(new Date()) + ".png";
	}
}