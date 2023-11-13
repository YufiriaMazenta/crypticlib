package crypticlib.nms.nbt.v1_13_R2;

import crypticlib.nms.nbt.NbtTagCompound;
import net.minecraft.server.v1_13_R2.NBTBase;
import net.minecraft.server.v1_13_R2.NBTTagCompound;

import java.util.Map;

public class V1_13_R2NbtTagCompound extends NbtTagCompound {

    public V1_13_R2NbtTagCompound() {
        super(V1_13_R2NbtTranslator.INSTANCE);
    }

    public V1_13_R2NbtTagCompound(Object nmsNbtCompound) {
        super(nmsNbtCompound, V1_13_R2NbtTranslator.INSTANCE);
    }

    public V1_13_R2NbtTagCompound(Map<String, Object> nbtValueMap) {
        super(nbtValueMap, V1_13_R2NbtTranslator.INSTANCE);
    }

    @Override
    public void fromNms(Object nmsNbt) {
        NBTTagCompound nms = (NBTTagCompound) nmsNbt;
        for (String key : nms.getKeys()) {
            value().put(key, nbtTranslator().translateNmsNbt(nms.get(key)));
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
