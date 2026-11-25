package net.mysticarts.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class MysticCodexScreen extends Screen {
    private final PlayerEntity player;

    public MysticCodexScreen(PlayerEntity player) {
        super(Text.literal("Mystic Codex"));
        this.player = player;
    }

    @Override
    protected void init() {
        // Initialize screen elements here (buttons, etc.)
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("--- Mystic Codex ---"), this.width / 2, 20, 0xFFFFFF);
        
        // In a real implementation, we would fetch data from MonkData
        // For now, we'll just display some placeholder text
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("Current Level: 1"), this.width / 2, 50, 0xFFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("Next Goal: Defeat 5 Wolves"), this.width / 2, 60, 0xFFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("Press [Esc] to Close"), this.width / 2, this.height - 20, 0xAAAAAA);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
