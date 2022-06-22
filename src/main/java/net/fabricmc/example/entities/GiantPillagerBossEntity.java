package net.fabricmc.example.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.tag.TagKey;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class GiantPillagerBossEntity extends GiantBossEntity {

    public GiantPillagerBossEntity(EntityType<? extends GiantBossEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createGiantPillagerEntityAttributes() {
        return createGiantBossEntityAttributes()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 30.0)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 200);
    }


}
