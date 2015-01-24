package org.theshadowsoftime.chemistrycraft.chemistry.substances;

public abstract class Substance {

    final double molarMass;
    final String name;
    final int standardState; //substances state at STP, 0 is solid, 1 is liquid, and 2 is gas

    public Substance(double molarMass, String name, int standardState) {
        if(standardState >= 3) {
            throw new IllegalArgumentException();
        }
        this.molarMass = molarMass;
        this.name = name;
        this.standardState = standardState;
    }
}
