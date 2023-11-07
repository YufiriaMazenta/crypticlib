package crypticlib.nms.nbt.v1_18_R1;

import crypticlib.nms.nbt.AbstractNbtFloat;
import net.minecraft.nbt.NBTTagFloat;

public class V1_18_R1NbtFloat extends AbstractNbtFloat {

    public V1_18_R1NbtFloat(float value) {
        super(value);
    }

    public V1_18_R1NbtFloat(Object nmsNbtFloat) {
        super(nmsNbtFloat);
    }

    @Override
    public void fromNms(Object nmsNbt) {
        NBTTagFloat nbtTagFloat = (NBTTagFloat) nmsNbt;
        setValue(nbtTagFloat.j());
    }

    @Override
    public NBTTagFloat toNms() {
        return NBTTagFloat.a(value());
    }

}
