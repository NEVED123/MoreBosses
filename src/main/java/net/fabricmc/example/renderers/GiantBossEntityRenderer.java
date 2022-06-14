package net.fabricmc.example.renderers;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.GiantEntityRenderer;

public class GiantBossEntityRenderer extends GiantEntityRenderer {
    public GiantBossEntityRenderer(EntityRendererFactory.Context ctx, float scale) {
        super(ctx, scale);
    }
}
