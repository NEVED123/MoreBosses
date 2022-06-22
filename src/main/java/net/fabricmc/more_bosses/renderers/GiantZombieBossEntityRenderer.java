package net.fabricmc.more_bosses.renderers;

import net.fabricmc.more_bosses.MoreBossesClient;
import net.fabricmc.more_bosses.entities.GiantZombieBossEntity;
import net.fabricmc.more_bosses.models.GiantZombieBossEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
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
