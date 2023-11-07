package crypticlib.nms.nbt.v1_15_R1;

import crypticlib.nms.nbt.AbstractNbtInt;
import net.minecraft.server.v1_15_R1.NBTTagInt;

public class V1_15_R1NbtInt extends AbstractNbtInt {

    public V1_15_R1NbtInt(int value) {
        super(value);
    }

    public V1_15_R1NbtInt(Object nmsNbtInt) {
        super(nmsNbtInt);
    }

    @Override
    public void fromNms(Object nmsNbt) {
        NBTTagInt nbtTagInt = (NBTTagInt) nmsNbt;
        setValue(nbtTagInt.asInt());
    }

    @Override
    public NBTTagInt toNms() {
        return NBTTagInt.a(value());
    }
}
