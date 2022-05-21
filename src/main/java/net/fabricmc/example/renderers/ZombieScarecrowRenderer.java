package net.fabricmc.example.renderers;

import net.fabricmc.example.entities.ZombieScarecrowEntity;
import net.fabricmc.example.MoreBossesClient;
import net.fabricmc.example.models.ZombieScarecrowEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.util.Identifier;

public class ZombieScarecrowRenderer extends LivingEntityRenderer<ZombieScarecrowEntity, ZombieScarecrowEntityModel> {
    public ZombieScarecrowRenderer(EntityRendererFactory.Context context){
        super(context, new ZombieScarecrowEntityModel(context.getPart(MoreBossesClient.MODEL_ZOMBIE_SCARECROW_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(ZombieScarecrowEntity entity) {
        return new Identifier("more_bosses", "textures/entity/zombie_scarecrow/zombie_scarecrow.png");
    }


}


