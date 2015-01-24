package org.theshadowsoftime.chemistrycraft.api.energy;

/**
 * Implement this class to make an item that runs off of energy instead of durability.
 */

public interface IEnergyTool {


    /**
     * Recharges the item for the specified amount.
     * @param energy The amount of energy to be added to the item.
     * @return the amount of energy successfully added;
     */
    public int recharge(int energy);

    /**
     * Removes the specified amount of energy from the item.
     * @param energy The amount of energy to be removed.
     * @return Whether or not there is enough energy stored in the tool to remove the specified amount.
     */
    public boolean removeEnergy(int energy);
}
