package com.sakurac1oud.SenseofCherryBlossom.utils;

import com.sakurac1oud.SenseofCherryBlossom.Tags;
import gregtech.api.util.GTLanguageManager;
import net.minecraft.util.EnumChatFormatting;

public class TextLocalization {

    // region general
    public static final String ModName = Tags.MODNAME;
    // #tr ModNameDesc
    // # Added by {\GREEN}ModName{\GRAY}
    // #zh_CN 由 {\GREEN}Twist Space Technology{\GRAY} 添加{\RESET}
    public static final String ModNameDesc = TextEnums.tr("ModNameDesc");

    // #tr HeatCapacity
    // # Heat Capacity:
    // #zh_CN 热容:
    public static final String HeatCapacity = TextEnums.tr("HeatCapacity");

    // #tr FluidCapacity
    // # Capacity:
    // #zh_CN 容量:
    public static final String FluidCapacity = TextEnums.tr("FluidCapacity");

    // #tr HatchTier
    // # Hatch Tier:
    // #zh_CN 仓室等级:
    public static final String HatchTier = TextEnums.tr("HatchTier");

    // #tr Kelvin
    // #  K
    // #zh_CN  K
    public static final String Kelvin = TextEnums.tr("Kelvin");

    // #tr AutoSeparation
    // # Automatically separate inputs
    // #zh_CN 自动隔离输入
    public static final String AutoSeparation = TextEnums.tr("AutoSeparation");

    // #tr Text_SeparatingLine
    // # {\GOLD}-----------------------------------------
    // #zh_CN {\GOLD}-----------------------------------------
    public static final String Text_SeparatingLine = TextEnums.tr("Text_SeparatingLine");

    // #tr BLUE_PRINT_INFO
    // # Follow the {\BLUE} Structure{\DARK_BLUE}Lib{\GRAY} hologram projector to build the main structure.
    // #zh_CN 请参考{\BLUE}Structure{\DARK_BLUE}Lib{\GRAY}全息投影，构建主体结构
    public static final String BLUE_PRINT_INFO = TextEnums.tr("BLUE_PRINT_INFO");

    // #tr DSPName
    // # {\BLUE}Dyson Sphere Program
    // #zh_CN {\BLUE}戴森球计划
    public static final String DSPName = TextEnums.tr("DSPName");

    // #tr Tooltip_Details
    // # {\LIGHT_PURPLE}Details:
    // #zh_CN {\LIGHT_PURPLE}具体细节:
    public static final String Tooltip_Details = TextEnums.tr("Tooltip_Details");

    // #tr Tooltips_JoinWirelessNetWithoutEnergyHatch
    // # Joining the wireless EU network when without installing an energy hatch.
    // #zh_CN 未安装能源仓时自动进入无线电力网络模式.
    public static final String Tooltips_JoinWirelessNetWithoutEnergyHatch = TextEnums.tr("Tooltips_JoinWirelessNetWithoutEnergyHatch");

    public static final String mNoMobsToolTip = GTLanguageManager
        .addStringLocalization("gt.nomobspawnsonthisblock", "Mobs cannot Spawn on this Block");
    public static final String mNoTileEntityToolTip = GTLanguageManager
        .addStringLocalization("gt.notileentityinthisblock", "This is NOT a TileEntity!");
    // endregion

    // region Waila

    // #tr Waila.General.WirelessMode
    // # Wireless Mode
    // #zh_CN 无线模式
    public static final String Waila_WirelessMode = TextEnums.tr("Waila.General.WirelessMode");

    // #tr Waila.General.CurrentEuCost
    // # Current EU Cost
    // #zh_CN 当前EU消耗
    public static final String Waila_CurrentEuCost = TextEnums.tr("Waila.General.CurrentEuCost");

    // endregion

    // region getInfoData

    // #tr General.getInfoData.Wireless_mode_enabled
    // # Wireless mode enabled
    // #zh_CN 已启用无线模式
    public static final String Info_Wireless_mode_enabled = TextEnums.tr("General.getInfoData.Wireless_mode_enabled");

    // endregion

    // region Names
    public static final String name_Nxer = "" + EnumChatFormatting.RED
        + EnumChatFormatting.BOLD
        + EnumChatFormatting.ITALIC
        + EnumChatFormatting.UNDERLINE
        + "N"
        + EnumChatFormatting.GREEN
        + EnumChatFormatting.BOLD
        + EnumChatFormatting.ITALIC
        + EnumChatFormatting.UNDERLINE
        + "x"
        + EnumChatFormatting.AQUA
        + EnumChatFormatting.BOLD
        + EnumChatFormatting.ITALIC
        + EnumChatFormatting.UNDERLINE
        + "e"
        + EnumChatFormatting.BLUE
        + EnumChatFormatting.BOLD
        + EnumChatFormatting.ITALIC
        + EnumChatFormatting.UNDERLINE
        + "r";

    public static final String authorName_Nxer = "Author: " + name_Nxer;
    // endregion

