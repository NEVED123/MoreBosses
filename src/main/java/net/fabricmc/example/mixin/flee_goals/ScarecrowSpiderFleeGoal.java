package net.fabricmc.example.mixin.flee_goals;

import net.fabricmc.example.entities.ZombieScarecrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpiderEntity.class)
public class ScarecrowSpiderFleeGoal extends MobEntity {
    public ScarecrowSpiderFleeGoal(EntityType<? extends MobEntity> entityType, World world){
        super(entityType, world);
    }
    @Inject(at = @At("HEAD"), method = "initGoals()V")
    private void init(CallbackInfo info)  {
        this.goalSelector.add(3, new FleeEntityGoal(((SpiderEntity)(Object)this),
                ZombieScarecrowEntity.class, 100.0F, 1.0, 1.2));

    }

}
