package net.fabricmc.example;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.example.MoreBosses;
import net.fabricmc.example.entities.GiantBossEntity;
import net.fabricmc.example.models.GiantBossEntityModel;
import net.fabricmc.example.models.TowerOfUndyingEntityModel;
import net.fabricmc.example.models.ZombieScarecrowEntityModel;
import net.fabricmc.example.renderers.GiantBossEntityRenderer;
import net.fabricmc.example.renderers.TowerOfUndyingRenderer;
import net.fabricmc.example.renderers.ZombieScarecrowRenderer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class MoreBossesClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_ZOMBIE_SCARECROW_LAYER = new EntityModelLayer(new Identifier("more_bosses", "zombie_scarecrow"), "main");
    public static final EntityModelLayer MODEL_TOWER_OF_UNDYING_LAYER = new EntityModelLayer(new Identifier("more_bosses", "tower_of_undying"), "main");

    public static final EntityModelLayer MODEL_GIANT_BOSS_LAYER = new EntityModelLayer(new Identifier("more_bosses", "giant_boss"), "main");
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.INSTANCE.register(MoreBosses.ZOMBIE_SCARECROW_ENTITY, (context) -> {
            return new ZombieScarecrowRenderer(context);
        });

        EntityModelLayerRegistry.registerModelLayer(MODEL_ZOMBIE_SCARECROW_LAYER, ZombieScarecrowEntityModel::getTexturedModelData);

        EntityRendererRegistry.INSTANCE.register(MoreBosses.TOWER_OF_UNDYING_ENTITY, (context) -> {
            return new TowerOfUndyingRenderer(context);
        });

        EntityModelLayerRegistry.registerModelLayer(MODEL_TOWER_OF_UNDYING_LAYER, TowerOfUndyingEntityModel::getTexturedModelData);


        EntityRendererRegistry.INSTANCE.register(MoreBosses.GIANT_BOSS_ENTITY, (context) -> {
            return new GiantBossEntityRenderer(context, 3);
        });

        EntityModelLayerRegistry.registerModelLayer(MODEL_GIANT_BOSS_LAYER, GiantBossEntityModel::getTexturedModelData);
    }
}
