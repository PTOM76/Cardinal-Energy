package nerdhub.cardinalenergy;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import nerdhub.cardinalenergy.api.IEnergyItemStorage;
import nerdhub.cardinalenergy.api.IEnergyStorage;

public class DefaultTypes extends DefaultKeys {
    // 互換性のためにおいておく

    public static final String MODID = DefaultKeys.MOD_ID;

    public static final ComponentKey<IEnergyStorage> CARDINAL_ENERGY = DefaultKeys.CARDINAL_ENERGY;

    public static final ComponentKey<IEnergyItemStorage> MANA_COMPONENT = DefaultKeys.MANA_COMPONENT;
}
