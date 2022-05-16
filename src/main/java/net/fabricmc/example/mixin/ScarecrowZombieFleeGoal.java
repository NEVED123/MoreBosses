package net.fabricmc.example.mixin;

import net.fabricmc.example.MoreBosses;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.CatEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class ScarecrowZombieFleeGoal {
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		System.out.println("This line is printed by an example mod mixin!");
	}
}

/*@Mixin(ZombieEntity.class)
public class ScarecrowZombieFleeGoal {
	@Inject(at = @At("HEAD"), method = "initCustomGoals()V")
	private void init(CallbackInfo info) {
		this.goalSelector.add(3, new FleeEntityGoal(this, SkeletonEntity.class, 6.0F, 1.0, 1.2));
	}
}*/
