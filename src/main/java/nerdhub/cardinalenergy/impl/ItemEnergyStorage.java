package nerdhub.cardinalenergy.impl;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.CopyableComponent;
import nerdhub.cardinalenergy.api.IEnergyItemStorage;
import nerdhub.cardinalenergy.api.IEnergyStorage;
import nerdhub.cardinalenergy.impl.example.ItemEnergyImpl;
import net.minecraft.nbt.NbtCompound;

/**
 * An implementation of {@link IEnergyItemStorage}
 *
 * An example implementation of this can be found at {@link ItemEnergyImpl}
 */
public class ItemEnergyStorage implements IEnergyItemStorage {

    public static final String ENERGY_TAG = "cardinal";

    private int capacity;
    private int energyStored;

    public ItemEnergyStorage(int capacity) {
        this(capacity, 0);
    }

    public ItemEnergyStorage(int capacity, int energyStored) {
        this.capacity = capacity;
        this.energyStored = 0;
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
    public int extractEnergy(int amount) {
        int extracted = amount;

        if (extracted > energyStored) {
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
    public void readFromNbt(NbtCompound tag) {
        if(tag.contains(ENERGY_TAG)) {
            NbtCompound energyData = tag.getCompound(ENERGY_TAG);
            this.capacity = energyData.getInt("capacity");
            this.energyStored = energyData.getInt("energyStored");
        }
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        NbtCompound energyData = new NbtCompound();
        energyData.putInt("capacity", capacity);
        energyData.putInt("energyStored", energyStored);

        tag.put(ENERGY_TAG, energyData);
    }

    @Override
    public boolean isComponentEqual(Component other) {
        return other instanceof IEnergyStorage && ((IEnergyStorage) other).getEnergyStored() == energyStored && ((IEnergyStorage) other).getCapacity() == capacity;
    }

    @Override
    public CopyableComponent newInstance() {
        return new ItemEnergyStorage(capacity, energyStored);
    }

    @Override
    public void copyFrom(Component other) {
        NbtCompound nbt = new NbtCompound();
        other.writeToNbt(nbt);
        readFromNbt(nbt);
    }
}
