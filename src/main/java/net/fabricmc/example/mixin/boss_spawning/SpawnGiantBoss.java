package net.fabricmc.example.mixin.boss_spawning;

import net.fabricmc.example.MoreBosses;
import net.fabricmc.example.entities.GiantBossEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//C:\Users\neved\OneDrive\Documents\Minecraft_Mods
// \MoreMobs\.gradle\loom-cache\1.18.2\net.fabricmc.yarn.1_18_2.1.18.2+build.1-v2
// \minecraft-project-@-merged-named.jar!\net\minecraft\world\biome\SpawnSettings.class

@Mixin(DefaultBiomeFeatures.class)
public class SpawnGiantBoss {

    private static final EntityType<GiantBossEntity> GIANT_BOSS = MoreBosses.GIANT_BOSS_ENTITY;

    @Inject(at = @At("Head"), method = "addMonsters(Lnet/minecraft/world/biome/SpawnSettings$Builder;IIIZ)V", cancellable = true)
    private static void addMonsters(SpawnSettings.Builder builder, int zombieWeight, int zombieVillagerWeight, int skeletonWeight, boolean drowned, CallbackInfo ci){
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(GIANT_BOSS,1, 1, 1));
    }
}
