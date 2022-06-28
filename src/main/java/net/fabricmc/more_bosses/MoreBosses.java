package net.fabricmc.more_bosses;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.more_bosses.entities.bosses.GiantZombieBossEntity;
import net.fabricmc.more_bosses.entities.bosses.GiantPillagerBossEntity;
import net.fabricmc.more_bosses.entities.TowerOfUndyingEntity;
import net.fabricmc.more_bosses.entities.ZombieScarecrowEntity;
import net.fabricmc.more_bosses.items.SleepingPillagerSoul;
import net.fabricmc.more_bosses.items.TowerOfUndying;
import net.fabricmc.more_bosses.items.ZombieScarecrowItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.structure.WoodlandMansionGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.WoodlandMansionFeature;

public class MoreBosses implements ModInitializer {

	public static final EntityType<ZombieScarecrowEntity> ZOMBIE_SCARECROW_ENTITY =
			Registry.register(Registry.ENTITY_TYPE, new Identifier("more_bosses","zombie_scarecrow"),
					FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ZombieScarecrowEntity::new). //CHANGE SPAWNGROUP TO MISC
							dimensions(EntityDimensions.fixed(0.75f,1.9f)).build());

	public static final EntityType<TowerOfUndyingEntity> TOWER_OF_UNDYING_ENTITY =
			Registry.register(Registry.ENTITY_TYPE, new Identifier("more_bosses","tower_of_undying"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, TowerOfUndyingEntity::new)
					.dimensions(EntityDimensions.fixed(.25F, .25F))
					.build());

	public static final EntityType<GiantZombieBossEntity> GIANT_ZOMBIE_BOSS_ENTITY =
			Registry.register(Registry.ENTITY_TYPE, new Identifier("more_bosses","giant_zombie_boss_entity"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GiantZombieBossEntity::new)
					.dimensions(EntityDimensions.fixed(3F, 12F))
					.build());

	public static final EntityType<GiantPillagerBossEntity> GIANT_PILLAGER_BOSS_ENTITY =
			Registry.register(Registry.ENTITY_TYPE, new Identifier("more_bosses","giant_pillager_boss_entity"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GiantPillagerBossEntity::new)
					.dimensions(EntityDimensions.fixed(3F, 12F))
					.build());

	public static final Item ZOMBIE_SCARECROW_ITEM = new ZombieScarecrowItem(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item TOWER_OF_UNDYING_ITEM = new TowerOfUndying(new FabricItemSettings().group(ItemGroup.MISC));

	public static final Item SLEEPING_PILLAGER_SOUL = new SleepingPillagerSoul(new FabricItemSettings().group(ItemGroup.MISC));

	private static final Identifier MANSION_LOOT_TABLE = new Identifier(Identifier.DEFAULT_NAMESPACE, "chests/woodland_mansion");
	//minecraft\loot_tables\chests\woodland_mansion.json
	@Override
	public void onInitialize() {

		FabricDefaultAttributeRegistry.register(ZOMBIE_SCARECROW_ENTITY, ZombieScarecrowEntity.createLivingAttributes());
		FabricDefaultAttributeRegistry.register(TOWER_OF_UNDYING_ENTITY, TowerOfUndyingEntity.createLivingAttributes());
		FabricDefaultAttributeRegistry.register(GIANT_ZOMBIE_BOSS_ENTITY, GiantZombieBossEntity.createGiantZombieBossEntityAttributes());
		FabricDefaultAttributeRegistry.register(GIANT_PILLAGER_BOSS_ENTITY, GiantPillagerBossEntity.createGiantPillagerBossEntityAttributes());

		Registry.register(Registry.ITEM, new Identifier("more_bosses", "zombie_scarecrow_item"), ZOMBIE_SCARECROW_ITEM);
		Registry.register(Registry.ITEM, new Identifier("more_bosses", "tower_of_undying_item"), TOWER_OF_UNDYING_ITEM);
		Registry.register(Registry.ITEM, new Identifier("more_bosses", "sleeping_pillager_soul"), SLEEPING_PILLAGER_SOUL);


		LootTableLoadingCallback.EVENT.register((resourceManager, manager, id, supplier, setter) -> {
			if(MANSION_LOOT_TABLE.equals(id)){
				LootPool.Builder poolBuilder = LootPool.builder()
								.with(ItemEntry.builder(SLEEPING_PILLAGER_SOUL));

				supplier.pool(poolBuilder);
			}
		});
	}
}
