package net.fabricmc.example.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

public class GiantPillagerBossEntity extends HostileEntity {

    public GiantPillagerBossEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }


}
