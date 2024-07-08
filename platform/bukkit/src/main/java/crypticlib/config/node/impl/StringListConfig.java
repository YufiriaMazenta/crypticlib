package crypticlib.config.node.impl;

import crypticlib.config.node.BukkitConfigNode;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StringListConfig extends BukkitConfigNode<List<String>> {

    public StringListConfig(@NotNull String key, @NotNull List<String> def) {
        super(key, def);
    }

    public StringListConfig(@NotNull String key, @NotNull List<String> def, @NotNull List<String> defComments) {
        super(key, def, defComments);
    }

    @Override
    public void load(@NotNull ConfigurationSection config) {
        saveDef(config);
        setValue(config.getStringList(key));
        setComments(getCommentsFromConfig());
    }

}
