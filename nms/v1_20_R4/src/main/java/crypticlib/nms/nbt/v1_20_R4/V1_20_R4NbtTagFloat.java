package crypticlib.nms.nbt.v1_20_R4;

import crypticlib.nms.nbt.NbtTagFloat;
import net.minecraft.nbt.NBTTagFloat;
import org.jetbrains.annotations.NotNull;

public class V1_20_R4NbtTagFloat extends NbtTagFloat {

    public V1_20_R4NbtTagFloat(float value) {
        super(value);
    }

    public V1_20_R4NbtTagFloat(Object nmsNbtFloat) {
        super(nmsNbtFloat);
    }

    @Override
    public void fromNms(@NotNull Object nmsNbt) {
        NBTTagFloat nbtTagFloat = (NBTTagFloat) nmsNbt;
        setValue(nbtTagFloat.k());
    }

    @Override
    public @NotNull NBTTagFloat toNms() {
        return NBTTagFloat.a(value);
    }

}
