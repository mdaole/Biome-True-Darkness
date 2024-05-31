package grondag.darkness;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

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
}
