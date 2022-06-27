package net.fabricmc.more_bosses.items;

import net.fabricmc.more_bosses.MoreBosses;
import net.fabricmc.more_bosses.entities.bosses.GiantPillagerBossEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SleepingPillagerSoul extends Item {

    public SleepingPillagerSoul(Settings settings) {
        super(settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos placedPos = context.getBlockPos();
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        if(!world.isClient){
            ServerWorld serverWorld = (ServerWorld)world;
            GiantPillagerBossEntity giantPillagerBossEntity = MoreBosses.GIANT_PILLAGER_BOSS_ENTITY
                    .create(serverWorld, null, null, null,
                            placedPos, SpawnReason.SPAWN_EGG, true, false);
            serverWorld.spawnEntity(giantPillagerBossEntity);
            if(!player.isCreative()){
                ItemStack stack = context.getStack();
                stack.decrement(1);
            }
            return ActionResult.SUCCESS;
        }

        return ActionResult.FAIL;
    }
}
