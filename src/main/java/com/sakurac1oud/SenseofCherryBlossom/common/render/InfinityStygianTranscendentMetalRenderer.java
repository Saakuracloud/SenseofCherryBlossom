package com.sakurac1oud.SenseofCherryBlossom.common.render;

import static gregtech.api.enums.Mods.Avaritia;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

import codechicken.lib.render.TextureUtils;
import fox.spiteful.avaritia.render.CosmicRenderShenanigans;
import gregtech.api.interfaces.IGT_ItemWithMaterialRenderer;
import gregtech.common.render.items.GeneratedMaterialRenderer;
import gregtech.api.enums.Textures;

/**
 * InfinityStygianTranscendentMetalRenderer
 *
 * 结合了基础的Stygian渲染和Infinity的光晕效果。
 * 移除了其他可能造成冲突的渲染效果，保持渲染的稳定性。
 */
public class InfinityStygianTranscendentMetalRenderer extends GeneratedMaterialRenderer {

    private long lastTime = System.currentTimeMillis();

    @Override
    public void renderItem(ItemRenderType type, ItemStack aStack, Object... data) {
        if (type == ItemRenderType.ENTITY) {
            if (!Minecraft.getMinecraft().gameSettings.fancyGraphics) {
                if (RenderItem.renderInFrame) {
                    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                }
                GL11.glTranslatef(-0.5F, -0.25F, 0.0421875F);
            }
        }

        if (!(aStack.getItem() instanceof IGT_ItemWithMaterialRenderer aItem)) return;
        short aMetaData = (short) aStack.getItemDamage();

        // 保存OpenGL状态
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        GL11.glPushMatrix();

        // 设置基础渲染状态
        setupRenderState(type);

        // 如果是物品栏渲染，先渲染光晕
        if (type == ItemRenderType.INVENTORY) {
            renderHalo();
        }

        // 渲染主要内容
        int passes = aItem.requiresMultipleRenderPasses() ? aItem.getRenderPasses(aMetaData) : 1;
        for (int pass = 0; pass < passes; pass++) {
            IIcon tIcon = aItem.getIcon(aMetaData, pass);
            IIcon tOverlay = aItem.getOverlayIcon(aMetaData, pass);

            // 渲染主要图标
            if (tIcon != null) {
                renderMainIcon(type, aStack, tIcon);
            }

            // 渲染叠加层
            if (tOverlay != null) {
                renderOverlay(type, tOverlay);
            }
        }

        // 恢复OpenGL状态
        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }

    private void setupRenderState(ItemRenderType type) {
        if (type == ItemRenderType.INVENTORY) {
            RenderHelper.enableGUIStandardItemLighting();
        }

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
    }

    private void renderHalo() {
        IIcon halo = Textures.ItemIcons.HALO.getIcon();
        if (halo == null) return;

        GL11.glPushMatrix();

        // 保存当前的颜色和混合模式
        GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_LIGHTING_BIT);

        // 设置合适的渲染状态
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        // 计算光晕动画
        float time = (System.currentTimeMillis() % 3000) / 3000.0f;
        float alpha = 0.3f + 0.1f * (float)Math.sin(time * Math.PI * 2);

        // 设置光晕颜色和透明度
        GL11.glColor4f(0.1f, 0.1f, 0.9f, alpha);

        // 渲染光晕
        int spread = 10;
        Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        t.addVertexWithUV(-spread, -spread, 0, halo.getMinU(), halo.getMinV());
        t.addVertexWithUV(-spread, 16 + spread, 0, halo.getMinU(), halo.getMaxV());
        t.addVertexWithUV(16 + spread, 16 + spread, 0, halo.getMaxU(), halo.getMaxV());
        t.addVertexWithUV(16 + spread, -spread, 0, halo.getMaxU(), halo.getMinV());
        t.draw();

        // 恢复之前的渲染状态
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    private void renderMainIcon(ItemRenderType type, ItemStack stack, IIcon icon) {
        GL11.glPushMatrix();

        // 应用基础变换
        if (type == ItemRenderType.INVENTORY) {
            GL11.glTranslatef(8f, 8f, 0f);
        } else {
            GL11.glTranslatef(0.5f, 0.5f, 0f);
        }

        // 应用简单旋转动画
        float angle = (System.currentTimeMillis() - lastTime) * 0.05f;
        GL11.glRotatef(angle % 360, 0.3f, 0.5f, 0.2f);

        // 恢复位置
        if (type == ItemRenderType.INVENTORY) {
            GL11.glTranslatef(-8f, -8f, 0f);
        } else {
            GL11.glTranslatef(-0.5f, -0.5f, 0f);
        }

        // 渲染主图标
        if (Avaritia.isModLoaded()) {
            renderWithShader(type, stack, icon);
        } else {
            renderNormal(type, stack, icon);
        }

        GL11.glPopMatrix();
    }

