package org.theshadowsoftime.chemistrycraft.api.chemistry.gas;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * An object of type gas (or one of it's subclasses) represents a substance in its gaseous form. Some substances will
 * not have registered gases due to impossible prerequisites or nonsensical conditions required for its creation (i.e.
 * carbon gas or iron gas).
 */
public class Gas {

    String name;

    /* Liquid form of the gas, only used in gasses in which the liquid form is obtainable (i.e. water, )*/
    Fluid fluid;

    IIcon icon;

    boolean fromFluid = false;

    /**
     * Creates a new gas object with the given name.
     * @param s The name for the gas.
     */
    public Gas(String s){
        name = s;
    }

    /**
     * Creates a new gas object from the specified fluid.
     * @param f The fluid to create the gas from
     */
    public Gas(Fluid f) {
        name = f.getName();
        icon = f.getStillIcon();
        fluid = f;
        fromFluid = true;
    }

    public String getName() {
        return name;
    }

    /**
     * Gets the IIcon associated with this Gas.
     * @return associated IIcon
     */
    public IIcon getIcon() {
        if(fromFluid) {
            return this.getFluid().getIcon();
        }

        return icon;
    }

    /**
     * Sets this gas's icon.
     * @param i - IIcon to associate with this Gas
     * @return this Gas object
     */
    public Gas setIcon(IIcon i) {
        icon = i;

        if(hasFluid()) {
            fluid.setIcons(getIcon());
        }

        fromFluid = false;

        return this;
    }

    /**
     * Gets the ID associated with this gas.
     * @return the associated gas ID
     */
    public int getId() {
        return GasRegistry.getGasId(this);
    }

    /**
     * Writes this Gas to a defined tag compound.
     * @param nbtTags - tag compound to write this Gas to
     * @return the tag compound this gas was written to
     */
    public NBTTagCompound write(NBTTagCompound nbtTags) {
        nbtTags.setString("gasName", getName());

        return nbtTags;
    }

    /**
     * Returns the Gas stored in the defined tag compound.
     * @param nbtTags - tag compound to get the Gas from
     * @return Gas stored in the tag compound
     */
    public static Gas readFromNBT(NBTTagCompound nbtTags) {
        if(nbtTags == null || nbtTags.hasNoTags())
        {
            return null;
        }

        return GasRegistry.getGas(nbtTags.getString("gasName"));
    }

    /**
     * Whether or not this Gas has an associated fluid.
     * @return if this gas has a fluid
     */
    public boolean hasFluid() {
        return fluid != null;
    }

    /**
     * Gets the fluid associated with this Gas.
     * @return fluid associated with this gas
     */
    public Fluid getFluid() {
        return fluid;
    }

    /**
     * Registers a new fluid out of this Gas or gets one from the FluidRegistry.
     * @return this Gas object
     */
    public Gas registerFluid() {
        if(fluid == null) {
            if(FluidRegistry.getFluid(getName()) == null) {
                fluid = new Fluid(getName()).setGaseous(true);
                FluidRegistry.registerFluid(fluid);
            }
            else {
                fluid = FluidRegistry.getFluid(getName());
            }
        }

        return this;
    }

    @Override
    public String toString() {
        return name;
    }
}
