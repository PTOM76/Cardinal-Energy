package nerdhub.cardinalenergy.impl.example;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import nerdhub.cardinalenergy.DefaultKeys;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Set;

/**
 * An example impl of {@link BlockComponentProvider}
 */
public class BlockEnergyImpl extends BlockWithEntity implements Component {

    protected BlockEnergyImpl(Settings block$Settings_1) {
        super(block$Settings_1);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityEnergyImpl(null, pos, state);
    }

    /*
    /**
     * Check if the ComponentKey given is the Cardinal Energy Component
     * /
    @Override
    public <T extends Component> boolean hasComponent(BlockView blockView, BlockPos pos, ComponentKey<T> type, Direction side) {
        return type == DefaultKeys.CARDINAL_ENERGY;
    }

    /**
     * Get the IEnergyHandler Component the ComponentKey given is the Cardinal Energy Component
     * /
    @Override
    public <T extends Component> T getComponent(BlockView blockView, BlockPos pos, ComponentKey<T> type, Direction side) {
        return type == DefaultKeys.CARDINAL_ENERGY ? (T) blockView.getBlockEntity(pos) :  null;
    }

    /**
     * Get a list of all valid components this block supports
     * /
    @Override
    public Set<ComponentKey<?>> getComponentKeys(BlockView blockView, BlockPos pos, Direction side) {
        return Collections.singleton(DefaultKeys.CARDINAL_ENERGY);
    }

     */

    @Override
    public void readFromNbt(NbtCompound tag) {

    }

    @Override
    public void writeToNbt(NbtCompound tag) {

    }
}
