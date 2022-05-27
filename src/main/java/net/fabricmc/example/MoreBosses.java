package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.entities.ZombieScarecrowEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreBosses implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("MoreMobs");

	public static final EntityType<ZombieScarecrowEntity> ZOMBIE_SCARECROW_ENTITY =
			Registry.register(Registry.ENTITY_TYPE, new Identifier("more_bosses","zombie_scarecrow"),
					FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ZombieScarecrowEntity::new). //CHANGE SPAWNGROUP TO MISC
							dimensions(EntityDimensions.fixed(0.75f,0.75f)).build());

	public static final Item ZOMBIE_SCARECROW_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item TOWER_OF_UNDYING_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		FabricDefaultAttributeRegistry.register(ZOMBIE_SCARECROW_ENTITY, ZombieScarecrowEntity.createLivingAttributes());
		Registry.register(Registry.ITEM, new Identifier("more_bosses", "zombie_scarecrow_item"), ZOMBIE_SCARECROW_ITEM);
		Registry.register(Registry.ITEM, new Identifier("more_bosses", "tower_of_undying_item"), TOWER_OF_UNDYING_ITEM);
	}
}
