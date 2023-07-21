package ink.huix.autoprice.event;

import com.google.common.eventbus.Subscribe;
import net.minecraft.*;
import net.xiaoyu233.fml.reload.event.PlayerLoggedInEvent;

public class AutoPriceEvents {
//    @Subscribe
//    public void handleChatCommand(HandleChatCommandEvent event) {
//        String command = event.getCommand();
//        EntityPlayer player = event.getPlayer();
//        if (command.startsWith("login")) {
//            List enchantmentList = EnchantmentManager.buildEnchantmentList(this.random, player.getHeldItemStack(),
//                    this.calcEnchantmentLevelsForSlot(this.random, 2, 24, player.getHeldItemStack()));
//            for (Object o : enchantmentList) {
//                EnchantmentInstance instance = (EnchantmentInstance) o;
//                player.sendChatToPlayer(ChatMessage.createFromText("附魔: " + instance.enchantmentobj.getTranslatedName(null)));
//                player.sendChatToPlayer(ChatMessage.createFromText("等级: " + instance.enchantmentLevel));
//            }
//
//            event.setExecuteSuccess(true);
//        }
//    }

    @Subscribe
    public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
        ServerPlayer player = event.getPlayer();
        player.sendChatToPlayer(ChatMessage.createFromText("Auto Price Successful Loaded"));
    }

}




