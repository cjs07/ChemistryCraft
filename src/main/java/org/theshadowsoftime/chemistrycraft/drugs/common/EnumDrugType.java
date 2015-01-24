package org.theshadowsoftime.chemistrycraft.drugs.common;

import org.theshadowsoftime.chemistrycraft.drugs.sideeffects.DrugSideEffect;

import java.util.List;

public enum EnumDrugType {

    OPIATE(0, null),
    DEPRESSANT(0, null),
    STIMULANT(0, null);

    int id;
    List<DrugSideEffect> sideEffects;

    EnumDrugType(int id, List<DrugSideEffect> sideeffects) {
        this.id = id;
        this.sideEffects = sideeffects;
    }

    public int getId() {
        return id;
    }

    public List<DrugSideEffect> getSideEffects() {
        return sideEffects;
    }
}
