package nerdhub.cardinalenergy.api;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import net.minecraft.util.math.Direction;

/**
 * Implemented on a energy handler item to specify that it handles energy
 */
public interface IEnergyItemHandler extends IEnergyHandler {

    @Override
    default boolean canConnectEnergy(Direction direction, ComponentKey<?> type) {
        throw new IllegalStateException("Tried to access IEnergyHandler methods from an IEnergyItemHandler");
    }

    @Override
    default boolean isEnergyProvider(Direction direction, ComponentKey<?> type) {
        throw new IllegalStateException("Tried to access IEnergyHandler methods from an IEnergyItemHandler");
    }

    @Override
    default boolean isEnergyReceiver(Direction direction, ComponentKey<?> type) {
        throw new IllegalStateException("Tried to access IEnergyHandler methods from an IEnergyItemHandler");
    }
}
