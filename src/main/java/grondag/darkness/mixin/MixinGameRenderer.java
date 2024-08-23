/*
 * This file is part of True Darkness and is licensed to the project under
 * terms that are compatible with the GNU Lesser General Public License.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership and licensing.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package grondag.darkness.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;

import grondag.darkness.Darkness;
import grondag.darkness.LightmapAccess;

//#if MC >= 12100
import net.minecraft.client.DeltaTracker;
//#elseif MC <= 12004
//$$ import com.mojang.blaze3d.vertex.PoseStack;
//#endif

@Mixin(GameRenderer.class)
@Environment(EnvType.CLIENT)
public class MixinGameRenderer {
    @Shadow
    private Minecraft minecraft;
    @Shadow
    private LightTexture lightTexture;

    @Inject(method = "renderLevel", at = @At(value = "HEAD"))
    // spotless:off
    //#if MC >= 12100
    private void onRenderLevel(DeltaTracker deltaTracker, CallbackInfo ci) {
    //#elseif MC >= 12005
    //$$ private void onRenderLevel(float tickDelta, long nanos, CallbackInfo ci) {
    //#else
    //$$ private void onRenderLevel(float tickDelta, long nanos, PoseStack matrixStack, CallbackInfo ci) {
    //#endif
    //spotless:on
        final LightmapAccess lightmap = (LightmapAccess) lightTexture;

        if (lightmap.darkness_isDirty()) {
            minecraft.getProfiler().push("lightTex");
            // spotless:off
            //#if MC >= 12100
            Darkness.updateLuminance(deltaTracker.getGameTimeDeltaTicks(), minecraft, (GameRenderer) (Object) this,
                    lightmap.darkness_prevFlicker());
            //#else
            //$$ Darkness.updateLuminance(tickDelta, minecraft, (GameRenderer) (Object) this, lightmap.darkness_prevFlicker());
            //#endif
            //spotless:on
            minecraft.getProfiler().pop();
        }
    }
}
