
package net.tp_compass.network;

import net.tp_compass.world.inventory.TPToPlayerGuiMenu;
import net.tp_compass.procedures.TPCompassTPButtonOKFromTPToPlayerGuiProcedure;
import net.tp_compass.procedures.TPCompassTPButtonBackFromTPToPlayerGuiProcedure;
import net.tp_compass.TpCompassMod;

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

import java.util.HashMap;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record TPToPlayerGuiButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<TPToPlayerGuiButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(TpCompassMod.MODID, "tp_to_player_gui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, TPToPlayerGuiButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, TPToPlayerGuiButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new TPToPlayerGuiButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<TPToPlayerGuiButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final TPToPlayerGuiButtonMessage message, final IPayloadContext context) {
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
		HashMap guistate = TPToPlayerGuiMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			TPCompassTPButtonBackFromTPToPlayerGuiProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 1) {

			TPCompassTPButtonOKFromTPToPlayerGuiProcedure.execute(world, x, y, z, entity, guistate);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		TpCompassMod.addNetworkMessage(TPToPlayerGuiButtonMessage.TYPE, TPToPlayerGuiButtonMessage.STREAM_CODEC, TPToPlayerGuiButtonMessage::handleData);
	}
}
