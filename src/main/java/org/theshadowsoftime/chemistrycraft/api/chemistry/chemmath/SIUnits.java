package org.theshadowsoftime.chemistrycraft.api.chemistry.chemmath;

public enum SIUnits {

    GRAMS("g"),
    LITERS("L"),
    METERS("m");

    String unitRep;

    SIUnits(String unitRep){
        this.unitRep = unitRep;
    }
}
