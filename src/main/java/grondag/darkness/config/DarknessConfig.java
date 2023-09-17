package grondag.darkness.config;

import grondag.darkness.Darkness;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = Darkness.MODID)
public class DarknessConfig implements ConfigData {

    public boolean darkOverworld = true;
    public boolean darkDefault = true;
    public boolean darkNether = true;
    public double darkNetherFog = 0.5;
    public boolean darkEnd = true;
    public double darkEndFog = 0.0;
    public boolean darkSkyless = true;
    public boolean blockLightOnly = false;
    public boolean ignoreMoonPhase = false;

    public static DarknessConfig getInstance() {
        return AutoConfig.getConfigHolder(DarknessConfig.class).getConfig();
    }

}
