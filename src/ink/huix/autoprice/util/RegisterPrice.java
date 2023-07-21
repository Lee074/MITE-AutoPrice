package ink.huix.autoprice.util;

import net.minecraft.*;

import java.util.HashMap;

public class RegisterPrice {


    public static void registerPriceForShaped(ItemStack out, Object... input){
        double buyPrice = 0D;
        ItemStack[] itemStacks = getRecipeItemStack(input);
        for (ItemStack stack : itemStacks) {
            if(stack != null) {
                if (stack.getItem().buyPriceArray.get(stack.getItemSubtype()) != null) {
                    Item item = stack.getItem();

                    if (item instanceof ItemBlock) {
                        Block block = ((ItemBlock) item).getBlock();
                        if (!block.canBeCarried()) {
                            break;
                        }

                        ItemStack itemBlock = new ItemStack(block);
                        if ((double) itemBlock.getItem().buyPriceArray.get(itemBlock.getItemSubtype()) * itemBlock.stackSize <= 0) {
                            buyPrice = 0;
                            break;
                        }

                        buyPrice += (double) itemBlock.getItem().buyPriceArray.get(itemBlock.getItemSubtype()) * itemBlock.stackSize;
                        continue;
                    }

                    if (item instanceof ItemWorldMap) {
                        break;
                    }

                    if ((double) item.buyPriceArray.get(stack.getItemSubtype()) * stack.stackSize <= 0) {
                        buyPrice = 0;
                        break;
                    }
                    buyPrice += (double) item.buyPriceArray.get(stack.getItemSubtype()) * stack.stackSize;
                }
            }
        }

        if(buyPrice > 0D && ((double)out.getItem().soldPriceArray.get(out.getItemSubtype()))<= 0){
            out.getItem().setSoldPrice(buyPrice / out.stackSize);
        }
    }


    public static void registerPriceForShapeless(ItemStack out, Object... input){
        double buyPrice = 0D;
        for (Object o : input) {
            if(o != null) {
                if(o instanceof Block){
                    Block block = (Block) o;
                    if(!block.canBeCarried()) {
                        break;
                    }

                    ItemStack itemBlock = new ItemStack(block);
                    if (itemBlock.getItem().buyPriceArray.get(itemBlock.getItemSubtype()) != null){
                        if ((double)itemBlock.getItem().buyPriceArray.get(itemBlock.getItemSubtype()) <= 0){
                            buyPrice = 0;
                            break;
                        }
                        buyPrice += (double)itemBlock.getItem().buyPriceArray.get(itemBlock.getItemSubtype());
                    }
                }

                if (o instanceof Item){
                    ItemStack item = new ItemStack((Item) o);
                    if (item.getItem().buyPriceArray.get(item.getItemSubtype()) != null) {
                        if ((double)item.getItem().buyPriceArray.get(item.getItemSubtype()) <= 0){
                            buyPrice = 0;
                            break;
                        }
                        buyPrice += (double)item.getItem().buyPriceArray.get(item.getItemSubtype());
                    }

                }

                if (o instanceof ItemStack){
                    ItemStack itemStack = (ItemStack) o;
                    if(itemStack.getItem().buyPriceArray.get(itemStack.getItemSubtype()) != null){
                        if ((double)itemStack.getItem().buyPriceArray.get(itemStack.getItemSubtype()) * itemStack.stackSize <= 0){
                            buyPrice = 0;
                            break;
                        }
                        buyPrice += (double)itemStack.getItem().buyPriceArray.get(itemStack.getItemSubtype()) * itemStack.stackSize;
                    }
                }

                if (o instanceof ItemWorldMap){
                    buyPrice =0;
                    break;
                }
            }
        }

        if(buyPrice > 0D && ((double)out.getItem().soldPriceArray.get(out.getItemSubtype()))<= 0){
            out.getItem().setSoldPrice(buyPrice / out.stackSize);
        }
    }


    private static ItemStack[] getRecipeItemStack(Object... par2ArrayOfObj) {
        StringBuilder stringBuilder = new StringBuilder();
        int var0 = 0;
        int var1 = 0;
        int var2 = 0;
        if (par2ArrayOfObj[var0] instanceof String[]) {
            String[] input = (String[]) par2ArrayOfObj[var0++];

            for (String s : input) {
                ++var2;
                var1 = s.length();
                stringBuilder.append(s);
            }
        } else {
            while(par2ArrayOfObj[var0] instanceof String) {
                String input_ = (String)par2ArrayOfObj[var0++];
                ++var2;
                var1 = input_.length();
                stringBuilder.append(input_);
            }
        }

        HashMap hashMap;
        for(hashMap = new HashMap(); var0 < par2ArrayOfObj.length; var0 += 2) {
            Character var13 = (Character)par2ArrayOfObj[var0];
            ItemStack stack = null;
            if (par2ArrayOfObj[var0 + 1] instanceof Item) {
                stack = new ItemStack((Item)par2ArrayOfObj[var0 + 1]);
            } else if (par2ArrayOfObj[var0 + 1] instanceof Block) {
                stack = new ItemStack((Block)par2ArrayOfObj[var0 + 1], 1, 32767);
            } else if (par2ArrayOfObj[var0 + 1] instanceof ItemStack) {
                stack = (ItemStack)par2ArrayOfObj[var0 + 1];
            } else {
                Minecraft.setErrorMessage("Invalid recipe component for "
//                        + par1ItemStack.getDisplayName()
                );
            }

            hashMap.put(var13, stack);
        }

        ItemStack[] itemStacks = new ItemStack[var1 * var2];

        for(int i = 0; i < var1 * var2; ++i) {
            char charAt = stringBuilder.charAt(i);
            if (hashMap.containsKey(charAt)) {
                itemStacks[i] = ((ItemStack)hashMap.get(charAt)).copy();
            } else {
                itemStacks[i] = null;
            }
        }

        return itemStacks;
    }
}
