package ink.huix.autoprice.mixins.util;


import ink.huix.autoprice.util.RegisterPrice;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.util.RecipesArgs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;

@Mixin(CraftingManager.class)
public class CraftingManagerMixin {

    @Inject(method = "addRecipe(Lnet/minecraft/ItemStack;Z[Ljava/lang/Object;)Lnet/minecraft/ShapedRecipes;", at = @At(value = "HEAD"))
    public void addPriceForShaped(ItemStack par1ItemStack, boolean include_in_lowest_crafting_difficulty_determination, Object[] par2ArrayOfObj, CallbackInfoReturnable<ShapedRecipes> cir) {
        RegisterPrice.registerPriceForShaped(par1ItemStack, par2ArrayOfObj);
    }

    @Inject(method = "addShapelessRecipe(Lnet/minecraft/ItemStack;Z[Ljava/lang/Object;)Lnet/minecraft/ShapelessRecipes;", at = @At(value = "HEAD"))
    public void addPriceForShapeless(ItemStack par1ItemStack, boolean include_in_lowest_crafting_difficulty_determination, Object[] par2ArrayOfObj, CallbackInfoReturnable<ShapelessRecipes> cir) {
        RegisterPrice.registerPriceForShapeless(par1ItemStack, par2ArrayOfObj);
    }
}
