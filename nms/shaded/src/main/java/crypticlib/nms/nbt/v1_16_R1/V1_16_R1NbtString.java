package crypticlib.nms.nbt.v1_16_R1;

import crypticlib.nms.nbt.AbstractNbtString;
import net.minecraft.server.v1_16_R1.NBTBase;
import net.minecraft.server.v1_16_R1.NBTTagString;

public class V1_16_R1NbtString extends AbstractNbtString {

    public V1_16_R1NbtString(String value) {
        super(value);
    }

    public V1_16_R1NbtString(Object nmsNbtString) {
        super(nmsNbtString);
    }

    @Override
    public void fromNms(Object nmsNbt) {
        NBTTagString nbtTagString = (NBTTagString) nmsNbt;
        setValue(nbtTagString.asString());
    }

    @Override
    public NBTBase toNms() {
        return NBTTagString.a(value());
    }

}
