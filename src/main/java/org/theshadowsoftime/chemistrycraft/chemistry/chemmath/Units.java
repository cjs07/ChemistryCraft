package org.theshadowsoftime.chemistrycraft.chemistry.chemmath;

public enum Units {

    MOLES("mol"),
    MOLARITY("M"),
    MOLALITY("m");

    String unitRep;

    Units(String unitRep){
        this.unitRep = unitRep;
    }
}
