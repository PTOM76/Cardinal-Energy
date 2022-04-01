package nerdhub.cardinalenergy.impl;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import dev.onyxstudios.cca.api.v3.component.CopyableComponent;
import nerdhub.cardinalenergy.DefaultKeys;
import nerdhub.cardinalenergy.api.IEnergyHandler;
import nerdhub.cardinalenergy.api.IEnergyStorage;
import nerdhub.cardinalenergy.impl.example.BlockEntityEnergyImpl;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * An implementation of {@link IEnergyStorage}
 *
 * An example implementation of this can be found at {@link BlockEntityEnergyImpl}
 */
public class EnergyStorage implements IEnergyStorage {

    private int capacity;
    private int energyStored;

    public EnergyStorage() {
        this(0, 0);
    }

    public EnergyStorage(int capacity) {
        this(capacity, 0);
    }

    public EnergyStorage(int capacity, int amount) {
        this.capacity = capacity;
        this.energyStored = amount;
    }

    @Override
    public int receiveEnergy(int amount) {
        int received = amount;

        if (received + energyStored > capacity) {
            received = amount - ((amount + energyStored) - capacity);
        }

        this.energyStored += received;
        return received;
    }

    @Override
    public int sendEnergy(World world, BlockPos pos, int amount) {
        if(amount <= energyStored) {

            if(isEnergyReceiver(world, pos)) {
                int amountReceived = getEnergyReceiver(world, pos).receiveEnergy(amount);
                this.extractEnergy(amountReceived);
                return amountReceived;
            }
        }

        return 0;
    }

    @Override
    public int extractEnergy(int amount) {
        int extracted = amount;

        if(extracted > energyStored) {
            extracted = energyStored;
        }

        this.energyStored -= extracted;
        return extracted;
    }

    @Override
    public int getEnergyStored() {
        return energyStored;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public void setCapacity(int maxCapacity) {
        this.capacity = maxCapacity;
    }

    @Override
    public void setEnergyStored(int energy) {
        this.energyStored = energy;
    }

    @Override
    public boolean canReceive(int amount) {
        return energyStored + amount <= capacity;
    }

    @Override
    public boolean canExtract(int amount) {
        return amount <= energyStored;
    }

    @Override
    public void writeToNbt(NbtCompound nbt) {
        nbt.putInt("capacity", capacity);
        nbt.putInt("energyStored", energyStored);
    }

    @Override
    public void readFromNbt(NbtCompound nbt) {
        capacity = nbt.getInt("capacity");
        energyStored = nbt.getInt("energyStored");
    }

    @Override
    public void copyFrom(Component other) {
        NbtCompound nbt = new NbtCompound();
        other.writeToNbt(nbt);
        readFromNbt(nbt);
    }

    @Override
    public CopyableComponent newInstance() {
        return new EnergyStorage(capacity, energyStored);
    }

    @Override
    public boolean isComponentEqual(Component other) {
        if (!(other instanceof EnergyStorage)) return false;
        EnergyStorage otherStorage = (EnergyStorage) other;
        return (capacity == otherStorage.capacity && energyStored == otherStorage.energyStored);
    }

    public IEnergyStorage getEnergyReceiver(World world, BlockPos pos) {
        ComponentProvider componentProvider = (ComponentProvider) world.getBlockState(pos).getBlock();

        if(world.getBlockEntity(pos) instanceof IEnergyHandler && DefaultKeys.CARDINAL_ENERGY.isProvidedBy(componentProvider)) {
            IEnergyHandler energyHandler = (IEnergyHandler) world.getBlockEntity(pos);
            return energyHandler.canConnectEnergy(null, DefaultKeys.CARDINAL_ENERGY) ? componentProvider.getComponent(DefaultKeys.CARDINAL_ENERGY) : null;
        }

        return null;
    }

    public boolean isEnergyReceiver(World world, BlockPos pos) {
        ComponentProvider componentProvider = (ComponentProvider) world.getBlockState(pos).getBlock();

        if(world.getBlockEntity(pos) instanceof IEnergyHandler && DefaultKeys.CARDINAL_ENERGY.isProvidedBy(componentProvider)) {
            return ((IEnergyHandler) world.getBlockEntity(pos)).isEnergyReceiver(null, DefaultKeys.CARDINAL_ENERGY);
        }

        return false;
    }
}