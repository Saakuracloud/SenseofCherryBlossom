package com.sakurac1oud.SenseofCherryBlossom.common.render;

import static gregtech.api.enums.Mods.Avaritia;

import java.nio.FloatBuffer;
import java.util.Random;

import gregtech.common.render.items.GeneratedMaterialRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import codechicken.lib.render.TextureUtils;
import fox.spiteful.avaritia.render.CosmicRenderShenanigans;
import gregtech.api.enums.ItemList;
import gregtech.api.interfaces.IGT_ItemWithMaterialRenderer;
import gregtech.common.render.GTRenderUtil;

/**
 * StygianRenderer - 强化版冥河渲染器
 *
 * 冥河（Styx）是希腊神话中连接人间与冥界的河流，被认为具有神秘且强大的魔力。
 * 此渲染器通过高级着色器技术模拟冥河的黑暗、流动和神秘特性，为物品赋予冥界气息。
 *
 * 特性：
 * 1. 深邃的黑色基调，带有微弱的紫蓝色光晕
 * 2. 动态流动的暗影纹理
 * 3. 基于视角的全息效果
 * 4. 环境光照自适应
 * 5. 粒子系统增强的视觉效果
 */
public class StygianRenderer extends GeneratedMaterialRenderer {

    // 基础渲染参数 - 无阴影效果
    private static final float BASE_OPACITY = 2.0f;        // 提高基础不透明度
    private static final float HOLOGRAM_INTENSITY = 6.5f;  // 增强全息效果强度
    // 移除阴影强度参数

    // 颜色配置 - 无阴影效果
    private static final Vector3f BASE_COLOR = new Vector3f(0.00f, 0.0f, 0.0f);   // 基础色
    private static final Vector3f GLOW_COLOR = new Vector3f(0.6f, 0.4f, 0.7f);    // 发光效果颜色
    // 移除阴影颜色

    // 动画和效果参数
    private static final float WAVE_SPEED = 0.8f;
    private static final float FLOW_SPEED = 1.2f;
    private static final float PARTICLE_DENSITY = 0.8f;

    // 系统组件
    private static final Random random = new Random();
    private static long lastUpdateTime = System.currentTimeMillis();
    private static float currentWaveIntensity = 0.8f;
    private static Matrix4f transformMatrix = new Matrix4f();
    private static float[] noiseTexture = generateNoiseTexture();

    // 渲染状态
    private static boolean isHologramActive = false;
    private static float currentOpacity = BASE_OPACITY;
    private static Vector3f currentColor = new Vector3f(BASE_COLOR);

    /**
     * 生成噪声纹理用于流体效果
     */
    private static float[] generateNoiseTexture() {
        float[] noise = new float[64 * 64];
        for (int i = 0; i < noise.length; i++) {
            noise[i] = random.nextFloat();
        }
        return noise;
    }

    /**
     * 更新动态效果参数
     */
    private void updateStygianEffect() {
        long currentTime = System.currentTimeMillis();
        float deltaTime = (currentTime - lastUpdateTime) / 1000.0f;
        lastUpdateTime = currentTime;

        // 更新波动强度 - 增强波动对比度
        float targetWave = 0.7f + (float) (0.5f * Math.sin(currentTime * 0.001));
        currentWaveIntensity += (targetWave - currentWaveIntensity) * deltaTime * WAVE_SPEED;

        // 更新全息效果
        if (random.nextFloat() < 0.05f) {
            isHologramActive = !isHologramActive;
        }

        // 更新颜色
        updateColors(deltaTime);

        // 更新变换矩阵
        updateTransformMatrix(deltaTime);
    }

    /**
     * 更新颜色系统
     */
    private void updateColors(float deltaTime) {
        // 基础颜色过渡
        Vector3f targetColor = isHologramActive ? GLOW_COLOR : BASE_COLOR;
            // 平衡颜色过渡速率
            currentColor.x += (targetColor.x - currentColor.x) * deltaTime * 1.0f;
            currentColor.y += (targetColor.y - currentColor.y) * deltaTime * 1.0f;
            currentColor.z += (targetColor.z - currentColor.z) * deltaTime * 1.0f;

        // 不透明度波动 - 增强波动范围
        currentOpacity = BASE_OPACITY * (0.7f + 0.3f * (float)Math.sin(lastUpdateTime * 0.002));
    }

