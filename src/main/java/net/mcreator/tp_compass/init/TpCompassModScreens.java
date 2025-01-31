
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.tp_compass.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.tp_compass.client.gui.TpguiScreen;
import net.mcreator.tp_compass.client.gui.TPToPlayerGuiScreen;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TpCompassModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(TpCompassModMenus.TPGUI.get(), TpguiScreen::new);
		event.register(TpCompassModMenus.TP_TO_PLAYER_GUI.get(), TPToPlayerGuiScreen::new);
	}
}
