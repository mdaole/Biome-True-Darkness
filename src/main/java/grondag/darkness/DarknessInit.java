package grondag.darkness;

import grondag.darkness.config.DarknessConfig;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerLoginConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class DarknessInit implements ModInitializer {
    public static final DarknessConfig CONFIG = DarknessConfig.createAndLoad();

    @Override
    public void onInitialize() {

        if (CONFIG.requireMod()) {
            int protocolVersion = 0;

            ServerLoginConnectionEvents.QUERY_START.register((handler, server, sender, synchronizer) -> {
                FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer(5));
                buf.writeVarInt(protocolVersion);
                // spotless:off
                //#if MC >= 12100
                sender.sendPacket(ResourceLocation.parse(Darkness.MODID), buf);
                //#elseif MC < 12100
                //$$ sender.sendPacket(ResourceLocation.tryParse(Darkness.MODID), buf);
                //#endif
                //spotless:on
            });
            // spotless:off
            //#if MC >= 12100
            ServerLoginNetworking.registerGlobalReceiver(ResourceLocation.parse(Darkness.MODID),
                    //#elseif MC < 12100
                    //$$ ServerLoginNetworking.registerGlobalReceiver(ResourceLocation.tryParse(Darkness.MODID),
                    //#endif
                    //spotless:on
                    (server, handler, understood, buf, synchronizer, sender) -> {
                        if (!understood) {
                            handler.disconnect(Component.literal("You are missing the mod: " + Darkness.MODNAME));
                        }
                    });
        }
    }
}
