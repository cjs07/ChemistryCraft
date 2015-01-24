package org.theshadowsoftime.chemistrycraft.client.guis;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.theshadowsoftime.chemistrycraft.common.inventory.ContainerGasCollector;
import org.theshadowsoftime.chemistrycraft.lib.Constants;
import org.theshadowsoftime.chemistrycraft.common.tileentities.TileEntityGasCollector;

public class GuiGasCollector extends GuiContainer {

    private int xSize, ySize;

    private ResourceLocation backgroundImage = new ResourceLocation(Constants.MODID + ":" +
            "textures/client/gui/GuiGasCollector.png");

    private TileEntityGasCollector tileEntity;

    public GuiGasCollector(InventoryPlayer playerInventory, TileEntityGasCollector tileEntity) {
        super(new ContainerGasCollector(playerInventory, tileEntity));
        this.tileEntity = tileEntity;
        xSize = 176;
        ySize = 214;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float renderPartialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(backgroundImage);
        int x = (this.width - xSize) / 2;
        int y = (this.height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
