package crypticlib.nms.nbt.v1_16_R2;

import crypticlib.nms.nbt.NbtTagDouble;
import net.minecraft.server.v1_16_R2.NBTTagDouble;
import org.jetbrains.annotations.NotNull;

public class V1_16_R2NbtTagDouble extends NbtTagDouble {

    public V1_16_R2NbtTagDouble(double value) {
        super(value);
    }

    public V1_16_R2NbtTagDouble(Object nmsNbtDouble) {
        super(nmsNbtDouble);
    }

    @Override
    public void fromNms(@NotNull Object nmsNbt) {
        NBTTagDouble nbtTagDouble = (NBTTagDouble) nmsNbt;
        setValue(nbtTagDouble.asDouble());
    }

    @Override
    public @NotNull NBTTagDouble toNms() {
        return NBTTagDouble.a(value);
    }

}
