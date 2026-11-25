package net.mysticarts;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.mysticarts.item.MysticCodexItem;
import net.mysticarts.command.MysticCommandRegistration;
import net.mysticarts.monk.MonkEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MysticArtsMod implements ModInitializer {
    public static final String MOD_ID = "mysticarts";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // Items
    public static final Item MYSTIC_CODEX = new MysticCodexItem(
        new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)
    );

    @Override
    public void onInitialize() {
        LOGGER.info("[Mystic Arts] Initializing Mystic Arts Mod for Fabric 1.20.1...");

        // Register Items
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "mystic_codex"), MYSTIC_CODEX);

        // Register Creative Tabs
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(MYSTIC_CODEX);
        });

        // Register Commands
        CommandRegistrationCallback.EVENT.register(MysticCommandRegistration::register);
        
        // Register Events
        MonkEvents.register();

        LOGGER.info("[Mystic Arts] Ready. The path of the ascetic monk is open.");
    }
}
