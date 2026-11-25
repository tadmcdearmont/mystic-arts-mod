package net.mysticarts.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class MysticArtsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
    }

    public static void openCodex(PlayerEntity player) {
        MinecraftClient.getInstance().setScreen(new MysticCodexScreen(player));
    }
}
