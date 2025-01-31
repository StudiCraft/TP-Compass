package net.mcreator.tp_compass.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

import net.mcreator.tp_compass.init.TpCompassModItems;

public class TPCommandX0Z0Procedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(TpCompassModItems.TP_COMPASS.get())) : false) {
			{
				Entity _ent = entity;
				_ent.teleportTo(0, (world.getLevelData().getSpawnPos().getY()), 0);
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport(0, (world.getLevelData().getSpawnPos().getY()), 0, _ent.getYRot(), _ent.getXRot());
			}
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("You have to get a TP Compass to do this!"), true);
		}
	}
}
