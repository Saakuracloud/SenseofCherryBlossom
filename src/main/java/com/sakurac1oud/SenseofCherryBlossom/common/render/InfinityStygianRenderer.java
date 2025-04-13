package com.sakurac1oud.SenseofCherryBlossom.common.render;

import static gregtech.api.enums.Mods.Avaritia;

import java.util.Random;

import gregtech.GTMod;
import gregtech.common.render.items.GeneratedMaterialRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.util.vector.Vector3f;

import codechicken.lib.render.TextureUtils;
import fox.spiteful.avaritia.render.CosmicRenderShenanigans;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.IGT_ItemWithMaterialRenderer;

public class InfinityStygianRenderer extends GeneratedMaterialRenderer {

    private static final float COSMIC_OPACITY = 0.7f;
    private static final Vector3f GLOW_COLOR = new Vector3f(0.6f, 0.4f, 0.7f);
    private static final Random random = new Random();

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

        short aMetaData = (short) aStack.getItemDamage();
        if (!(aStack.getItem() instanceof IGT_ItemWithMaterialRenderer aItem)) return;

        // 保存OpenGL状态
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        GL11.glPushMatrix();

        // 设置基础渲染状态
        setupRenderState(type);

        // 渲染主要内容
        int passes = aItem.requiresMultipleRenderPasses() ? aItem.getRenderPasses(aMetaData) : 1;
        for (int pass = 0; pass < passes; pass++) {
            IIcon tIcon = aItem.getIcon(aMetaData, pass);
            IIcon tOverlay = aItem.getOverlayIcon(aMetaData, pass);

            // 渲染物品栏特效
            if (type == ItemRenderType.INVENTORY && pass == 0) {
                renderHalo();
                renderInfinityEffect(tIcon);
            }

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

    private void renderMainIcon(ItemRenderType type, ItemStack stack, IIcon icon) {
        GL11.glPushMatrix();

        // 应用基础变换
        if (type == ItemRenderType.INVENTORY) {
            GL11.glTranslatef(8f, 8f, 0f);
        } else {
            GL11.glTranslatef(0.5f, 0.5f, 0f);
        }

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

        // 设置着色器参数
        CosmicRenderShenanigans.cosmicOpacity = COSMIC_OPACITY;
        CosmicRenderShenanigans.inventoryRender = type == ItemRenderType.INVENTORY;

        // 获取物品的基础颜色
        short[] colors = itemRenderer.getRGBa(stack);
        float r = colors[0] / 255.0F;
        float g = colors[1] / 255.0F;
        float b = colors[2] / 255.0F;

        // 应用颜色
        GL11.glColor4f(r, g, b, 1.0F);

        // 设置着色器
        CosmicRenderShenanigans.useShader();

        // 渲染物品
        renderItemWithEffect(type, icon, colors);

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
        GL11.glColor4f(0.6f, 0.4f, 0.7f, alpha);

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

    private void renderInfinityEffect(IIcon icon) {
        if (icon == null) return;

        GL11.glPushMatrix();

        // 设置渲染状态
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);

        // 渲染多层效果
        for (int i = 0; i < 3; i++) {
            float scale = 1.0f + i * 0.1f;
            float alpha = 0.3f - i * 0.1f;
            float time = (System.currentTimeMillis() % 2000) / 2000.0f;

            // 计算颜色
            float r = GLOW_COLOR.x + (float)Math.sin(time * Math.PI * 2) * 0.1f;
            float g = GLOW_COLOR.y + (float)Math.cos(time * Math.PI * 2) * 0.1f;
            float b = GLOW_COLOR.z + (float)Math.sin(time * Math.PI * 3) * 0.1f;

            GL11.glPushMatrix();
            GL11.glTranslatef(8, 8, 0);
            GL11.glScalef(scale, scale, 1.0f);
            GL11.glTranslatef(-8, -8, 0);

            // 渲染发光层
            GL11.glColor4f(r, g, b, alpha);
            Tessellator t = Tessellator.instance;
            t.startDrawingQuads();
            t.addVertexWithUV(0, 0, 0, icon.getMinU(), icon.getMinV());
            t.addVertexWithUV(0, 16, 0, icon.getMinU(), icon.getMaxV());
            t.addVertexWithUV(16, 16, 0, icon.getMaxU(), icon.getMaxV());
            t.addVertexWithUV(16, 0, 0, icon.getMaxU(), icon.getMinV());
            t.draw();

            GL11.glPopMatrix();
        }

        // 恢复渲染状态
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
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
}
