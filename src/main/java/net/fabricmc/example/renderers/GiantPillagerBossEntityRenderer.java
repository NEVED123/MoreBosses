package net.fabricmc.example.renderers;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.GiantEntityRenderer;

public class GiantPillagerBossEntityRenderer extends GiantEntityRenderer {
    public GiantPillagerBossEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, 6);
    }
}
