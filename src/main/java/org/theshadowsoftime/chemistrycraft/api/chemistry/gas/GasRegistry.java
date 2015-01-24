package org.theshadowsoftime.chemistrycraft.api.chemistry.gas;

import net.minecraftforge.fluids.Fluid;

import java.util.ArrayList;
import java.util.List;

public class GasRegistry {

    private static ArrayList<Gas> registeredGases = new ArrayList<Gas>();

    public static Gas registerGas(Gas gas) {
        if (gas == null) {
            return null;
        }
        registeredGases.add(gas);
        return gas;
    }

    public static Gas getGas(int id) {
        return registeredGases.get(id);
    }

    public static Gas getGas(Fluid f) {
        for (Gas gas : registeredGases) {
            if (gas.hasFluid() && gas.getFluid() == f) {
                return gas;
            }
        }
        return null;
    }

    public static boolean containsGas(String name) {
        return getGas(name) != null;
    }

    public static List<Gas> getRegisteredGases() {
        return (List<Gas>)registeredGases.clone();
    }

    public static Gas getGas(String name) {
        for (Gas gas : registeredGases) {
            if (gas.getName().toLowerCase().equals(name.toLowerCase())) {
                return gas;
            }
        }
        return null;
    }

    public static int getGasId(Gas gas) {
        if (gas == null || !containsGas(gas.getName())) {
            return -1;
        }
        return registeredGases.indexOf(gas);
    }
}
