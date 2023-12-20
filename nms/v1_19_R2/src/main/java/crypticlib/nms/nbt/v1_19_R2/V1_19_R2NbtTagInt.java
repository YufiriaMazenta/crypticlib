package crypticlib.nms.nbt.v1_19_R2;

import crypticlib.nms.nbt.NbtTagInt;
import net.minecraft.nbt.NBTTagInt;
import org.jetbrains.annotations.NotNull;

public class V1_19_R2NbtTagInt extends NbtTagInt {

    public V1_19_R2NbtTagInt(int value) {
        super(value);
    }

    public V1_19_R2NbtTagInt(Object nmsNbtInt) {
        super(nmsNbtInt);
    }

    @Override
    public void fromNms(@NotNull Object nmsNbt) {
        NBTTagInt nbtTagInt = (NBTTagInt) nmsNbt;
        setValue(nbtTagInt.g());
    }

    @Override
    public @NotNull NBTTagInt toNms() {
        return NBTTagInt.a(value);
    }
}
