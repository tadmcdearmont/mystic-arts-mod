package net.mysticarts.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.mysticarts.monk.MonkData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    private void applyMonkArmor(net.minecraft.entity.damage.DamageSource source, float amount, CallbackInfoReturnable<Boolean> result) {
        if ((Object) this instanceof PlayerEntity player) {
            if (MonkData.isUnarmoredAndUnarmed(player)) {
                float armor = MonkData.getNaturalArmor(player);
                if (armor > 0) {
                    result.setReturnValue(true); // Just a placeholder to show we intercepted, but we actually want to modify amount
                    // Actually, modifying return value of damage is hard. 
                    // It's better to use the event if possible or a different Mixin.
                }
            }
        }
    }
}
