package nerdhub.cardinalenergy.impl.example;

import nerdhub.cardinalenergy.api.IEnergyHandler;
import nerdhub.cardinalenergy.impl.EnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

/**
 * An example impl of {@link IEnergyHandler}
 */
public class BlockEntityEnergyImpl extends BlockEntity implements IEnergyHandler {

    /**
     * Create an EnergyStorage instance that stores 10,000 energy
     * BlockComponentProvider is implemented on the Block to reference this
     */
    public EnergyStorage storage = new EnergyStorage(10000);

    public BlockEntityEnergyImpl(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state);
    }

    @Override
    public void writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        //Write energy to nbt
        this.storage.writeToNbt(tag);
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        //Read energy from nbt
        this.storage.readFromNbt(tag);
    }
}
