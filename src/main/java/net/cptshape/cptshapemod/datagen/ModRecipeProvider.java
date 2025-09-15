package net.cptshape.cptshapemod.datagen;

import net.cptshape.cptshapemod.CptShapeMod;
import net.cptshape.cptshapemod.block.ModBlocks;
import net.cptshape.cptshapemod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> BISMUTH_SMELTABLES = List.of(ModItems.RAW_TEST_ITEM,
                ModBlocks.TEST_BLOCK_ORE, ModBlocks.TEST_BLOCK_DEEPSLATE_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TEST_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.TEST_ITEM.get())
                .unlockedBy("has_testitem", has(ModItems.TEST_ITEM)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TEST_ITEM.get(), 9)
                .requires(ModBlocks.TEST_BLOCK)
                .unlockedBy("has_testblock", has(ModBlocks.TEST_BLOCK)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TEST_ITEM.get(), 18)
                .requires(ModBlocks.MAGIC_BLOCK)
                .unlockedBy("has_magic_block", has(ModBlocks.MAGIC_BLOCK))
                .save(recipeOutput, "cptshapemod:testitem_from_magic_block");

        oreSmelting(recipeOutput, BISMUTH_SMELTABLES, RecipeCategory.MISC, ModItems.TEST_ITEM.get(), 0.25f, 200, "testitem");
        oreBlasting(recipeOutput, BISMUTH_SMELTABLES, RecipeCategory.MISC, ModItems.TEST_ITEM.get(), 0.25f, 100, "testitem");

        stairBuilder(ModBlocks.TESTITEM_STAIRS.get(), Ingredient.of(ModItems.TEST_ITEM)).group("testitem")
                .unlockedBy("has_testitem", has(ModItems.TEST_ITEM)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TESTITEM_SLAB.get(), ModItems.TEST_ITEM.get());

        buttonBuilder(ModBlocks.TESTITEM_BUTTON.get(), Ingredient.of(ModItems.TEST_ITEM.get())).group("testitem")
                .unlockedBy("has_testitem", has(ModItems.TEST_ITEM.get())).save(recipeOutput);
        pressurePlate(recipeOutput, ModBlocks.TESTITEM_PRESSURE_PLATE.get(), ModItems.TEST_ITEM.get());

        fenceBuilder(ModBlocks.TESTITEM_FENCE.get(), Ingredient.of(ModItems.TEST_ITEM.get())).group("testitem")
                .unlockedBy("has_testitem", has(ModItems.TEST_ITEM.get())).save(recipeOutput);
        fenceGateBuilder(ModBlocks.TESTITEM_FENCE_GATE.get(), Ingredient.of(ModItems.TEST_ITEM.get())).group("testitem")
                .unlockedBy("has_testitem", has(ModItems.TEST_ITEM.get())).save(recipeOutput);
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TESTITEM_WALL.get(), ModItems.TEST_ITEM.get());

        doorBuilder(ModBlocks.TESTITEM_DOOR.get(), Ingredient.of(ModItems.TEST_ITEM.get())).group("testitem")
                .unlockedBy("has_testitem", has(ModItems.TEST_ITEM.get())).save(recipeOutput);
        trapdoorBuilder(ModBlocks.TESTITEM_TRAP_DOOR.get(), Ingredient.of(ModItems.TEST_ITEM.get())).group("testitem")
                .unlockedBy("has_testitem", has(ModItems.TEST_ITEM.get())).save(recipeOutput);



    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, CptShapeMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}