package net.fabricmc.example.renderers;

import net.fabricmc.example.entities.GiantPillagerBossEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.GiantEntityRenderer;
import net.minecraft.util.Identifier;

public class GiantBossEntityRenderer extends GiantEntityRenderer {
    public GiantBossEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, 6);
    }

}
