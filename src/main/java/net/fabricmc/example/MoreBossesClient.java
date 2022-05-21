package net.fabricmc.example;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.example.MoreBosses;
import net.fabricmc.example.models.ZombieScarecrowEntityModel;
import net.fabricmc.example.renderers.ZombieScarecrowRenderer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class MoreBossesClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_ZOMBIE_SCARECROW_LAYER = new EntityModelLayer(new Identifier("more_bosses", "zombie_scarecrow"), "main");
    @Override
    public void onInitializeClient() {
        /*
         * Registers our Cube Entity's renderer, which provides a model and texture for the entity.
         *
         * Entity Renderers can also manipulate the model before it renders based on entity context (EndermanEntityRenderer#render).
         */
        EntityRendererRegistry.INSTANCE.register(MoreBosses.ZOMBIE_SCARECROW, (context) -> {
            return new ZombieScarecrowRenderer(context);
        });

        EntityModelLayerRegistry.registerModelLayer(MODEL_ZOMBIE_SCARECROW_LAYER, ZombieScarecrowEntityModel::getTexturedModelData);
    }
}
