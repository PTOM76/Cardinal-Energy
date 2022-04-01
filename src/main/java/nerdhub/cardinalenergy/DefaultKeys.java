package nerdhub.cardinalenergy;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import nerdhub.cardinalenergy.api.IEnergyItemStorage;
import nerdhub.cardinalenergy.api.IEnergyStorage;
import net.minecraft.util.Identifier;

/**
 * Default Cardinal Energy component type
 * Can be used or a custom Energy Component can be created using a new ComponentKey
 */
public class DefaultKeys {

    public static final String MOD_ID = "cardinalenergy";

    /**
     * The default Cardinal Energy ComponentKey
     * To create your own energy type simply create a new ComponentKey of IEnergyHandler
     */
    public static final ComponentKey<IEnergyStorage> CARDINAL_ENERGY = ComponentRegistry.getOrCreate(new Identifier(MOD_ID, "cardinal_energy"), IEnergyStorage.class);

    /**
     * The default Cardinal Energy mana ComponentKey, meant for entities
     */
    public static final ComponentKey<IEnergyItemStorage> MANA_COMPONENT = ComponentRegistry.getOrCreate(new Identifier(MOD_ID, "mana_type"), IEnergyItemStorage.class);
}
