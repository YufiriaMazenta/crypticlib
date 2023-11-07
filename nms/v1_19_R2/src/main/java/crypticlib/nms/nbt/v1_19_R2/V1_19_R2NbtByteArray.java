package crypticlib.nms.nbt.v1_19_R2;

import crypticlib.nms.nbt.AbstractNbtByteArray;
import net.minecraft.nbt.NBTTagByteArray;

public class V1_19_R2NbtByteArray extends AbstractNbtByteArray {

    public V1_19_R2NbtByteArray(byte[] value) {
        super(value);
    }

    public V1_19_R2NbtByteArray(Object object) {
        super(object);
    }

    @Override
    public void fromNms(Object nmsNbt) {
        NBTTagByteArray nbtTagByteArray = (NBTTagByteArray) nmsNbt;
        setValue(nbtTagByteArray.e());
    }

    @Override
    public NBTTagByteArray toNms() {
        return new NBTTagByteArray(value());
    }

}
