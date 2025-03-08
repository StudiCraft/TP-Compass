
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.tp_compass.init;

import net.tp_compass.client.gui.TpguiScreen;
import net.tp_compass.client.gui.TPToPlayerGuiScreen;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TpCompassModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(TpCompassModMenus.TPGUI.get(), TpguiScreen::new);
		event.register(TpCompassModMenus.TP_TO_PLAYER_GUI.get(), TPToPlayerGuiScreen::new);
	}
}
