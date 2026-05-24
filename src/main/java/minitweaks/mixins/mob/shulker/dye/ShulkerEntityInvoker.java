package minitweaks.mixins.mob.shulker.dye;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Optional;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.item.DyeColor;

@Mixin(Shulker.class)
public interface ShulkerEntityInvoker {
    @Invoker("setVariant")
    void invokeSetColor(Optional<DyeColor> color);
}
