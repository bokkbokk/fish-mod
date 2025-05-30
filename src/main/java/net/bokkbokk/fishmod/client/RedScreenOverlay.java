package net.bokkbokk.fishmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.world.timer.Timer;

public class RedScreenOverlay {
    public void renderRedOverlay(DrawContext drawContext) {

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);

        drawContext.fill(0, 0, drawContext.getScaledWindowWidth(), drawContext.getScaledWindowHeight(), 0x55FF0000);

        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();

        RenderSystem.setShaderColor(1f,0f,0f,1f);




    }
}
