package io.github.fripe070.nototempvp.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static io.github.fripe070.nototempvp.NoTotemPVP.CONFIG;

@Mixin(PlayerEntity.class)
public abstract class DisableElytraMixin {
    public int elytraCooldownTicks;

    @Inject(at = @At("TAIL"), method = "damage")
    private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (amount == 0.0f) return;
        if (!CONFIG.elytraDisable()) return;

        switch (CONFIG.disableElytraOn()) {
            case PLAYER -> {
                if (!(source.getAttacker() instanceof PlayerEntity)) return;
            }
            case ENTITY -> {
                if (source.getAttacker() == null) return;
            }
        }

        elytraCooldownTicks = CONFIG.elytraCooldownTicks();
    }
    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo ci) {
        if (elytraCooldownTicks > 0) elytraCooldownTicks--;
    }

    @Inject(at = @At("HEAD"), method = "checkFallFlying", cancellable = true)
    private void checkFallFlying(CallbackInfoReturnable<Boolean> cir) {
        if (elytraCooldownTicks > 0) cir.setReturnValue(false);
    }
}
