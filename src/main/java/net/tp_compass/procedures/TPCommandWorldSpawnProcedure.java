package net.tp_compass.procedures;

import net.tp_compass.init.TpCompassModItems;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

public class TPCommandWorldSpawnProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(TpCompassModItems.TP_COMPASS.get())) : false) {
			{
				Entity _ent = entity;
				_ent.teleportTo((world.getLevelData().getSpawnPos().getX()), (world.getLevelData().getSpawnPos().getY()), (world.getLevelData().getSpawnPos().getZ()));
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport((world.getLevelData().getSpawnPos().getX()), (world.getLevelData().getSpawnPos().getY()), (world.getLevelData().getSpawnPos().getZ()), _ent.getYRot(), _ent.getXRot());
			}
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("You have to get a TP Compass to run this!"), true);
		}
	}
}
