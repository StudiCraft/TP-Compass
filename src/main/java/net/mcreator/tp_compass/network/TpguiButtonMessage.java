
package net.mcreator.tp_compass.network;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.tp_compass.world.inventory.TpguiMenu;
import net.mcreator.tp_compass.procedures.TPCompassTPButtonX0Z0Procedure;
import net.mcreator.tp_compass.procedures.TPCompassTPButtonWorldSpawnProcedure;
import net.mcreator.tp_compass.procedures.TPCompassTPButtonOpenTPPlayerGuiProcedure;
import net.mcreator.tp_compass.procedures.TPCompassTPButtonLatestProcedure;
import net.mcreator.tp_compass.TpCompassMod;

import java.util.HashMap;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record TpguiButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<TpguiButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(TpCompassMod.MODID, "tpgui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, TpguiButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, TpguiButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new TpguiButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<TpguiButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final TpguiButtonMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> {
				Player entity = context.player();
				int buttonID = message.buttonID;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleButtonAction(entity, buttonID, x, y, z);
			}).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = TpguiMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			TPCompassTPButtonLatestProcedure.execute(entity);
		}
		if (buttonID == 1) {

			TPCompassTPButtonWorldSpawnProcedure.execute(world, entity);
		}
		if (buttonID == 2) {

			TPCompassTPButtonX0Z0Procedure.execute(world, entity);
		}
		if (buttonID == 3) {

			TPCompassTPButtonOpenTPPlayerGuiProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		TpCompassMod.addNetworkMessage(TpguiButtonMessage.TYPE, TpguiButtonMessage.STREAM_CODEC, TpguiButtonMessage::handleData);
	}
}