    // region special hatch info

    // #tr Tooltip_DoNotNeedMaintenance
    // # Do Not Need Maintenance!
    // #zh_CN 不需要维护仓!
    public static final String Tooltip_DoNotNeedMaintenance = TextEnums.tr("Tooltip_DoNotNeedMaintenance");

    // #tr Tooltip_DoNotNeedEnergyHatch
    // # Do Not Need Energy Hatch!
    // #zh_CN 不需要能源仓!
    public static final String Tooltip_DoNotNeedEnergyHatch = TextEnums.tr("Tooltip_DoNotNeedEnergyHatch");

    // #tr Mark_TwistSpaceTechnology_TecTech
    // # {\AQUA}{\BOLD}ModName : {\RESET}CommonValues.TEC_MARK_SHORT
    // #zh_CN {\AQUA}{\BOLD}Twist Space Technology : {\RESET}{\BLUE}Tec{\DARK_BLUE}Tech
    public static final String Mark_TwistSpaceTechnology_TecTech = TextEnums.tr("Mark_TwistSpaceTechnology_TecTech");

    // endregion

    // region get info message
    // #tr infoText_CurrentStellarCoefficient
    // # Current Stellar Coefficient:
    // #zh_CN 当前恒星系数:
    public static final String infoText_CurrentStellarCoefficient = TextEnums.tr("infoText_CurrentStellarCoefficient");

    // #tr infoText_CurrentPlanetCoefficient
    // # Current Planet Coefficient:
    // #zh_CN 当前行星系数:
    public static final String infoText_CurrentPlanetCoefficient = TextEnums.tr("infoText_CurrentPlanetCoefficient");

    // endregion

    // region casing

    // #tr textCasing
    // # Casing
    // #zh_CN Casing
    public static final String textCasing = TextEnums.tr("textCasing");

    // #tr textUseBlueprint
    // # Use {\BLUE}Blue{\AQUA}print{\RESET} to preview
    // #zh_CN 用{\BLUE}蓝{\AQUA}图{\RESET}预览
    public static final String textUseBlueprint = TextEnums.tr("textUseBlueprint");

    // #tr textColon
    // # :{\SPACE}
    // #zh_CN ：{\SPACE}
    public static final String textColon = TextEnums.tr("textColon");

    public static String getBlueprintWithDot(int dot) {
        // #tr textDot
        // # Dot
        // #zh_CN 提示方块
        return textUseBlueprint + EnumChatFormatting.WHITE + " " + TextEnums.tr("textDot") + " : " + EnumChatFormatting.AQUA + dot;
    }

    // #tr textSpace
    // # {\SPACE}
    // #zh_CN {\SPACE}
    public static final String textSpace = TextEnums.tr("textSpace");

    // #tr textAnyCasing
    // # Any Casing
    // #zh_CN 任意机械方块
    public static final String textAnyCasing = TextEnums.tr("textAnyCasing");

    // #tr textTopCenter
    // # Top center
    // #zh_CN 顶层中央
    public static final String textTopCenter = TextEnums.tr("textTopCenter");

    // #tr textFrontCenter
    // # Front center
    // #zh_CN 正面中央
    public static final String textFrontCenter = TextEnums.tr("textFrontCenter");

    // #tr textFrontBottom
    // # Front bottom
    // #zh_CN 正面底中
    public static final String textFrontBottom = TextEnums.tr("textFrontBottom");

    // #tr textCenterOfLRSides
    // # Center area of left and right side
    // #zh_CN 左右两侧的中央区域
    public static final String textCenterOfLRSides = TextEnums.tr("textCenterOfLRSides");

    // #tr textCenterOfUDSides
    // # Center area of up and down side
    // #zh_CN 上下侧的中央区域
    public static final String textCenterOfUDSides = TextEnums.tr("textCenterOfUDSides");

    // #tr textEndSides
    // # Machine end
    // #zh_CN 机器末端
    public static final String textEndSides = TextEnums.tr("textEndSides");

    // #tr StructureTooComplex
    // # The structure is too complex!
    // #zh_CN 结构太复杂了！
    public static final String StructureTooComplex = TextEnums.tr("StructureTooComplex");

    // #tr textCasingAdvIrPlated
    // # Advanced Iridium Plated Machine Casing
    // #zh_CN 强化镀铱机械方块
    public static final String textCasingAdvIrPlated = TextEnums.tr("textCasingAdvIrPlated");

    // #tr textCasingTT_0
    // # High Power Casing
    // #zh_CN 超能机械方块
    public static final String textCasingTT_0 = TextEnums.tr("textCasingTT_0");

    // #tr textAroundController
    // # Around the Controller
    // #zh_CN 控制器方块周围
    public static final String textAroundController = TextEnums.tr("textAroundController");

    // #tr textScrewdriverChangeMode
    // # Use screwdriver to change mode.
    // #zh_CN 使用螺丝刀切换模式.
    public static final String textScrewdriverChangeMode = TextEnums.tr("textScrewdriverChangeMode");

