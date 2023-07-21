package ink.huix.autoprice.mixins.util;

import ink.huix.autoprice.util.RegisterPrice;
import net.minecraft.ItemStack;
import net.xiaoyu233.mitemod.miteite.util.RecipeRegister;
import net.xiaoyu233.mitemod.miteite.util.RecipesArgs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecipeRegister.class)
public class RecipeRegisterMixin {

    @Inject(method = "registerShapedRecipe", at = @At(value = "RETURN"))
    public void addPriceForShaped(ItemStack out, boolean include_in_lowest_crafting_difficulty_determination, Object[] input, CallbackInfoReturnable<RecipesArgs> cir) {
        RegisterPrice.registerPriceForShaped(out, input);
    }

    @Inject(method = "registerShapelessRecipe", at = @At(value = "RETURN"))
    public void addPriceForShapeless(ItemStack out, boolean include_in_lowest_crafting_difficulty_determination, Object[] input, CallbackInfoReturnable<RecipesArgs> cir) {
        RegisterPrice.registerPriceForShapeless(out, input);
    }

}
