package ink.huix.autoprice;

import ink.huix.autoprice.event.AutoPriceEvents;
import ink.huix.autoprice.mixins.MinecraftMixin;
import net.xiaoyu233.fml.AbstractMod;
import net.xiaoyu233.fml.classloading.Mod;
import net.xiaoyu233.fml.config.InjectionConfig;
import net.xiaoyu233.fml.reload.event.MITEEvents;
import org.spongepowered.asm.mixin.MixinEnvironment;

import javax.annotation.Nonnull;

@Mod
public class MITEDEV extends AbstractMod {
    @Override
    public void preInit() {
    }

    @Override
    public void postInit() {
        MITEEvents.MITE_EVENT_BUS.register(new AutoPriceEvents());
    }

    @Nonnull
    @Override
    public InjectionConfig getInjectionConfig() {
        return InjectionConfig.Builder.of("AutoPrice", MinecraftMixin.class.getPackage(), MixinEnvironment.Phase.INIT).setRequired().build();
    }

    @Override
    public String modId() {
        return "AutoPrice";
    }

    @Override
    public int modVerNum() {
        return 100;
    }

    @Override
    public String modVerStr() {
        return "0.1.0-BETA";
    }
}

