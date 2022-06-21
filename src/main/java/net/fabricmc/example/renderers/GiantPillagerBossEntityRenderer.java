package net.fabricmc.example.renderers;

import net.fabricmc.example.entities.GiantPillagerBossEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.GiantEntityRenderer;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.util.Identifier;

public class GiantPillagerBossEntityRenderer extends GiantEntityRenderer {
    public GiantPillagerBossEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, 6);
    }

    public Identifier getTexture(GiantEntity entity){
        return new Identifier("more_bosses", "textures/entity/giant_pillager_boss/giant_pillager_boss.png");
    }
}
