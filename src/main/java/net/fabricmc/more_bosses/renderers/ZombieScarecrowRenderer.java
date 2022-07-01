package net.fabricmc.more_bosses.renderers;

import net.fabricmc.more_bosses.entities.TowerOfUndyingEntity;
import net.fabricmc.more_bosses.entities.ZombieScarecrowEntity;
import net.fabricmc.more_bosses.MoreBossesClient;
import net.fabricmc.more_bosses.models.ZombieScarecrowEntityModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ZombieScarecrowRenderer extends LivingEntityRenderer<ZombieScarecrowEntity, BipedEntityModel<ZombieScarecrowEntity>> {
    public ZombieScarecrowRenderer(EntityRendererFactory.Context context){
        super(context, new ZombieScarecrowEntityModel(context.getPart(MoreBossesClient.MODEL_ZOMBIE_SCARECROW_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(ZombieScarecrowEntity entity) {
        return new Identifier("more_bosses", "textures/entity/zombie_scarecrow/zombie_scarecrow.png");
    }

    protected void renderLabelIfPresent(ZombieScarecrowEntity entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light){
        if(entity.hasCustomName()){
            super.renderLabelIfPresent(entity, text, matrices, vertexConsumers, light);
        }
    }


}


