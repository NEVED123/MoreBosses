package net.fabricmc.example.renderers;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.GiantEntityRenderer;

public class GiantZombieBossEntityRenderer extends GiantEntityRenderer {
    public GiantZombieBossEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, 6);
    }

}
