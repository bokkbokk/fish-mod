package net.bokkbokk.fishmod.entity.client;

import net.bokkbokk.fishmod.FishMod;
import net.bokkbokk.fishmod.entity.custom.CrawlerSmallEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class CrawlerSmallRenderer extends MobEntityRenderer<CrawlerSmallEntity, CrawlerSmallModel<CrawlerSmallEntity>> {
    public CrawlerSmallRenderer(EntityRendererFactory.Context context ) {
        super(context, new CrawlerSmallModel<>(context.getPart(CrawlerSmallModel.CRAWLER_SMALL)), 0.3f);
    }

    @Override
    public Identifier getTexture(CrawlerSmallEntity entity) {
        return Identifier.of(FishMod.MOD_ID,"textures/entity/crawler_small/crawler_small.png");
    }

    @Override
    public void render(CrawlerSmallEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
