package net.cptshape.cptshapemod.datagen;

import net.cptshape.cptshapemod.CptShapeMod;
import net.cptshape.cptshapemod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CptShapeMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.TEST_BLOCK);

        blockWithItem(ModBlocks.TEST_BLOCK_ORE);
        blockWithItem(ModBlocks.TEST_BLOCK_DEEPSLATE_ORE);

        blockWithItem(ModBlocks.MAGIC_BLOCK);

        stairsBlock(ModBlocks.TESTITEM_STAIRS.get(), blockTexture(ModBlocks.TEST_BLOCK.get()));
        slabBlock(ModBlocks.TESTITEM_SLAB.get(), blockTexture(ModBlocks.TEST_BLOCK.get()), blockTexture(ModBlocks.TEST_BLOCK.get()));
        buttonBlock(ModBlocks.TESTITEM_BUTTON.get(), blockTexture(ModBlocks.TEST_BLOCK.get()));
        pressurePlateBlock(ModBlocks.TESTITEM_PRESSURE_PLATE.get(), blockTexture(ModBlocks.TEST_BLOCK.get()));
        fenceBlock(ModBlocks.TESTITEM_FENCE.get(), blockTexture(ModBlocks.TEST_BLOCK.get()));
        fenceGateBlock(ModBlocks.TESTITEM_FENCE_GATE.get(), blockTexture(ModBlocks.TEST_BLOCK.get()));
        wallBlock(ModBlocks.TESTITEM_WALL.get(), blockTexture(ModBlocks.TEST_BLOCK.get()));
        doorBlockWithRenderType(ModBlocks.TESTITEM_DOOR.get(), modLoc("block/testitem_door_bottom"), modLoc("block/testitem_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.TESTITEM_TRAP_DOOR.get(), modLoc("block/testitem_trapdoor"), true,"cutout");

        blockItem(ModBlocks.TESTITEM_STAIRS);
        blockItem(ModBlocks.TESTITEM_SLAB);
        blockItem(ModBlocks.TESTITEM_PRESSURE_PLATE);
        blockItem(ModBlocks.TESTITEM_FENCE_GATE);
        blockItem(ModBlocks.TESTITEM_TRAP_DOOR, "_bottom");
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("cptshapemod:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("cptshapemod:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
