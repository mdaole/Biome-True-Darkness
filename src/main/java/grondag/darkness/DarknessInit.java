package grondag.darkness;

import grondag.darkness.config.DarknessConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class DarknessInit implements ModInitializer {
    @Override
    public void onInitialize() {
        AutoConfig.register(DarknessConfig.class, JanksonConfigSerializer::new);
    }
}
