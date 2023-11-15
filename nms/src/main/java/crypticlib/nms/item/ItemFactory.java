package crypticlib.nms.item;

import crypticlib.CrypticLib;
import crypticlib.function.TernaryFunction;
import crypticlib.nms.item.v1_12_R1.V1_12_R1Item;
import crypticlib.nms.item.v1_13_R1.V1_13_R1Item;
import crypticlib.nms.item.v1_13_R2.V1_13_R2Item;
import crypticlib.nms.item.v1_14_R1.V1_14_R1Item;
import crypticlib.nms.item.v1_15_R1.V1_15_R1Item;
import crypticlib.nms.item.v1_16_R1.V1_16_R1Item;
import crypticlib.nms.item.v1_16_R2.V1_16_R2Item;
import crypticlib.nms.item.v1_16_R3.V1_16_R3Item;
import crypticlib.nms.item.v1_17_R1.V1_17_R1Item;
import crypticlib.nms.item.v1_18_R1.V1_18_R1Item;
import crypticlib.nms.item.v1_18_R2.V1_18_R2Item;
import crypticlib.nms.item.v1_19_R1.V1_19_R1Item;
import crypticlib.nms.item.v1_19_R2.V1_19_R2Item;
import crypticlib.nms.item.v1_19_R3.V1_19_R3Item;
import crypticlib.nms.item.v1_20_R1.V1_20_R1Item;
import crypticlib.nms.item.v1_20_R2.V1_20_R2Item;
import crypticlib.nms.nbt.NbtFactory;
import crypticlib.nms.nbt.NbtTagCompound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * CrypticLib的物品提供工厂
 */
public class ItemFactory {

    private static final Map<String, Function<ItemStack, Item>> itemProviderMap1;
    private static final Map<String, BiFunction<String, NbtTagCompound, Item>> itemProviderMap2;
    private static final Map<String, TernaryFunction<String, NbtTagCompound, Integer, Item>> itemProviderMap3;

    static {
        itemProviderMap1 = new ConcurrentHashMap<>();
        itemProviderMap2 = new ConcurrentHashMap<>();
        itemProviderMap3 = new ConcurrentHashMap<>();

        regItemProvider("v1_12_R1", V1_12_R1Item::new, V1_12_R1Item::new, V1_12_R1Item::new);
        regItemProvider("v1_13_R1", V1_13_R1Item::new, V1_13_R1Item::new, V1_13_R1Item::new);
        regItemProvider("v1_13_R2", V1_13_R2Item::new, V1_13_R2Item::new, V1_13_R2Item::new);
        regItemProvider("v1_14_R1", V1_14_R1Item::new, V1_14_R1Item::new, V1_14_R1Item::new);
        regItemProvider("v1_15_R1", V1_15_R1Item::new, V1_15_R1Item::new, V1_15_R1Item::new);
        regItemProvider("v1_16_R1", V1_16_R1Item::new, V1_16_R1Item::new, V1_16_R1Item::new);
        regItemProvider("v1_16_R2", V1_16_R2Item::new, V1_16_R2Item::new, V1_16_R2Item::new);
        regItemProvider("v1_16_R3", V1_16_R3Item::new, V1_16_R3Item::new, V1_16_R3Item::new);
        regItemProvider("v1_17_R1", V1_17_R1Item::new, V1_17_R1Item::new, V1_17_R1Item::new);
        regItemProvider("v1_18_R1", V1_18_R1Item::new, V1_18_R1Item::new, V1_18_R1Item::new);
        regItemProvider("v1_18_R2", V1_18_R2Item::new, V1_18_R2Item::new, V1_18_R2Item::new);
        regItemProvider("v1_19_R1", V1_19_R1Item::new, V1_19_R1Item::new, V1_19_R1Item::new);
        regItemProvider("v1_19_R2", V1_19_R2Item::new, V1_19_R2Item::new, V1_19_R2Item::new);
        regItemProvider("v1_19_R3", V1_19_R3Item::new, V1_19_R3Item::new, V1_19_R3Item::new);
        regItemProvider("v1_20_R1", V1_20_R1Item::new, V1_20_R1Item::new, V1_20_R1Item::new);
        regItemProvider("v1_20_R2", V1_20_R2Item::new, V1_20_R2Item::new, V1_20_R2Item::new);
    }

    public static Item item(ItemStack itemStack) {
        return itemProviderMap1.getOrDefault(CrypticLib.nmsVersion(), i -> {
            throw new RuntimeException("Unsupported version: " + CrypticLib.nmsVersion());
        }).apply(itemStack);
    }

    public static Item item(String material) {
        return item(material, NbtFactory.emptyNbtCompound());
    }

    public static Item item(String material, Integer amount) {
        return item(material, NbtFactory.emptyNbtCompound(), amount);
    }

    public static Item item(String material, NbtTagCompound nbtTagCompound) {
        return itemProviderMap2.getOrDefault(CrypticLib.nmsVersion(), (material_, nbtCompound_) -> {
            throw new RuntimeException("Unsupported version: " + CrypticLib.nmsVersion());
        }).apply(material, nbtTagCompound);
    }

    public static Item item(String material, NbtTagCompound nbtTagCompound, Integer amount) {
        return itemProviderMap3.getOrDefault(CrypticLib.nmsVersion(), (material_, nbtTagCompound_, amount_) -> {
            throw new RuntimeException("Unsupported version: " + CrypticLib.nmsVersion());
        }).apply(material, nbtTagCompound, amount);
    }

    public static Item item(ConfigurationSection config) {
        String material = config.getString("material");
        NbtTagCompound nbtCompound = NbtFactory.emptyNbtCompound();
        int amount = config.getInt("amount", 1);
        if (config.isConfigurationSection("nbt")) {
            nbtCompound = NbtFactory.config2NbtTagCompound(config.getConfigurationSection("nbt"));
        }
        return item(material, nbtCompound, amount);
    }

    public static void regItemProvider(String nmsVersion, Function<ItemStack, Item> provider1, BiFunction<String, NbtTagCompound, Item> provider2, TernaryFunction<String, NbtTagCompound, Integer, Item> provider3) {
        itemProviderMap1.put(nmsVersion, provider1);
        itemProviderMap2.put(nmsVersion, provider2);
        itemProviderMap3.put(nmsVersion, provider3);
    }

}
