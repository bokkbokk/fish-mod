package net.bokkbokk.fishmod;

import net.bokkbokk.fishmod.client.RedScreenOverlay;
import net.bokkbokk.fishmod.entity.ModEntities;
import net.bokkbokk.fishmod.entity.client.CrawlerSmallModel;
import net.bokkbokk.fishmod.entity.client.CrawlerSmallRenderer;
import net.bokkbokk.fishmod.networking.ModMessages;
import net.bokkbokk.fishmod.networking.packet.RedScreenOffS2CPacket;
import net.bokkbokk.fishmod.networking.packet.RedScreenOnS2CPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class FishModClient implements ClientModInitializer {

    private static boolean redScreen = false;

    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();

        EntityModelLayerRegistry.registerModelLayer(CrawlerSmallModel.CRAWLER_SMALL,CrawlerSmallModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.CRAWLER_SMALL, CrawlerSmallRenderer::new);

        ClientPlayNetworking.registerGlobalReceiver(RedScreenOnS2CPacket.ID, (payload, context) -> {
            context.client().execute(() -> {
                setRedScreenOn();
            });
        });
        ClientPlayNetworking.registerGlobalReceiver(RedScreenOffS2CPacket.ID, (payload, context) -> {
            context.client().execute(() -> {
                setRedScreenOff();
            });
        });



        HudRenderCallback.EVENT.register((drawContext, tickDeltaManager) -> {
            if (redScreen) {
                RedScreenOverlay redScreenOverlay = new RedScreenOverlay();
                redScreenOverlay.renderRedOverlay(drawContext);
            }
        });



    }
    public static void setRedScreenOn( ) {
        System.out.println("RED TURN ON");
        redScreen = true;
    }
    public static void setRedScreenOff( ) {
        System.out.println("RED TURN OFF");
        redScreen = false;
    }

}
