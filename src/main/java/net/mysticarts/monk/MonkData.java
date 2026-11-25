package net.mysticarts.monk;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.world.level.storage.DynamicStorageStateManager;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MonkData {
    public static final int MAX_ASCETIC_LEVEL = 20;
    public static final int MAX_TRANSCENDENT_LEVEL = 100;
    
    private static final String DATA_KEY = "mystic_arts_data";
    private static final String LEVEL_NBT = "player_level";
    private static final String MILESTONES_NBT = "achievements";

    public static int getLevel(PlayerEntity player) {
        NbtCompound nbt = getPlayerData(player);
        return nbt.getInt(LEVEL_NBT);
    }

    public static void setLevel(PlayerEntity player, int level) {
        NbtCompound nbt = getPlayerData(player);
        nbt.putInt(LEVEL_NBT, Math.max(0, Math.min(MAX_TRANSCENDENT_LEVEL, level)));
    }

    public static List<String> getMilestones(PlayerEntity player) {
        List<String> milestones = new ArrayList<>();
        NbtCompound nbt = getPlayerData(player);
        if (nbt.contains(MILESTONES_NBT)) {
            NbtList list = nbt.getList(MILESTONES_NBT, 10);
            for (int i = 0; i < list.size(); i++) {
                milestones.add(list.getString(i));
            }
        }
        return milestones;
    }

    public static void addMilestone(PlayerEntity player, String milestone) {
        NbtCompound nbt = getPlayerData(player);
        NbtList list = nbt.getList(MILESTONES_NBT, 10);
        boolean alreadyHas = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.getString(i).equals(milestone)) {
                alreadyHas = true;
                break;
            }
        }
        if (!alreadyHas) {
            list.add(milestone);
            nbt.put(MILESTONES_NBT, list);
        }
    }

    public static boolean hasMilestone(PlayerEntity player, String milestone) {
        return getMilestones(player).contains(milestone);
    }

    private static NbtCompound getPlayerData(PlayerEntity player) {
        // In a real implementation, this would be managed by a Capability or similar.
        // For now, we use the player's persistent data.
        return player.getPersistentData();
    }

    public static boolean isUnarmoredAndUnarmed(PlayerEntity player) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                if (!player.getEquippedStack(slot).isEmpty()) {
                    return false;
                }
            }
        }

        ItemStack mainHand = player.getMainHandStack();
        if (!mainHand.isEmpty() && !(mainHand.getItem() instanceof net.mysticarts.item.MysticCodexItem)) {
            return false;
        }

        return true;
    }

    public static float getUnarmedAttackDamage(PlayerEntity player) {
        if (!isUnarmoredAndUnarmed(player)) return 1.0f;

        int level = getLevel(player);
        if (level <= 0) return 1.0f;
        
        float damage = 1.0f + (level * 1.5f); // Base scaling for Ascetic Path
        
        if (level > MAX_ASCETIC_LEVEL) {
            // Transcendent scaling: exponentially increases
            damage *= (1.0f + (level - MAX_ASCETIC_LEVEL) * 0.5f);
        }
        
        return damage;
    }

    public static float getNaturalArmor(PlayerEntity player) {
        if (!isUnarmoredAndUnarmed(player)) return 0.0f;

        int level = getLevel(player);
        if (level <= 0) return 0.0f;

        float armor = (level <= MAX_ASCETIC_LEVEL) ? (level * 0.5f) : (MAX_ASCETIC_LEVEL * 0.5f);
        
        if (level > MAX_ASCETIC_LEVEL) {
            // Transcendent armor scaling
            armor += (level - MAX_ASCETIC_LEVEL) * 1.5f;
        }
        
        return armor;
    }

    public static void syncPlayerProgress(PlayerEntity player) {
        // This is a placeholder for networking logic to sync NBT to client
    }
}
