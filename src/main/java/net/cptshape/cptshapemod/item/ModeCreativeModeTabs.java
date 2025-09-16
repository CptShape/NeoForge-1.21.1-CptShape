package net.cptshape.cptshapemod.item;

import net.cptshape.cptshapemod.CptShapeMod;
import net.cptshape.cptshapemod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModeCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CptShapeMod.MOD_ID);

    public static final Supplier<CreativeModeTab> CPTSHAPE_ITEMS_TAB = CREATIVE_MODE_TAB.register("cptshape_items_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.BISMUTH.get()))
                    .title(Component.translatable("creativetab.cptshapemod.cptshape_items_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.BISMUTH);
                        output.accept(ModItems.RAW_BISMUTH);
                        output.accept(ModItems.STARLIGHT_ASHES);
                        output.accept(ModItems.FROSTFIRE_ICE);

                        output.accept(ModItems.CHISEL);

                    }).build());

    public static final Supplier<CreativeModeTab> CPTSHAPE_BLOCKS_TAB = CREATIVE_MODE_TAB.register("cptshape_blocks_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.BISMUTH_BLOCK))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(CptShapeMod.MOD_ID, "cptshape_items_tab"))
                    .title(Component.translatable("creativetab.cptshapemod.cptshape_blocks_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.BISMUTH_BLOCK);
                        output.accept(ModBlocks.BISMUTH_ORE);
                        output.accept(ModBlocks.BISMUTH_DEEPSLATE_ORE);

                        output.accept(ModBlocks.BISMUTH_STAIRS);
                        output.accept(ModBlocks.BISMUTH_SLAB);
                        output.accept(ModBlocks.BISMUTH_PRESSURE_PLATE);
                        output.accept(ModBlocks.BISMUTH_BUTTON);
                        output.accept(ModBlocks.BISMUTH_FENCE);
                        output.accept(ModBlocks.BISMUTH_FENCE_GATE);
                        output.accept(ModBlocks.BISMUTH_WALL);
                        output.accept(ModBlocks.BISMUTH_DOOR);
                        output.accept(ModBlocks.BISMUTH_TRAP_DOOR);

                        output.accept(ModBlocks.MAGIC_BLOCK);
                    }).build());

    public static final Supplier<CreativeModeTab> CPTSHAPE_FOODS_TAB = CREATIVE_MODE_TAB.register("cptshape_foods_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.RADISH.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(CptShapeMod.MOD_ID, "cptshape_blocks_tab"))
                    .title(Component.translatable("creativetab.cptshapemod.cptshape_foods_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RADISH);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);

    }

}
