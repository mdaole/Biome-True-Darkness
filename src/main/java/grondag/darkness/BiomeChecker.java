package grondag.darkness;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import java.util.Properties;

public class BiomeChecker {
    public static Biome GetCurrentBiome() {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player != null) {
            Level world = minecraft.player.level();
            BlockPos playerPos = minecraft.player.blockPosition();
            Biome biome = world.getBiome(playerPos).value();
            return biome;
        }

        return null;
    }

    public static boolean ShouldBiomeBeDark(ClientLevel world)
    {
        //Check if the Biome Should be dark
        //Get Biome string
        ResourceLocation biomeWeAreIn = world.registryAccess().registryOrThrow(Registries.BIOME).getKey(BiomeChecker.GetCurrentBiome());
        JsonArray array = Darkness.darknessBiomes.getAsJsonArray("Biomes");
        boolean insideSpecifiedBiome = false;
        for (JsonElement element : array) {
            if (element.getAsString().equals(biomeWeAreIn.toString())) {
                insideSpecifiedBiome = true;
                break;
            }
        }

        return Darkness.invertBiomeDarkness ? insideSpecifiedBiome : !insideSpecifiedBiome;
    }
}
