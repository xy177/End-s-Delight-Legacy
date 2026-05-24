package xy177.endsdelightlegacy.integration.jei;

import java.util.Collections;
import java.util.List;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import xy177.endsdelightlegacy.EndsDelightLegacy;
import xy177.endsdelightlegacy.common.registry.EDItems;

public class MobDropRecipeCategory implements IRecipeCategory<MobDropJeiRecipe>
{
    private static final ResourceLocation BG = new ResourceLocation(EndsDelightLegacy.MODID, "textures/gui/jei/mob_drops.png");

    private final IDrawable background;
    private final IDrawable icon;

    public MobDropRecipeCategory(IGuiHelper helper)
    {
        this.background = helper.createDrawable(BG, 0, 0, 134, 66, 0, 0, 0, 0);
        this.icon = helper.createDrawableIngredient(new ItemStack(EDItems.DRAGON_TOOTH_KNIFE));
    }

    @Override
    public String getUid()
    {
        return EDJeiRecipeTypes.MOB_DROPS;
    }

    @Override
    public String getTitle()
    {
        return I18n.format("ends_delight.jei.mob_drops.title");
    }

    @Override
    public String getModName()
    {
        return EndsDelightLegacy.NAME;
    }

    @Override
    public IDrawable getBackground()
    {
        return background;
    }

    @Override
    public IDrawable getIcon()
    {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, MobDropJeiRecipe recipeWrapper, IIngredients ingredients)
    {
        recipeLayout.getItemStacks().init(0, false, 107, 22);
        recipeLayout.getItemStacks().set(0, recipeWrapper.getOutput());

        recipeLayout.getItemStacks().addTooltipCallback((slotIndex, input, ingredient, tooltip) -> {
            if (slotIndex == 0 && recipeWrapper.getNoteKey() != null) {
                tooltip.add(I18n.format(recipeWrapper.getNoteKey()));
            }
        });
    }

    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY)
    {
        if (mouseX > 43 && mouseY > 17 && mouseX < 73 && mouseY < 45) {
            return Collections.singletonList(I18n.format("ends_delight.jei.mob_drops.tools"));
        }
        return Collections.emptyList();
    }
}
