package net.bokkbokk.fishmod.entity.client;

import net.bokkbokk.fishmod.FishMod;
import net.bokkbokk.fishmod.entity.custom.CrawlerSmallEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class CrawlerSmallModel<T extends CrawlerSmallEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer CRAWLER_SMALL = new EntityModelLayer(Identifier.of(FishMod.MOD_ID,"crawler_small"),"main");

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart eyes;
    private final ModelPart Rightlegs;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart Leftlegs;
    private final ModelPart leg4;
    private final ModelPart leg5;
    private final ModelPart leg6;


    public CrawlerSmallModel(ModelPart root) {
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.eyes = this.body.getChild("eyes");
        this.Rightlegs = this.root.getChild("Rightlegs");
        this.leg1 = this.Rightlegs.getChild("leg1");
        this.leg2 = this.Rightlegs.getChild("leg2");
        this.leg3 = this.Rightlegs.getChild("leg3");
        this.Leftlegs = this.root.getChild("Leftlegs");
        this.leg4 = this.Leftlegs.getChild("leg4");
        this.leg5 = this.Leftlegs.getChild("leg5");
        this.leg6 = this.Leftlegs.getChild("leg6");

    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -6.0F, -4.0F, 6.0F, 2.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData eyes = body.addChild("eyes", ModelPartBuilder.create().uv(0, 2).cuboid(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 2).cuboid(-3.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -6.0F, 3.0F));

        ModelPartData Rightlegs = root.addChild("Rightlegs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData leg1 = Rightlegs.addChild("leg1", ModelPartBuilder.create().uv(0, 11).cuboid(-0.5F, 0.5F, -0.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(3.5F, -4.5F, -3.5F));

        ModelPartData leg2 = Rightlegs.addChild("leg2", ModelPartBuilder.create().uv(4, 11).cuboid(-0.5F, 0.5F, -0.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(3.5F, -4.5F, 0.5F));

        ModelPartData leg3 = Rightlegs.addChild("leg3", ModelPartBuilder.create().uv(8, 11).cuboid(-0.5F, 0.5F, -0.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(3.5F, -4.5F, 4.5F));

        ModelPartData Leftlegs = root.addChild("Leftlegs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData leg4 = Leftlegs.addChild("leg4", ModelPartBuilder.create().uv(12, 11).cuboid(-0.5F, 0.5F, -0.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.5F, -4.5F, -3.5F));

        ModelPartData leg5 = Leftlegs.addChild("leg5", ModelPartBuilder.create().uv(0, 16).cuboid(-0.5F, 0.5F, -0.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.5F, -4.5F, 0.5F));

        ModelPartData leg6 = Leftlegs.addChild("leg6", ModelPartBuilder.create().uv(4, 16).cuboid(-0.5F, 0.5F, -0.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.5F, -4.5F, 4.5F));
        return TexturedModelData.of(modelData, 32, 32);
    }
    @Override
    public void setAngles(CrawlerSmallEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.animateMovement(CrawlerSmallAnimations.WALK,limbSwing,limbSwingAmount,2f,2.5f);
        this.updateAnimation(entity.idleAnimationState, CrawlerSmallAnimations.IDLE, ageInTicks,1f);

    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay,int color) {
        root.render(matrices, vertexConsumer, light, overlay, color);
//        Rightlegs.render(matrices, vertexConsumer, light, overlay, color);
//        Leftlegs.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return root;
    }
}
