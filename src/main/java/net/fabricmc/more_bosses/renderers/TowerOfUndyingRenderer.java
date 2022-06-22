package net.fabricmc.more_bosses.renderers;

import net.fabricmc.more_bosses.MoreBossesClient;
import net.fabricmc.more_bosses.entities.TowerOfUndyingEntity;
import net.fabricmc.more_bosses.models.TowerOfUndyingEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
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
