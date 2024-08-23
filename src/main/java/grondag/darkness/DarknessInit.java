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
                sender.sendPacket(ResourceLocation.parse(Darkness.MODID), buf);
            });

            ServerLoginNetworking.registerGlobalReceiver(ResourceLocation.parse(Darkness.MODID),
                    (server, handler, understood, buf, synchronizer, sender) -> {
                        if (!understood) {
                            handler.disconnect(Component.literal("You are missing the mod: " + Darkness.MODNAME)); // you
                                                                                                                   // can
                                                                                                                   // replace
                                                                                                                   // the
                                                                                                                   // other
                            // texts with translatable
                            // keys, but not this one
                            // since they wont have them
                        }
                    });
        }
    }
}
