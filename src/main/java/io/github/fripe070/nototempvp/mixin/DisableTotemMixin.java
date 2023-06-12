package io.github.fripe070.nototempvp.mixin;


import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static io.github.fripe070.nototempvp.NoTotemPVP.CONFIG;

@Mixin(LivingEntity.class)
public abstract class DisableTotemMixin {
    @Inject(at = @At("HEAD"), method = "tryUseTotem", cancellable = true)
    private void tryUseTotem(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (CONFIG.totemIgnoredEntities().contains("*")) {
            cir.setReturnValue(false);
            return;
        }

        Entity attacker = source.getAttacker();
        if (attacker == null) return;
        if (CONFIG.totemIgnoredEntities().contains(Registries.ENTITY_TYPE.getId(attacker.getType()).toString())) {
            cir.setReturnValue(false);
        }
    }
}
