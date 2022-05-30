package net.fabricmc.example.mixin;

import net.minecraft.entity.mob.AbstractSkeletonEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractSkeletonEntity.class)
public class ScarecrowSkeletonFleeGoal {
}
