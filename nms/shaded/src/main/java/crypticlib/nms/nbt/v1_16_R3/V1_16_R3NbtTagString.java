package crypticlib.nms.nbt.v1_16_R3;

import crypticlib.nms.nbt.NbtTagString;
import net.minecraft.server.v1_16_R3.NBTBase;
import net.minecraft.server.v1_16_R3.NBTTagString;
import org.jetbrains.annotations.NotNull;

public class V1_16_R3NbtTagString extends NbtTagString {

    public V1_16_R3NbtTagString(String value) {
        super(value);
    }

    public V1_16_R3NbtTagString(Object nmsNbtString) {
        super(nmsNbtString);
    }

    @Override
    public void fromNms(@NotNull Object nmsNbt) {
        NBTTagString nbtTagString = (NBTTagString) nmsNbt;
        setValue(nbtTagString.asString());
    }

    @Override
    public @NotNull NBTBase toNms() {
        return NBTTagString.a(value);
    }

}
