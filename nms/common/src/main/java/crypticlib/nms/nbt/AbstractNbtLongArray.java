package crypticlib.nms.nbt;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

import java.util.StringJoiner;

public abstract class AbstractNbtLongArray implements INbtTag<long[]>, IFormatNbtNumber {

    private long[] value;

    public AbstractNbtLongArray(long[] value) {
        this.value = value;
    }

    public AbstractNbtLongArray(Object nmsNbtLongArray) {
        fromNms(nmsNbtLongArray);
    }

    @Override
    public NbtType type() {
        return NbtType.LONG_ARRAY;
    }

    @Override
    public long[] value() {
        return this.value;
    }

    @Override
    public void setValue(long[] value) {
        this.value = value;
    }

    @Override
    public JsonArray toJson() {
        JsonArray jsonArray = new JsonArray();
        for (long l: value) {
            jsonArray.add(new JsonPrimitive(l));
        }
        return jsonArray;
    }

    @Override
    public String formatString() {
        StringJoiner str = new StringJoiner(",", "LONG$[", "]");
        for (long l : value) {
            str.add(l + "");
        }
        return str.toString();
    }

}
