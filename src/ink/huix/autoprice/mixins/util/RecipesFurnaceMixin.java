package ink.huix.autoprice.mixins.util;


import net.minecraft.ItemStack;
import net.minecraft.RecipesFurnace;
import net.minecraft.TileEntityFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RecipesFurnace.class)
public class RecipesFurnaceMixin {

    @Inject(method = "addSmelting", at = @At("RETURN"))
    private void test(int input_item_id, ItemStack output_item_stack, CallbackInfo callbackInfo){
        if (output_item_stack != null) {
            ItemStack inputItemStack = new ItemStack(input_item_id);
            double input = (double)inputItemStack.getItem().soldPriceArray.get(inputItemStack.getItemSubtype());
            if(output_item_stack.getPrice().soldPrice <= 0){
                output_item_stack.getItem().setSoldPrice(input + TileEntityFurnace.getHeatLevelRequired(input_item_id));
            }
        }
    }
}
