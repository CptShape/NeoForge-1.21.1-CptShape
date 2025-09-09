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
                    .icon(() -> new ItemStack(ModItems.TEST_ITEM.get()))
                    .title(Component.translatable("creativetab.cptshapemod.cptshape_items_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.TEST_ITEM);
                        output.accept(ModItems.RAW_TEST_ITEM);

                        output.accept(ModItems.CHISEL);

                    }).build());

    public static final Supplier<CreativeModeTab> CPTSHAPE_BLOCKS_TAB = CREATIVE_MODE_TAB.register("cptshape_blocks_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.TEST_BLOCK))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(CptShapeMod.MOD_ID, "cptshape_items_tab"))
                    .title(Component.translatable("creativetab.cptshapemod.cptshape_blocks_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.TEST_BLOCK);
                        output.accept(ModBlocks.TEST_BLOCK_ORE);
                        output.accept(ModBlocks.TEST_BLOCK_DEEPSLATE_ORE);

                        output.accept(ModBlocks.MAGIC_BLOCK);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);

    }

}
