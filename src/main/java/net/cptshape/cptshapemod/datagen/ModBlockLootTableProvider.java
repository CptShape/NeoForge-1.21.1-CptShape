package net.cptshape.cptshapemod.datagen;

import net.cptshape.cptshapemod.block.ModBlocks;
import net.cptshape.cptshapemod.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.TEST_BLOCK.get());
        dropSelf(ModBlocks.MAGIC_BLOCK.get());

        add(ModBlocks.TEST_BLOCK_ORE.get(),
                block -> createOreDrop(ModBlocks.TEST_BLOCK_ORE.get(), ModItems.RAW_TEST_ITEM.get()));
        add(ModBlocks.TEST_BLOCK_DEEPSLATE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.TEST_BLOCK_DEEPSLATE_ORE.get(), ModItems.RAW_TEST_ITEM.get(), 2, 5));

        dropSelf(ModBlocks.TESTITEM_STAIRS.get());
        add(ModBlocks.TESTITEM_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.TESTITEM_SLAB.get()));
        dropSelf(ModBlocks.TESTITEM_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.TESTITEM_BUTTON.get());
        dropSelf(ModBlocks.TESTITEM_FENCE.get());
        dropSelf(ModBlocks.TESTITEM_FENCE_GATE.get());
        dropSelf(ModBlocks.TESTITEM_WALL.get());
        dropSelf(ModBlocks.TESTITEM_TRAP_DOOR.get());
        dropSelf(ModBlocks.TESTITEM_WALL.get());
        add(ModBlocks.TESTITEM_DOOR.get(),
                block -> createDoorTable(ModBlocks.TESTITEM_DOOR.get()));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}