package org.theshadowsoftime.chemistrycraft.api.chemistry.chemmath;

public enum Units {

    MOLES("mol"),
    MOLARITY("M"),
    MOLALITY("m");

    String unitRep;

    Units(String unitRep){
        this.unitRep = unitRep;
    }
}
