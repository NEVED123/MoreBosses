package net.fabricmc.example.mixin;

import net.fabricmc.example.entities.ZombieScarecrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


/*@Mixin(MobEntity.class)
interface GoalSelectorAccessor{
	@Accessor
	GoalSelector getGoalSelector();
}*/

@Mixin(ZombieEntity.class)
public class ScarecrowZombieFleeGoal extends MobEntity{
	public ScarecrowZombieFleeGoal(EntityType<? extends MobEntity> entityType, World world){
		super(entityType, world);
	}
	@Inject(at = @At("HEAD"), method = "initCustomGoals()V")
	private void init(CallbackInfo info)  {
		this.goalSelector.add(3, new FleeEntityGoal(((ZombieEntity)(Object)this),
				ZombieScarecrowEntity.class, 100.0F, 1.0, 1.2));

	}

}



