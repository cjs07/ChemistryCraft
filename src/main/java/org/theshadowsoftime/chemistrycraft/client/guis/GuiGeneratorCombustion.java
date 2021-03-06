package org.theshadowsoftime.chemistrycraft.client.guis;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.theshadowsoftime.chemistrycraft.common.inventory.ContainerGeneratorCombustion;
import org.theshadowsoftime.chemistrycraft.lib.Constants;
import org.theshadowsoftime.chemistrycraft.common.tileentities.TileEntityGeneratorCombustion;

public class GuiGeneratorCombustion extends GuiContainer {

    private static final ResourceLocation texture = new ResourceLocation(Constants.MODID,
            "textures/gui/generatorCombustion.png");

    TileEntityGeneratorCombustion generator;

    public GuiGeneratorCombustion(InventoryPlayer invPlayer, TileEntityGeneratorCombustion generator) {
        super(new ContainerGeneratorCombustion(invPlayer, generator));
        this.generator = generator;
        xSize = 176;
        ySize = 214;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        fontRendererObj.drawString("Combustion Generator", 8,6, 0x404040);
        fontRendererObj.drawString("Energy Stored: " + generator.source.getEnergyStored(), 53, 83, 0x404040);
        System.out.println("[CC Debug] [GUI Updates] Combustion generator gui foreground layer drawn. RF Stored: " +
                generator.source.getEnergyStored());
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