    // endregion

    // region general tooltips

    // #tr Tooltip_GlassTierLimitEnergyHatchTier
    // # The Glass Tier limit the Energy hatch voltage Tier.
    // #zh_CN 玻璃等级限制能源仓等级.
    public static final String Tooltip_GlassTierLimitEnergyHatchTier = TextEnums.tr("Tooltip_GlassTierLimitEnergyHatchTier");

    public static final String NameCherryBlossomLCR = TextEnums.tr("NameCherryBlossomLCR");

    public static final String Tooltip_CherryBlossomLCR_MachineType = TextEnums.tr("Tooltip_CherryBlossomLCR_MachineType");
    public static final String Tooltip_CherryBlossomLCR_00 = TextEnums.tr("Tooltip_CherryBlossomLCR_00");

    public static final String Tooltip_CherryBlossomLCR_01 = TextEnums.tr("Tooltip_CherryBlossomLCR_01");

    public static final String Tooltip_CherryBlossomLCR_02 = TextEnums.tr("Tooltip_CherryBlossomLCR_02");

    public static final String Tooltip_CherryBlossomLCR_03 = TextEnums.tr("Tooltip_CherryBlossomLCR_03");

    public static final String Tooltip_CherryBlossomLCR_04 = TextEnums.tr("Tooltip_CherryBlossomLCR_04");

    public static final String NameCherryBlossomBorderofLife = TextEnums.tr("NameCherryBlossomBorderofLife");
    public static final String Tooltip_CherryBlossomBorderofLife_MachineType = TextEnums.tr("Tooltip_CherryBlossomBorderofLife_MachineType");
    public static final String Tooltip_CherryBlossomBorderofLife_00 = TextEnums.tr("Tooltip_CherryBlossomBorderofLife_00");
    public static final String Tooltip_CherryBlossomBorderofLife_01 = TextEnums.tr("Tooltip_CherryBlossomBorderofLife_01");
    public static final String Tooltip_CherryBlossomBorderofLife_02 = TextEnums.tr("Tooltip_CherryBlossomBorderofLife_02");
    public static final String Tooltip_CherryBlossomBorderofLife_03 = TextEnums.tr("Tooltip_CherryBlossomBorderofLife_03");
    public static final String Tooltip_CherryBlossomBorderofLife_04 = TextEnums.tr("Tooltip_CherryBlossomBorderofLife_04");
    public static final String Tooltip_CherryBlossomBorderofLife_05 = TextEnums.tr("Tooltip_CherryBlossomBorderofLife_05");
    public static final String Tooltip_CherryBlossomBorderofLife_06 = TextEnums.tr("Tooltip_CherryBlossomBorderofLife_06");
    public static final String Tooltip_CherryBlossomBorderofLife_07 = TextEnums.tr("Tooltip_CherryBlossomBorderofLife_07");
    public static final String Tooltip_CherryBlossomBorderofLife_08 = TextEnums.tr("Tooltip_CherryBlossomBorderofLife_08");
    public static final String Tooltip_CherryBlossomBorderofLife_09 = TextEnums.tr("Tooltip_CherryBlossomBorderofLife_09");
    public static final String Tooltip_CherryBlossomBorderofLife_CoilInfo = TextEnums.tr("Tooltip_CherryBlossomBorderofLife_CoilInfo");

    public static final String NameCherryBlossomAssembler = TextEnums.tr("NameCherryBlossomAssembler");
    public static final String Tooltip_CherryBlossomAssembler_MachineType = TextEnums.tr("Tooltip_CherryBlossomAssembler_MachineType");
    public static final String Tooltip_CherryBlossomAssembler_Controller = TextEnums.tr("Tooltip_CherryBlossomAssembler_Controller");
    public static final String Tooltip_CherryBlossomAssembler_01 = TextEnums.tr("Tooltip_CherryBlossomAssembler_01");
    public static final String Tooltip_CherryBlossomAssembler_02 = TextEnums.tr("Tooltip_CherryBlossomAssembler_02");
    public static final String Tooltip_CherryBlossomAssembler_03 = TextEnums.tr("Tooltip_CherryBlossomAssembler_03");
    public static final String Tooltip_CherryBlossomAssembler_04 = TextEnums.tr("Tooltip_CherryBlossomAssembler_04");
    public static final String Tooltip_CherryBlossomAssembler_05 = TextEnums.tr("Tooltip_CherryBlossomAssembler_05");
    public static final String Tooltip_CherryBlossomAssembler_06 = TextEnums.tr("Tooltip_CherryBlossomAssembler_06");
    public static final String Tooltip_CherryBlossomAssembler_07 = TextEnums.tr("Tooltip_CherryBlossomAssembler_07");
    public static final String
            Tooltip_CherryBlossomAssembler_08 = TextEnums.tr("Tooltip_CherryBlossomAssembler_08");

}
