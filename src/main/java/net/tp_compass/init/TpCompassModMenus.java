
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.tp_compass.init;

import net.tp_compass.world.inventory.TpguiMenu;
import net.tp_compass.world.inventory.TPToPlayerGuiMenu;
import net.tp_compass.TpCompassMod;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.registries.Registries;

public class TpCompassModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, TpCompassMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<TpguiMenu>> TPGUI = REGISTRY.register("tpgui", () -> IMenuTypeExtension.create(TpguiMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<TPToPlayerGuiMenu>> TP_TO_PLAYER_GUI = REGISTRY.register("tp_to_player_gui", () -> IMenuTypeExtension.create(TPToPlayerGuiMenu::new));
}
