package net.mcreator.tp_compass.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.client.gui.components.EditBox;

import net.mcreator.tp_compass.init.TpCompassModItems;

import java.util.HashMap;

public class TPCompassTPButtonOKFromTPToPlayerGuiProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(TpCompassModItems.TP_COMPASS.get())) : false) {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), ("tp" + " "
						+ (guistate.containsKey("text:myselfname") ? ((EditBox) guistate.get("text:myselfname")).getValue() : "") + " " + (guistate.containsKey("text:PlayerName") ? ((EditBox) guistate.get("text:PlayerName")).getValue() : "")));
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("You have to get a TP Compass to run this!"), true);
		}
	}
}
