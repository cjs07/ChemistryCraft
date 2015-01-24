package org.theshadowsoftime.chemistrycraft.chemistry.substances.elements;

import org.theshadowsoftime.chemistrycraft.chemistry.substances.Substance;

public abstract class Element extends Substance{

    final int atomicNumber;
    final String symbol;

    //TODO: ADD ELECTRON CONFIGURATIONS
    public Element(int atomicNumber, double molarMass, String name, String symbol, int standardState) {
        super(molarMass, name, standardState);
        this.atomicNumber = atomicNumber;
        this.symbol = symbol;
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }


    public String getSymbol() {
        return symbol;
    }
}
