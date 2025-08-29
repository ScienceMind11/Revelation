package com.sember.revelation.registry;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

public class RevelationLootTables {

    public static void register() {

        LootTableEvents.MODIFY.register(RevelationLootTables::addSmithingTable);

    }

    public static void addSmithingTable(RegistryKey<LootTable> key, LootTable.Builder tableBuilder, LootTableSource source, RegistryWrapper.WrapperLookup registries) {

        if (EntityType.WITHER.getLootTableKey().orElse(null) != key || !source.isBuiltin()) return;

        LootPool.Builder smithingTable = LootPool.builder()
                .with(ItemEntry.builder(Items.SMITHING_TABLE));

        LootPool.Builder upgradeTemplate = LootPool.builder()
                .rolls(UniformLootNumberProvider.create(6.0F, 8.0F))
                .with(ItemEntry.builder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE));

        tableBuilder.pool(smithingTable);
        tableBuilder.pool(upgradeTemplate);

    }

}
