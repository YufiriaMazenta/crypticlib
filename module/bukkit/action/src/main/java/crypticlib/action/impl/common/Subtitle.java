package crypticlib.action.impl.common;

import crypticlib.action.BaseAction;
import crypticlib.chat.BukkitMsgSender;
import crypticlib.util.StringHelper;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Function;

public class Subtitle extends BaseAction {

    public String message;

    public Subtitle(String message) {
        this.message = Objects.requireNonNull(message);
    }

    @Override
    public String toActionStr() {
        return "subtitle " + message;
    }

    @Override
    public void run(Player player, @NotNull Plugin plugin, @Nullable Function<String, String> argPreprocessor) {
        if (argPreprocessor != null) {
            message = argPreprocessor.apply(toActionStr());
        }
        BukkitMsgSender.INSTANCE.sendTitle(player, "", message);
        runNext(player, plugin, argPreprocessor);
    }

}
