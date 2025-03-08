package net.tp_compass.client.gui;

import net.tp_compass.world.inventory.TpguiMenu;
import net.tp_compass.network.TpguiButtonMessage;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class TpguiScreen extends AbstractContainerScreen<TpguiMenu> {
	private final static HashMap<String, Object> guistate = TpguiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_latest_spawn_point;
	Button button_world_spawn;
	Button button_x_0_z_0;
	Button button_tp_to_player;

	public TpguiScreen(TpguiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 400;
		this.imageHeight = 200;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("tp_compass:textures/screens/tpgui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(RenderType::guiTextured, texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.tp_compass.tpgui.label_tp_selection"), 174, 8, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.tp_compass.tpgui.label_more_coming_soon"), 159, 183, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_latest_spawn_point = Button.builder(Component.translatable("gui.tp_compass.tpgui.button_latest_spawn_point"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new TpguiButtonMessage(0, x, y, z));
				TpguiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 12, this.topPos + 28, 119, 20).build();
		guistate.put("button:button_latest_spawn_point", button_latest_spawn_point);
		this.addRenderableWidget(button_latest_spawn_point);
		button_world_spawn = Button.builder(Component.translatable("gui.tp_compass.tpgui.button_world_spawn"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new TpguiButtonMessage(1, x, y, z));
				TpguiButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 12, this.topPos + 56, 82, 20).build();
		guistate.put("button:button_world_spawn", button_world_spawn);
		this.addRenderableWidget(button_world_spawn);
		button_x_0_z_0 = Button.builder(Component.translatable("gui.tp_compass.tpgui.button_x_0_z_0"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new TpguiButtonMessage(2, x, y, z));
				TpguiButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 12, this.topPos + 83, 76, 20).build();
		guistate.put("button:button_x_0_z_0", button_x_0_z_0);
		this.addRenderableWidget(button_x_0_z_0);
		button_tp_to_player = Button.builder(Component.translatable("gui.tp_compass.tpgui.button_tp_to_player"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new TpguiButtonMessage(3, x, y, z));
				TpguiButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + 12, this.topPos + 108, 87, 20).build();
		guistate.put("button:button_tp_to_player", button_tp_to_player);
		this.addRenderableWidget(button_tp_to_player);
	}
}
