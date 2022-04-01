package nerdhub.cardinalenergy.impl.example;

import nerdhub.cardinalenergy.api.IEnergyHandler;
import nerdhub.cardinalenergy.impl.EnergyStorage;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;

/**
 * An example impl of {@link IEnergyHandler}
 */
public class BlockEntityEnergyImpl extends BlockEntity implements IEnergyHandler {

    /**
     * Create an EnergyStorage instance that stores 10,000 energy
     * BlockComponentProvider is implemented on the Block to reference this
     */
    public EnergyStorage storage = new EnergyStorage(10000);

    public BlockEntityEnergyImpl(BlockEntityType<?> blockEntityType) {
        super(blockEntityType);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        //Write energy to nbt
        this.storage.toTag(tag);
        return tag;
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        //Read energy from nbt
        this.storage.fromTag(tag);
    }
}
