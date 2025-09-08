package net.cptshape.cptshapemod.item;

import net.cptshape.cptshapemod.CptShapeMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CptShapeMod.MOD_ID);

    public static final DeferredItem<Item> TEST_ITEM = ITEMS.register("testitem",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_TEST_ITEM = ITEMS.register("raw_testitem",
            () -> new Item(new Item.Properties()));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }


}
