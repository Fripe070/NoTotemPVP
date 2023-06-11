package io.github.fripe070.nototempvp.mixin;


import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static io.github.fripe070.nototempvp.NoTotemPVP.CONFIG;

@Mixin(LivingEntity.class)
public class TryUseTotemMixin {
    @Inject(at = @At("HEAD"), method = "tryUseTotem", cancellable = true)
    private void tryUseTotem(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        Entity attacker = source.getAttacker();
        if (attacker == null) return;

        if (CONFIG.totemIgnoredEntities().contains(Registry.ENTITY_TYPE.getId(attacker.getType()).toString())) {
            cir.setReturnValue(false);
        }
    }
}