    /**
     * 更新变换矩阵
     */
    private void updateTransformMatrix(float deltaTime) {
        // 重置矩阵
        transformMatrix.setIdentity();

        // 添加旋转
        float angle = (float) (lastUpdateTime * 0.0001);
        transformMatrix.rotate(angle, new Vector3f(0, 1, 0));

        // 添加缩放波动 - 增强缩放效果
        float scale = 1.0f + 0.08f * (float)Math.sin(lastUpdateTime * 0.003);
        transformMatrix.scale(new Vector3f(scale, scale, scale));
    }

    @Override
    public boolean renderFluidDisplayItem(ItemRenderType type, ItemStack aStack, Object... data) {
        try {
            Item item = aStack.getItem();
            if (item == null) return false;

            IIcon icon = item.getIconFromDamage(aStack.getItemDamage());
            if (icon == null) return false;

            updateStygianEffect();
            renderStygianEffect(type, aStack, icon, true, data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack aStack, Object... data) {
        updateStygianEffect();

        short aMetaData = (short) aStack.getItemDamage();
        if (!(aStack.getItem() instanceof IGT_ItemWithMaterialRenderer aItem)) return;

        int passes = aItem.requiresMultipleRenderPasses() ? aItem.getRenderPasses(aMetaData) : 1;

        for (int pass = 0; pass < passes; pass++) {
            IIcon tIcon = aItem.getIcon(aMetaData, pass);
            IIcon tOverlay = aItem.getOverlayIcon(aMetaData, pass);

            setupRenderState();

            if (tIcon != null) {
                markNeedsAnimationUpdate(tIcon);
                renderStygianEffect(type, aStack, tIcon, false, data);
            }

            if (tOverlay != null) {
                renderOverlay(type, tOverlay);
            }

            restoreRenderState();
        }
    }

    /**
     * 设置渲染状态
     */
    private void setupRenderState() {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_LIGHTING);
    }

    /**
     * 恢复渲染状态
     */
    private void restoreRenderState() {
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    /**
     * 渲染叠加层
     */
    private void renderOverlay(ItemRenderType type, IIcon overlay) {
        GL11.glColor4f(
            currentColor.x * 1.2f,
            currentColor.y * 1.2f,
            currentColor.z * 1.2f,
            0.7f
        );

        TextureUtils.bindAtlas(0);
        markNeedsAnimationUpdate(overlay);
        renderItemOverlay(type, overlay);
    }

    /**
     * 核心渲染方法 - 无阴影版本
     */
    private void renderStygianEffect(ItemRenderType type, ItemStack aStack, IIcon tIcon, boolean fluidDisplay, Object... data) {
        if (!Avaritia.isModLoaded()) {
            GTRenderUtil.renderItem(type, tIcon);
            return;
        }

        setupAdvancedRendering(type, aStack, fluidDisplay);

        // 应用自定义着色器效果
        applyStygianShader();

        // 渲染主体 - 直接渲染，无阴影
        GTRenderUtil.renderItem(type, tIcon);

        // 渲染粒子效果
        if (type != ItemRenderType.INVENTORY) {
            renderParticleEffects();
        }

        // 清理着色器状态
        cleanupRendering(type);
    }

    /**
     * 设置高级渲染状态
     */
    private void setupAdvancedRendering(ItemRenderType type, ItemStack stack, boolean fluidDisplay) {
        if (type == ItemRenderType.INVENTORY) {
            setupInventoryRendering(stack, fluidDisplay);
        } else {
            setup3DRendering();
        }
    }

    /**
     * 设置物品栏渲染
     */
    private void setupInventoryRendering(ItemStack stack, boolean fluidDisplay) {
        try {
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glDisable(GL11.GL_DEPTH_TEST);

            if (fluidDisplay && stack != null) {
                Minecraft mc = Minecraft.getMinecraft();
                if (mc != null) {
                    ResourceLocation resourcelocation = mc.getTextureManager().getResourceLocation(stack.getItemSpriteNumber());
                    if (resourcelocation != null) {
                        mc.getTextureManager().bindTexture(resourcelocation);
                    }
                }
            }
        } catch (Exception e) {
            // 恢复默认状态
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            RenderHelper.disableStandardItemLighting();
        }
    }

    /**
     * 设置3D渲染
     */
    private void setup3DRendering() {
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }

    /**
     * 应用冥河着色器效果 - 无阴影版本
     */
    private void applyStygianShader() {
        // 设置基础参数
        CosmicRenderShenanigans.cosmicOpacity = currentOpacity * currentWaveIntensity;
        CosmicRenderShenanigans.inventoryRender = false;

        // 应用自定义颜色 - 确保颜色平衡，无阴影
        GL11.glColor4f(
            Math.min(1.0f, currentColor.x * currentWaveIntensity),
            Math.min(1.0f, currentColor.y * currentWaveIntensity),
            Math.min(1.0f, currentColor.z * currentWaveIntensity),
            1.0f
        );

        // 禁用阴影相关设置
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_LIGHT0);
        GL11.glDisable(GL11.GL_LIGHT1);

        // 激活着色器
        CosmicRenderShenanigans.useShader();

        // 设置额外的着色器参数
        if (isHologramActive) {
            applyHologramEffect();
        }
    }

    /**
     * 应用全息效果 - 简化版本
     */
    private void applyHologramEffect() {
        float time = (float) (System.currentTimeMillis() % 10000) / 10000.0f;

        // 设置基础渲染状态
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        // 修改颜色以产生全息效果
        float hologramAlpha = 0.2f + 0.4f * (float)Math.sin(time * Math.PI * 2);
        GL11.glColor4f(
            Math.min(1.0f, currentColor.x * (1.0f + 0.2f * (float)Math.sin(time * 5.0f))),
            Math.min(1.0f, currentColor.y * (1.0f + 0.2f * (float)Math.cos(time * 4.0f))),
            Math.min(1.0f, currentColor.z * (1.0f + 0.2f * (float)Math.sin(time * 3.0f))),
            hologramAlpha
        );
    }

    /**
     * 渲染粒子效果
     */
    private void renderParticleEffects() {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glPointSize(2.0f);
        GL11.glBegin(GL11.GL_POINTS);

        for (int i = 0; i < 20 * PARTICLE_DENSITY; i++) {
            float x = random.nextFloat() * 2 - 1;
            float y = random.nextFloat() * 2 - 1;
            float z = random.nextFloat() * 2 - 1;

            if (x * x + y * y + z * z <= 1) {
                GL11.glColor4f(
                    Math.min(1.0f, GLOW_COLOR.x),
                    Math.min(1.0f, GLOW_COLOR.y),
                    Math.min(1.0f, GLOW_COLOR.z),
                    random.nextFloat() * 0.5f
                );
                GL11.glVertex3f(x, y, z);
            }
        }

        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }

    /**
     * 清理渲染状态 - 无阴影版本
     */
    private void cleanupRendering(ItemRenderType type) {
        CosmicRenderShenanigans.releaseShader();

        // 恢复默认光照状态
        GL11.glEnable(GL11.GL_LIGHTING);

        if (type == ItemRenderType.INVENTORY) {
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        }
    }

    /**
     * 处理光照 - 无阴影版本
     */
    private void processLightLevel(ItemRenderType type, Object... data) {
        // 使用统一的光照级别，不区分环境
        CosmicRenderShenanigans.setLightLevel(10.0f * currentWaveIntensity);
    }

    /**
     * 从实体位置设置光照 - EntityItem 版本
     */
    private void setLightFromEntity(EntityItem ent) {
        CosmicRenderShenanigans.setLightFromLocation(
            ent.worldObj,
            MathHelper.floor_double(ent.posX),
            MathHelper.floor_double(ent.posY),
            MathHelper.floor_double(ent.posZ)
        );
    }

    /**
     * 从实体位置设置光照 - EntityLivingBase 版本
     */
    private void setLightFromEntity(EntityLivingBase ent) {
        CosmicRenderShenanigans.setLightFromLocation(
            ent.worldObj,
            MathHelper.floor_double(ent.posX),
            MathHelper.floor_double(ent.posY),
            MathHelper.floor_double(ent.posZ)
        );
    }
}
