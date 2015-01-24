package org.theshadowsoftime.chemistrycraft.chemistry;

public class ElectronConfiguration {

    int sShell;
    int pShell;
    int dShell;
    int fShell;

    //long form
    public ElectronConfiguration(int sShell, int pShell, int dShell, int fShell) {
        this.sShell = sShell;
        this.pShell = pShell;
        this.dShell = dShell;
        this.fShell = fShell;
    }

    //noble gas form
    public ElectronConfiguration(ElectronConfiguration nobleGas, int... otherShells) {

    }
}