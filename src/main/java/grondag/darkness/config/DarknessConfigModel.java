package grondag.darkness.config;

import grondag.darkness.DarknessInit;
import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.Sync;

// owo-config documentation: https://docs.wispforest.io/owo/config/getting-started/

@Modmenu(modId = DarknessInit.MOD_ID)
@Sync(Option.SyncMode.OVERRIDE_CLIENT)
@Config(name = DarknessInit.MOD_ID, wrapperName = "DarknessConfig")
public class DarknessConfigModel {
    public boolean darkOverworld = true;
    public boolean darkDefault = true;
    public boolean darkNether = true;
    public double darkNetherFog = 0.5;
    public boolean darkEnd = true;
    public double darkEndFog = 0.0;
    public boolean darkSkyless = true;
    public boolean blockLightOnly = false;
    public boolean ignoreMoonPhase = false;
    public boolean requireMod = false;
}