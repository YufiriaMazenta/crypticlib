package crypticlib.nms.nbt.v1_14_R1;

import crypticlib.nms.nbt.AbstractNbtShort;
import net.minecraft.server.v1_14_R1.NBTTagShort;

public class V1_14_R1NbtShort extends AbstractNbtShort {

    public V1_14_R1NbtShort(short value) {
        super(value);
    }

    public V1_14_R1NbtShort(Object nmsNbtShort) {
        super(nmsNbtShort);
    }

    @Override
    public void fromNms(Object nmsNbt) {
        NBTTagShort nbtTagShort = (NBTTagShort) nmsNbt;
        setValue(nbtTagShort.asShort());
    }

    @Override
    public NBTTagShort toNms() {
        return new NBTTagShort(value());
    }
}