    private void renderWithShader(ItemRenderType type, ItemStack stack, IIcon icon) {
        if (!(stack.getItem() instanceof IGT_ItemWithMaterialRenderer itemRenderer)) return;

        // 使用基本的Cosmic着色器参数
        CosmicRenderShenanigans.cosmicOpacity = 0.5f;
        CosmicRenderShenanigans.inventoryRender = type == ItemRenderType.INVENTORY;

        // 设置着色器
        CosmicRenderShenanigans.useShader();

        // 渲染物品
        renderItemWithEffect(type, icon, itemRenderer.getRGBa(stack));

        // 清理着色器
        CosmicRenderShenanigans.releaseShader();
    }

    private void renderNormal(ItemRenderType type, ItemStack stack, IIcon icon) {
        if (!(stack.getItem() instanceof IGT_ItemWithMaterialRenderer itemRenderer)) return;

        short[] colors = itemRenderer.getRGBa(stack);
        GL11.glColor4f(
            colors[0] / 255.0F,
            colors[1] / 255.0F,
            colors[2] / 255.0F,
            1.0F
        );

        renderItemWithEffect(type, icon, colors);
    }

    private void renderItemWithEffect(ItemRenderType type, IIcon icon, short[] colors) {
        if (type == ItemRenderType.INVENTORY) {
            GL11.glScalef(16F, 16F, 16F);
        }

        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, icon.getMinU(), icon.getMinV());
        tessellator.addVertexWithUV(1.0D, 0.0D, 0.0D, icon.getMaxU(), icon.getMinV());
        tessellator.addVertexWithUV(1.0D, 1.0D, 0.0D, icon.getMaxU(), icon.getMaxV());
        tessellator.addVertexWithUV(0.0D, 1.0D, 0.0D, icon.getMinU(), icon.getMaxV());
        tessellator.draw();
    }

    private void renderOverlay(ItemRenderType type, IIcon overlay) {
        GL11.glPushMatrix();

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.7f);
        TextureUtils.bindAtlas(0);

        if (type == ItemRenderType.INVENTORY) {
            GL11.glScalef(16F, 16F, 16F);
        }

        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, overlay.getMinU(), overlay.getMinV());
        tessellator.addVertexWithUV(1.0D, 0.0D, 0.0D, overlay.getMaxU(), overlay.getMinV());
        tessellator.addVertexWithUV(1.0D, 1.0D, 0.0D, overlay.getMaxU(), overlay.getMaxV());
        tessellator.addVertexWithUV(0.0D, 1.0D, 0.0D, overlay.getMinU(), overlay.getMaxV());
        tessellator.draw();

        GL11.glPopMatrix();
    }

    @Override
    protected void renderContainedFluid(ItemRenderType type, FluidStack fluidStack, IIcon fluidIcon) {
        GL11.glPushMatrix();

        Fluid fluid = fluidStack.getFluid();
        int color = fluid.getColor();
        GL11.glColor4f(
            ((color >> 16) & 0xFF) / 255.0F,
            ((color >> 8) & 0xFF) / 255.0F,
            (color & 0xFF) / 255.0F,
            ((color >> 24) & 0xFF) / 255.0F
        );

        TextureUtils.bindAtlas(fluid.getSpriteNumber());
        GL11.glDepthFunc(GL11.GL_EQUAL);

        if (type == ItemRenderType.INVENTORY) {
            GL11.glScalef(16F, 16F, 16F);
        }

        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, fluidIcon.getMinU(), fluidIcon.getMinV());
        tessellator.addVertexWithUV(1.0D, 0.0D, 0.0D, fluidIcon.getMaxU(), fluidIcon.getMinV());
        tessellator.addVertexWithUV(1.0D, 1.0D, 0.0D, fluidIcon.getMaxU(), fluidIcon.getMaxV());
        tessellator.addVertexWithUV(0.0D, 1.0D, 0.0D, fluidIcon.getMinU(), fluidIcon.getMaxV());
        tessellator.draw();

        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glPopMatrix();
    }
}
