package crypticlib.scheduler;

import crypticlib.lifecycle.AutoTask;
import crypticlib.lifecycle.BukkitLifeCycleTask;
import crypticlib.lifecycle.LifeCycle;
import crypticlib.lifecycle.TaskRule;
import crypticlib.scheduler.task.FoliaTaskWrapper;
import crypticlib.scheduler.task.ITaskWrapper;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Folia平台的调度器
 */
@AutoTask(
    rules = @TaskRule(lifeCycle = LifeCycle.INIT)
)
public enum FoliaScheduler implements IScheduler, BukkitLifeCycleTask {

    INSTANCE;

    private Plugin plugin;

    @Override
    public ITaskWrapper sync(@NotNull Runnable task) {
        return new FoliaTaskWrapper(Bukkit.getGlobalRegionScheduler().run(plugin, runnableToConsumer(task)));
    }

    @Override
    public ITaskWrapper async(@NotNull Runnable task) {
        return new FoliaTaskWrapper(Bukkit.getAsyncScheduler().runNow(plugin, runnableToConsumer(task)));
    }

    @Override
    public ITaskWrapper syncLater(@NotNull Runnable task, long delayTicks) {
        return new FoliaTaskWrapper(Bukkit.getGlobalRegionScheduler().runDelayed(plugin, runnableToConsumer(task), toSafeTick(delayTicks)));
    }

    @Override
    public ITaskWrapper asyncLater(@NotNull Runnable task, long delayTicks) {
        return new FoliaTaskWrapper(Bukkit.getAsyncScheduler().runDelayed(plugin, runnableToConsumer(task), toSafeTick(delayTicks) * 50, TimeUnit.MILLISECONDS));
    }

    @Override
    public ITaskWrapper syncTimer(@NotNull Runnable task, long delayTicks, long periodTicks) {
        return new FoliaTaskWrapper(Bukkit.getGlobalRegionScheduler().runAtFixedRate(plugin, runnableToConsumer(task), toSafeTick(delayTicks), toSafeTick(periodTicks)));
    }

    @Override
    public ITaskWrapper asyncTimer(@NotNull Runnable task, long delayTicks, long periodTicks) {
        return new FoliaTaskWrapper(Bukkit.getAsyncScheduler().runAtFixedRate(plugin, runnableToConsumer(task), toSafeTick(delayTicks) * 50, toSafeTick(periodTicks) * 50, TimeUnit.MILLISECONDS));
    }

    @Override
    public ITaskWrapper runOnEntity(Entity entity, Runnable task, Runnable retriedTask) {
        return new FoliaTaskWrapper(entity.getScheduler().run(plugin, runnableToConsumer(task), retriedTask));
    }


    @Override
    public ITaskWrapper runOnEntityLater(Entity entity, Runnable task, Runnable retriedTask, long delayTicks) {
        return new FoliaTaskWrapper(entity.getScheduler().runDelayed(plugin, runnableToConsumer(task), retriedTask, toSafeTick(delayTicks)));
    }

    @Override
    public ITaskWrapper runOnEntityTimer(Entity entity, Runnable task, Runnable retriedTask, long delayTicks, long periodTicks) {
        return new FoliaTaskWrapper(entity.getScheduler().runAtFixedRate(plugin, runnableToConsumer(task), retriedTask, toSafeTick(delayTicks), toSafeTick(periodTicks)));
    }

    @Override
    public ITaskWrapper runOnLocation(Location location, Runnable task) {
        return new FoliaTaskWrapper(Bukkit.getRegionScheduler().run(plugin, location, runnableToConsumer(task)));
    }

    @Override
    public ITaskWrapper runOnLocationLater(Location location, Runnable task, long delayTicks) {
        return new FoliaTaskWrapper(Bukkit.getRegionScheduler().runDelayed(plugin, location, runnableToConsumer(task), toSafeTick(delayTicks)));
    }

    @Override
    public ITaskWrapper runOnLocationTimer(Location location, Runnable task, long delayTicks, long periodTicks) {
        return new FoliaTaskWrapper(Bukkit.getRegionScheduler().runAtFixedRate(plugin, location, runnableToConsumer(task), toSafeTick(delayTicks), toSafeTick(periodTicks)));
    }

    @Override
    public void cancelTasks() {
        Bukkit.getGlobalRegionScheduler().cancelTasks(plugin);
        Bukkit.getAsyncScheduler().cancelTasks(plugin);
    }

    private Consumer<ScheduledTask> runnableToConsumer(Runnable runnable) {
        return (final ScheduledTask task) -> runnable.run();
    }

    private long toSafeTick(long originTick) {
        return originTick > 0 ? originTick : 1;
    }

    @Override
    public void run(Plugin plugin, LifeCycle lifeCycle) {
        this.plugin = plugin;
    }
    
}
