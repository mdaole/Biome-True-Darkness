package grondag.darkness.compat;


import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;

// Imports for Apoli NightVisionPower compat
//#if MC >= 12100
import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.type.NightVisionPowerType;
//#elseif MC <= 12004
//$$ import io.github.apace100.apoli.component.PowerHolderComponent;
//$$ import io.github.apace100.apoli.power.NightVisionPower;
//#endif

public class ApoliCompat {

    public boolean isApoliNightVisionPower(Minecraft client) {
        if(FabricLoader.getInstance().isModLoaded("apoli")) {
            // spotless:off
            //#if MC >= 12100
            return PowerHolderComponent.hasPowerType(client.player, NightVisionPowerType.class);
            //#elseif MC <= 12004
            //$$ return PowerHolderComponent.hasPower(client.player, NightVisionPower.class);
            //#elseif MC == 12006
            //$$ return false;
            //#endif
            //spotless:on
        }
        else{
            return false;
        }
    }
}
