package crypticlib.nms.nbt.v1_18_R2;

import crypticlib.nms.nbt.AbstractNbtIntArray;
import net.minecraft.nbt.NBTTagIntArray;

public class V1_18_R2NbtIntArray extends AbstractNbtIntArray {

    public V1_18_R2NbtIntArray(int[] value) {
        super(value);
    }

    public V1_18_R2NbtIntArray(Object nmsNbtIntArray) {
        super(nmsNbtIntArray);
    }

    @Override
    public void fromNms(Object nmsNbt) {
        NBTTagIntArray nbtTagIntArray = (NBTTagIntArray) nmsNbt;
        setValue(nbtTagIntArray.f());
    }

    @Override
    public NBTTagIntArray toNms() {
        return new NBTTagIntArray(value());
    }
}
