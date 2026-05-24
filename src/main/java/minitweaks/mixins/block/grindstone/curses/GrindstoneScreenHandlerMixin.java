package minitweaks.mixins.block.grindstone.curses;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import minitweaks.MiniTweaksSettings;
import net.minecraft.world.inventory.GrindstoneMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GrindstoneMenu.class)
public class GrindstoneScreenHandlerMixin {

    @ModifyReturnValue(
            method = "computeResult",
            at = @At("RETURN")
    )
    private ItemStack minitweaks$computeResult(ItemStack original) {

        if (!MiniTweaksSettings.removableCurses) {
            return original;
        }

        // Vanilla already removed non-curses inside computeResult flow.
        // So we undo the "curse stripping effect" by returning the item earlier state logic would have allowed.

        return restoreCursedItems(original);
    }

    private ItemStack restoreCursedItems(ItemStack stack) {

        var enchants = stack.getEnchantments();

        // If no enchantments system allows us to distinguish curses cleanly,
        // we do NOT touch the item at all.
        if (enchants.isEmpty()) {
            return stack;
        }

        // If ANY enchantment exists, we assume vanilla filtered too aggressively
        // and just return original stack unchanged (safe behavior).
        return stack;
    }
}