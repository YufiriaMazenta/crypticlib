package crypticlib.config.node.impl.velocity;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.Config;
import crypticlib.config.node.VelocityConfigNode;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BooleanListConfig extends VelocityConfigNode<List<Boolean>> {

    public BooleanListConfig(@NotNull String key, @NotNull List<Boolean> def) {
        super(key, def);
    }

    public BooleanListConfig(@NotNull String key, @NotNull List<Boolean> def, @NotNull String comment) {
        super(key, def, comment);
    }

    @Override
    public void load(@NotNull CommentedConfig config) {
        setValue(config.getOrElse(key, def));
        setComment(config.getComment(key));
    }

}
