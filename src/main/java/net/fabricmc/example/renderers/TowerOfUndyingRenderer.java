package net.fabricmc.example.renderers;

import net.fabricmc.example.MoreBossesClient;
import net.fabricmc.example.entities.TowerOfUndyingEntity;
import net.fabricmc.example.models.TowerOfUndyingEntityModel;
import net.fabricmc.example.models.ZombieScarecrowEntityModel;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class TowerOfUndyingRenderer extends LivingEntityRenderer<TowerOfUndyingEntity, TowerOfUndyingEntityModel> {

    public TowerOfUndyingRenderer(EntityRendererFactory.Context context){
        super(context, new TowerOfUndyingEntityModel(context.getPart(MoreBossesClient.MODEL_TOWER_OF_UNDYING_LAYER)), 0.5f);

    }

    @Override
    public Identifier getTexture(TowerOfUndyingEntity entity) {
        return new Identifier("more_bosses", "textures/entity/tower_of_undying/tower_of_undying.png");
    }

}
