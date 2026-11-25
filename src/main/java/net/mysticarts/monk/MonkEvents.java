package net.mysticarts.monk;

import net.fabricmc.fabric.api.entity.event.v1.LivingEntityEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class MonkEvents {
    public static void register() {
        // Unarmed Attack Damage Modifier
        LivingEntityEvents.MODIFY_ATTACK_DAMAGE.register((entity, source, amount, context) -> {
            if (entity instanceof PlayerEntity player) {
                if (MonkData.isUnarmoredAndUnarmed(player)) {
                    float extraDamage = MonkData.getUnarmedAttackDamage(player) - 1.0f;
                    return amount + extraDamage;
                }
            }
            return amount;
        });

        // Unarmed Natural Armor (Damage Reduction)
        LivingEntityEvents.MODIFY_DAMAGE_TAKEN.register((entity, source, amount, context) -> {
            if (entity instanceof PlayerEntity player) {
                if (MonkData.isUnarmoredAndUnarmed(player)) {
                    float armorReduction = MonkData.getNaturalArmor(player);
                    // Simple reduction: amount - armor. Ensure it doesn't go below 0.
                    return Math.max(0, amount - armorReduction);
                }
            }
            return amount;
        });
    }
}
