package net.fabricmc.more_bosses.mixin.flee_goals;

import net.fabricmc.more_bosses.entities.TowerOfUndyingEntity;
import net.fabricmc.more_bosses.entities.ZombieScarecrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.fabricmc.more_bosses.entities.ZombieScarecrowEntity.FLEE_DISTANCE;

@Mixin(RaiderEntity.class)
public class ScarecrowRaiderFleeGoal extends MobEntity {
    public ScarecrowRaiderFleeGoal(EntityType<? extends MobEntity> entityType, World world){
        super(entityType, world);
    }
    @Inject(at = @At("HEAD"), method = "initGoals()V")
    private void init(CallbackInfo info)  {
        this.goalSelector.add(1, new FleeEntityGoal(((RaiderEntity)(Object)this),
                ZombieScarecrowEntity.class, FLEE_DISTANCE, 1.0, 1.2));
        this.goalSelector.add(1, new ActiveTargetGoal(((RaiderEntity)(Object)this),
                TowerOfUndyingEntity.class, false));
    }

}
