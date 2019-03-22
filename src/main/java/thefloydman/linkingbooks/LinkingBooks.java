package thefloydman.linkingbooks;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import thefloydman.linkingbooks.proxy.ServerProxy;
import thefloydman.linkingbooks.gui.LinkingBooksGuiHandler;
import thefloydman.linkingbooks.network.LinkingBooksPacketHandler;
import thefloydman.linkingbooks.proxy.ClientProxy;
import thefloydman.linkingbooks.util.Reference;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

@Mod(Reference.MOD_ID)
public class LinkingBooks {

	public static ServerProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

	public LinkingBooks() {
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY,
				() -> LinkingBooksGuiHandler::getClientGuiElement);

		// Register the enqueueIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		// Register the processIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		LinkingBooksPacketHandler.register();
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		if (Reference.getClientImageDirectory() == null) {
			try {
				File file = event.getMinecraftSupplier().get().gameDir;
				file = new File(file, "linkingbooks/panel_images");
				file.mkdirs();
				Reference.setClientImageDirectory(file.getCanonicalPath());
			} catch (IOException e) {
				Reference.LOGGER.info("Could not resolve linking panel image directory on client.");
				e.printStackTrace();
			}
		}
	}

	private void enqueueIMC(final InterModEnqueueEvent event) {
		// some example code to dispatch IMC to another mod
		InterModComms.sendTo("linkingbooks", "helloworld", () -> {
			Reference.LOGGER.info("Hello world from the MDK");
			return "Hello world";
		});
	}

	private void processIMC(final InterModProcessEvent event) {
		// some example code to receive and process InterModComms from other mods
		Reference.LOGGER.info("Got IMC {}",
				event.getIMCStream().map(m -> m.getMessageSupplier().get()).collect(Collectors.toList()));
	}

	static {
		FluidRegistry.enableUniversalBucket();
	}
}
