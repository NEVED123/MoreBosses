package net.fabricmc.example.models;

import net.fabricmc.example.entities.TowerOfUndyingEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.include.com.google.common.collect.ImmutableList;

public class TowerOfUndyingEntityModel extends SinglePartEntityModel<TowerOfUndyingEntity> {

    private ModelPart base;

    public TowerOfUndyingEntityModel(ModelPart modelPart){
        this.base = modelPart;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("base", ModelPartBuilder.create().uv(0, 0).cuboid(-2F, 8F, -2F, 4F, 16F, 4F), ModelTransform.pivot(0F, 0F, 0F));
        modelPartData.addChild("right_top_wing", ModelPartBuilder.create().uv(0,20).cuboid(-1F, 12F, 2F, 2F, 2F, 8F), ModelTransform.pivot(0F,0F,0F));
        modelPartData.addChild("left_top_wing", ModelPartBuilder.create().uv(0,20).mirrored().cuboid(-1F,12F, -10F, 2F, 2F, 8F), ModelTransform.pivot(0F, 0F,0F));
        modelPartData.addChild("right_bottom_wing", ModelPartBuilder.create().uv(0, 30).cuboid(-1F, 16F, 2F, 2F, 2F, 6F), ModelTransform.pivot(0F,0F,0F));
        modelPartData.addChild("left_bottom_wing", ModelPartBuilder.create().uv(0,30).mirrored().cuboid(-1F,16F, -8F, 2F, 2F, 6F), ModelTransform.pivot(0F, 0F,0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public ModelPart getPart() {
        return this.base;
    }


    @Override
    public void setAngles(TowerOfUndyingEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }
}

