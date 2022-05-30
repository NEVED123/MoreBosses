package net.fabricmc.example.mixin;

import net.fabricmc.example.entities.ZombieScarecrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public class ScarecrowCreeperFleeGoal extends MobEntity {
    public ScarecrowCreeperFleeGoal(EntityType<? extends MobEntity> entityType, World world){
        super(entityType, world);
    }
    @Inject(at = @At("HEAD"), method = "initGoals()V")
    private void init(CallbackInfo info)  {
        this.goalSelector.add(3, new FleeEntityGoal(((CreeperEntity)(Object)this),
                ZombieScarecrowEntity.class, 100.0F, 1.0, 1.2));

    }

}