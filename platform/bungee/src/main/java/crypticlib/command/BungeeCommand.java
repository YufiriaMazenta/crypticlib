package crypticlib.command;

import crypticlib.perm.PermInfo;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CrypticLib提供的插件命令类，用于注册插件命令
 */
public class BungeeCommand extends Command implements CommandHandler<CommandSender>, TabExecutor {

    protected final Map<String, AbstractSubCommand<CommandSender>> subcommands = new ConcurrentHashMap<>();
    protected CommandInfo commandInfo;
    protected Boolean registered = false;

    public BungeeCommand(CommandInfo commandInfo) {
        super(commandInfo.name(), commandInfo.permission() != null ? commandInfo.permission().permission() : null, commandInfo.aliases());
        this.commandInfo = commandInfo;
    }

    public BungeeCommand setRootCommandInfo(CommandInfo commandInfo) {
        this.commandInfo = commandInfo;
        return this;
    }

    @Override
    public final void onCommand(CommandSender sender, List<String> args) {
        CommandHandler.super.onCommand(sender, args);
    }

    @Override
    public final List<String> onTabComplete(CommandSender sender, List<String> args) {
        return CommandHandler.super.onTabComplete(sender, args);
    }

    @Override
    public BungeeCommand regSub(@NotNull AbstractSubCommand<CommandSender> subcommandHandler) {
        return (BungeeCommand) CommandHandler.super.regSub(subcommandHandler);
    }

    public CommandInfo rootCommandInfo() {
        return commandInfo;
    }

    @Override
    public @NotNull Map<String, AbstractSubCommand<CommandSender>> subcommands() {
        return subcommands;
    }
    
    public void register(@NotNull Plugin plugin) {
        if (registered)
            throw new UnsupportedOperationException("Cannot register a command repeatedly");
        registered = true;
        scanSubCommands();
        registerPerms();
        BungeeCommandManager.INSTANCE.register(plugin, commandInfo, this);
    }

    @Override
    public void registerPerms() {
        CommandHandler.super.registerPerms();
        PermInfo permission = commandInfo.permission();
        if (permission != null)
            permission.register();
    }

    @Override
    public final void execute(CommandSender sender, String[] args) {
        onCommand(sender, Arrays.asList(args));
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        return onTabComplete(sender, Arrays.asList(args));
    }

}
