
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.tp_compass.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;

import net.minecraft.world.item.Item;

import net.mcreator.tp_compass.item.TPCompassItem;
import net.mcreator.tp_compass.TpCompassMod;

public class TpCompassModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(TpCompassMod.MODID);
	public static final DeferredItem<Item> TP_COMPASS = REGISTRY.register("tp_compass", TPCompassItem::new);
	// Start of user code block custom items
	// End of user code block custom items
}
