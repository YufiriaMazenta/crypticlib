package crypticlib.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * CrypticLib提供的插件基础命令接口
 */
public interface IPluginCmdExecutor extends TabExecutor, ICmdExecutor {

    Plugin getPlugin();

    @Override
    default List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return ICmdExecutor.super.onTabComplete(sender, Arrays.asList(args));
    }

    @Override
    default boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) {
            return false;
        }
        List<String> argList = new ArrayList<>(Arrays.asList(args));
        ISubCmdExecutor subCommand = subCommands().get(argList.get(0));
        if (subCommand != null) {
            String perm = subCommand.permission();
            if (perm == null || sender.hasPermission(perm)) {
                return subCommand.onCommand(sender, argList.subList(1, argList.size()));
            }
        }
        return false;
    }

}
