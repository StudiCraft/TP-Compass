
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.tp_compass.init;

import net.tp_compass.item.TPCompassItem;
import net.tp_compass.TpCompassMod;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;

import net.minecraft.world.item.Item;

import java.util.function.Function;

public class TpCompassModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(TpCompassMod.MODID);
	public static final DeferredItem<Item> TP_COMPASS = register("tp_compass", TPCompassItem::new);

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> DeferredItem<I> register(String name, Function<Item.Properties, ? extends I> supplier) {
		return REGISTRY.registerItem(name, supplier, new Item.Properties());
	}
}
