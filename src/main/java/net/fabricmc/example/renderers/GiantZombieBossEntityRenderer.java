package net.fabricmc.example.renderers;

import net.fabricmc.example.MoreBossesClient;
import net.fabricmc.example.entities.GiantZombieBossEntity;
import net.fabricmc.example.models.GiantZombieBossEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.GiantEntityRenderer;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.util.Identifier;

public class GiantZombieBossEntityRenderer extends MobEntityRenderer<GiantZombieBossEntity, BipedEntityModel<GiantZombieBossEntity>> {

    private final float scale = 6;

    public GiantZombieBossEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new GiantZombieBossEntityModel(context.getPart(MoreBossesClient.MODEL_GIANT_BOSS_LAYER)), 3);
    }

    @Override
    public Identifier getTexture(GiantZombieBossEntity entity) {
        return new Identifier("more_bosses", "textures/entity/giant_zombie_boss/giant_zombie_boss.png");
    }

    protected void scale(GiantZombieBossEntity entity, MatrixStack matrixStack, float f) {
        matrixStack.scale(this.scale, this.scale, this.scale);
    }
}
