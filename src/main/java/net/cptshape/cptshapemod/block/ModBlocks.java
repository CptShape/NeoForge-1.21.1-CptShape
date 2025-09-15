package net.cptshape.cptshapemod.block;

import net.cptshape.cptshapemod.CptShapeMod;
import net.cptshape.cptshapemod.block.custom.MagicBlock;
import net.cptshape.cptshapemod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CptShapeMod.MOD_ID);

    public static final DeferredBlock<Block> TEST_BLOCK =
            registerBlock("testblock", () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> TEST_BLOCK_ORE =
            registerBlock("testblock_ore", () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .strength(3f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> TEST_BLOCK_DEEPSLATE_ORE =
            registerBlock("testblock_deepslate_ore", () -> new DropExperienceBlock(UniformInt.of(3, 6),
                    BlockBehaviour.Properties.of()
                            .strength(4f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> MAGIC_BLOCK =
            registerBlock("magic_block", () -> new MagicBlock(
                    BlockBehaviour.Properties.of()
                            .strength(2f)
                            .requiresCorrectToolForDrops()
            ));

    public static final DeferredBlock<StairBlock> TESTITEM_STAIRS = registerBlock("testitem_stairs",
            () -> new StairBlock(ModBlocks.TEST_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<SlabBlock> TESTITEM_SLAB = registerBlock("testitem_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<PressurePlateBlock> TESTITEM_PRESSURE_PLATE = registerBlock("testitem_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<ButtonBlock> TESTITEM_BUTTON = registerBlock("testitem_button",
            () -> new ButtonBlock(BlockSetType.IRON, 20,
                    BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().noCollission()));

    public static final DeferredBlock<FenceBlock> TESTITEM_FENCE = registerBlock("testitem_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<FenceGateBlock> TESTITEM_FENCE_GATE = registerBlock("testitem_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<WallBlock> TESTITEM_WALL = registerBlock("testitem_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<DoorBlock> TESTITEM_DOOR = registerBlock("testitem_door",
            () -> new DoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().noOcclusion()));
    public static final DeferredBlock<TrapDoorBlock> TESTITEM_TRAP_DOOR = registerBlock("testitem_trap_door",
            () -> new TrapDoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().noOcclusion()));











    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }


}
