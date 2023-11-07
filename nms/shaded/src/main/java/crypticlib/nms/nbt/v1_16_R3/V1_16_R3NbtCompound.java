package crypticlib.nms.nbt.v1_16_R3;

import crypticlib.nms.nbt.AbstractNbtCompound;
import net.minecraft.server.v1_16_R3.NBTBase;
import net.minecraft.server.v1_16_R3.NBTTagCompound;

import java.util.Map;

public class V1_16_R3NbtCompound extends AbstractNbtCompound {

    public V1_16_R3NbtCompound() {
        super(V1_16_R3NbtTranslator.INSTANCE);
    }

    public V1_16_R3NbtCompound(Object nmsNbtCompound) {
        super(nmsNbtCompound, V1_16_R3NbtTranslator.INSTANCE);
    }

    public V1_16_R3NbtCompound(Map<String, Object> nbtValueMap) {
        super(nbtValueMap, V1_16_R3NbtTranslator.INSTANCE);
    }

    @Override
    public void fromNms(Object nmsNbt) {
        NBTTagCompound nms = (NBTTagCompound) nmsNbt;
        for (String key : nms.getKeys()) {
            value().put(key, getNbtTranslator().fromNms(nms.get(key)));
        }
    }

    @Override
    public NBTTagCompound toNms() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        for (String key : value().keySet()) {
            nbtTagCompound.set(key, (NBTBase) get(key).toNms());
        }
        return nbtTagCompound;
    }

}
