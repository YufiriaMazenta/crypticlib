package crypticlib.config.node.impl.velocity;

import com.electronwill.nightconfig.core.Config;
import crypticlib.config.node.VelocityConfigNode;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListConfig<T> extends VelocityConfigNode<List<T>> {

    public ListConfig(@NotNull String key, @NotNull List<T> def) {
        super(key, def);
    }

    @Override
    public void load(@NotNull Config config) {
        setValue(config.getOrElse(key, def));
    }

}
