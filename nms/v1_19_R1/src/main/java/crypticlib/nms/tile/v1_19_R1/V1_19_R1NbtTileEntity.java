package crypticlib.nms.tile.v1_19_R1;

import crypticlib.nms.nbt.v1_19_R1.V1_19_R1NbtTagCompound;
import crypticlib.nms.tile.NbtTileEntity;
import crypticlib.util.ReflectUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.level.block.entity.TileEntity;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_19_R1.block.CraftBlockEntityState;

import java.lang.reflect.Method;

public class V1_19_R1NbtTileEntity extends NbtTileEntity {

    public V1_19_R1NbtTileEntity(BlockState bukkit) {
        super(bukkit);
    }

    @Override
    public void saveNbtToTileEntity() {
        CraftBlockEntityState<?> craftTileEntity = ((CraftBlockEntityState<?>) bukkit);
        Method getSnapshotMethod = ReflectUtil.getDeclaredMethod(CraftBlockEntityState.class, "getSnapshot");
        TileEntity nmsTileEntity = (TileEntity) ReflectUtil.invokeDeclaredMethod(getSnapshotMethod, craftTileEntity);
        nmsTileEntity.a((NBTTagCompound) nbtTagCompound.toNms());
    }

    @Override
    public void fromBukkit() {
        NBTTagCompound nms = ((CraftBlockEntityState<?>) bukkit).getSnapshotNBT();
        setNbtTagCompound(new V1_19_R1NbtTagCompound(nms));
    }
}
