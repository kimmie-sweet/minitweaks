package minitweaks.mixins.mob.snowgolem.melt;

import minitweaks.MiniTweaksSettings;
import net.minecraft.world.entity.animal.golem.SnowGolem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SnowGolem.class)
public abstract class SnowGolemEntityMixin {

    @Inject(
            method = "aiStep",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/animal/golem/SnowGolem;hurtServer(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z",
                    shift = At.Shift.BEFORE
            ),
            cancellable = true
    )
    private void noMelting(CallbackInfo ci) {
        if (MiniTweaksSettings.noSnowGolemMelting) {
            ci.cancel();
        }
    }
}