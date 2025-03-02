package net.mcreator.tp_compass.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.tp_compass.world.inventory.TPToPlayerGuiMenu;
import net.mcreator.tp_compass.network.TPToPlayerGuiButtonMessage;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class TPToPlayerGuiScreen extends AbstractContainerScreen<TPToPlayerGuiMenu> {
	private final static HashMap<String, Object> guistate = TPToPlayerGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox PlayerName;
	Button button_back;
	Button button_ok;

	public TPToPlayerGuiScreen(TPToPlayerGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 300;
		this.imageHeight = 200;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("tp_compass:textures/screens/tp_to_player_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		PlayerName.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (PlayerName.isFocused())
			return PlayerName.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String PlayerNameValue = PlayerName.getValue();
		super.resize(minecraft, width, height);
		PlayerName.setValue(PlayerNameValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.tp_compass.tp_to_player_gui.label_tp_to_player"), 117, 9, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.tp_compass.tp_to_player_gui.label_youll_be_tp_to"), 107, 61, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		PlayerName = new EditBox(this.font, this.leftPos + 88, this.topPos + 85, 118, 18, Component.translatable("gui.tp_compass.tp_to_player_gui.PlayerName")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.tp_compass.tp_to_player_gui.PlayerName").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos, boolean flag) {
				super.moveCursorTo(pos, flag);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.tp_compass.tp_to_player_gui.PlayerName").getString());
				else
					setSuggestion(null);
			}
		};
		PlayerName.setMaxLength(32767);
		PlayerName.setSuggestion(Component.translatable("gui.tp_compass.tp_to_player_gui.PlayerName").getString());
		guistate.put("text:PlayerName", PlayerName);
		this.addWidget(this.PlayerName);
		button_back = Button.builder(Component.translatable("gui.tp_compass.tp_to_player_gui.button_back"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new TPToPlayerGuiButtonMessage(0, x, y, z));
				TPToPlayerGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 5, this.topPos + 6, 46, 20).build();
		guistate.put("button:button_back", button_back);
		this.addRenderableWidget(button_back);
		button_ok = Button.builder(Component.translatable("gui.tp_compass.tp_to_player_gui.button_ok"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new TPToPlayerGuiButtonMessage(1, x, y, z));
				TPToPlayerGuiButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 127, this.topPos + 126, 35, 20).build();
		guistate.put("button:button_ok", button_ok);
		this.addRenderableWidget(button_ok);
	}
}
