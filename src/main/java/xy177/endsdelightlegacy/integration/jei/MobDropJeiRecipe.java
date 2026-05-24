package xy177.endsdelightlegacy.integration.jei;

import com.google.common.collect.ImmutableList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

public class MobDropJeiRecipe implements IRecipeWrapper
{
    public enum Condition
    {
        ALWAYS,
        KNIFE,
        BURNING_KNIFE
    }

    private final ResourceLocation entityId;
    private final List<ItemStack> toolOptions;
    private final ItemStack output;
    private final Condition condition;
    private final String noteKey;

    private World currentWorld;
    private EntityLivingBase entityInstance;
    private boolean entityErrored;
    private double renderScale = -1.0D;

    public MobDropJeiRecipe(ResourceLocation entityId, List<ItemStack> toolOptions, ItemStack output, Condition condition, String noteKey)
    {
        this.entityId = entityId;
        this.toolOptions = ImmutableList.copyOf(toolOptions);
        this.output = output;
        this.condition = condition;
        this.noteKey = noteKey;
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setOutput(ItemStack.class, output);
    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY)
    {
        if (minecraft.world != null && minecraft.world != currentWorld) {
            currentWorld = minecraft.world;
            entityInstance = null;
            entityErrored = false;
            renderScale = -1.0D;
        }

        if (entityInstance == null && !entityErrored && minecraft.world != null) {
            try {
                Entity entity = EntityList.createEntityByIDFromName(entityId, minecraft.world);
                if (entity instanceof EntityLivingBase) {
                    entityInstance = (EntityLivingBase) entity;
                } else {
                    entityErrored = true;
                }
            } catch (Exception e) {
                entityErrored = true;
            }
        }

        if (entityInstance != null) {
            if (condition == Condition.BURNING_KNIFE) {
                entityInstance.setFire(1);
            } else {
                entityInstance.extinguish();
            }

            if (renderScale < 0.0D) {
                double width = entityInstance.width;
                double height = entityInstance.height;
                renderScale = width > height ? 13.0D / width : 32.0D / height;
            }

            GlStateManager.enableDepth();
            GlStateManager.pushMatrix();
            GlStateManager.translate(35, 45, 0);
            GuiInventory.drawEntityOnScreen(0, 0, (int) Math.round(renderScale), -100, 0, entityInstance);
            GlStateManager.popMatrix();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        }

        drawTool(minecraft);

        if (condition == Condition.BURNING_KNIFE) {
            minecraft.fontRenderer.drawString(I18n.format("ends_delight.jei.mob_drops.burning"), 40, 55, 0xAA3333);
        } else if (condition == Condition.KNIFE) {
            minecraft.fontRenderer.drawString(I18n.format("ends_delight.jei.mob_drops.knife_required"), 40, 55, 0x505050);
        }
    }

    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY)
    {
        if (mouseX > 9 && mouseY > 8 && mouseX < 42 && mouseY < 57) {
            if (entityInstance != null) {
                ModContainer mod = Loader.instance().getIndexedModList().get(entityId.getResourceDomain());
                return ImmutableList.of(
                    entityInstance.getName(),
                    TextFormatting.BLUE + "" + TextFormatting.ITALIC + (mod == null ? "Unknown" : mod.getName())
                );
            }
            return Collections.singletonList(entityId.toString());
        }
        return Collections.emptyList();
    }

    public ItemStack getOutput()
    {
        return output;
    }

    public String getNoteKey()
    {
        return noteKey;
    }

    private void drawTool(Minecraft minecraft)
    {
        ItemStack tool = getAnimatedTool(minecraft);
        if (tool.isEmpty()) {
            return;
        }

        long time = minecraft.world == null ? Minecraft.getSystemTime() : minecraft.world.getTotalWorldTime();
        float theta = (float) (Math.PI / 2 * ((time + minecraft.getRenderPartialTicks()) / 10F + 5));
        double movementX = Math.sin(theta);
        double movementY = Math.cos(theta);

        GlStateManager.pushMatrix();
        GlStateManager.translate(50 + movementX * 16, 35 + movementY * 8, 0);
        GlStateManager.rotate((float) (120 * movementX), 0, 0, 1);
        GlStateManager.disableDepth();
        RenderHelper.enableGUIStandardItemLighting();
        minecraft.getRenderItem().renderItemAndEffectIntoGUI(tool, 0, -16);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
    }

    private ItemStack getAnimatedTool(Minecraft minecraft)
    {
        if (toolOptions.isEmpty()) {
            return ItemStack.EMPTY;
        }
        long time = minecraft.world == null ? Minecraft.getSystemTime() : minecraft.world.getTotalWorldTime();
        return toolOptions.get((int) ((time / 20L) % toolOptions.size()));
    }
}
