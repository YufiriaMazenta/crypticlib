package crypticlib.nms.nbt.v1_18_R2;

import crypticlib.nms.nbt.AbstractNbtLongArray;
import net.minecraft.nbt.NBTTagLongArray;

public class V1_18_R2NbtLongArray extends AbstractNbtLongArray {

    public V1_18_R2NbtLongArray(long[] value) {
        super(value);
    }

    public V1_18_R2NbtLongArray(Object nmsNbtLongArray) {
        super(nmsNbtLongArray);
    }

    @Override
    public void fromNms(Object nmsNbt) {
        NBTTagLongArray nbtTagLongArray = (NBTTagLongArray) nmsNbt;
        setValue(nbtTagLongArray.f());
    }

    @Override
    public NBTTagLongArray toNms() {
        return new NBTTagLongArray(value());
    }
}
