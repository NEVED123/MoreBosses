package net.fabricmc.more_bosses.renderers;

import net.fabricmc.more_bosses.MoreBossesClient;
import net.fabricmc.more_bosses.entities.GiantPillagerBossEntity;
import net.fabricmc.more_bosses.models.GiantPillagerBossEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class GiantPillagerBossEntityRenderer extends MobEntityRenderer<GiantPillagerBossEntity, BipedEntityModel<GiantPillagerBossEntity>> {

    private final float scale = 6;
    public GiantPillagerBossEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new GiantPillagerBossEntityModel(context.getPart(MoreBossesClient.MODEL_GIANT_PILlAGER_LAYER)), 2);
    }

    public Identifier getTexture(GiantPillagerBossEntity entity){
        return new Identifier("more_bosses", "textures/entity/giant_pillager_boss/giant_pillager_boss.png");
    }

    protected void scale(GiantPillagerBossEntity entity, MatrixStack matrixStack, float f) {
        matrixStack.scale(this.scale, this.scale, this.scale);
    }
}
