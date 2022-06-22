package net.fabricmc.more_bosses.models;

import net.fabricmc.more_bosses.entities.ZombieScarecrowEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class ZombieScarecrowEntityModel extends BipedEntityModel<ZombieScarecrowEntity> {
    //private final ModelPart base;

    public ZombieScarecrowEntityModel(ModelPart modelPart){
        super(modelPart, RenderLayer::getEntityCutoutNoCull);
    }

    public static TexturedModelData getTexturedModelData() {
        return TexturedModelData.of(getModelData(Dilation.NONE, 0F), 64, 64);
    }

    @Override
    public void setAngles(ZombieScarecrowEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        getBodyParts().forEach((modelRenderer) -> {
            modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        });
        getHeadParts().forEach((modelRenderer) -> {
            modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        });
    }

}
