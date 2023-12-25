package crypticlib.config.entry;

import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DoubleListConfigEntry extends ConfigEntry<List<Double>> {

    public DoubleListConfigEntry(@NotNull String key, @NotNull List<Double> def) {
        super(key, def);
    }

    @Override
    public void load(@NotNull ConfigurationSection config) {
        saveDef(config);
        setValue(config.getDoubleList(key));
    }

}
