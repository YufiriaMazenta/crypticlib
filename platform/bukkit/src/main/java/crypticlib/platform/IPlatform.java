package crypticlib.platform;

import crypticlib.scheduler.IScheduler;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Future;

/**
 * 平台接口
 */
public interface IPlatform {

    /**
     * 获取平台的类型
     *
     * @return 平台的类型
     */
    @NotNull
    IPlatform.PlatformType type();

    /**
     * 获取平台对应的调度器
     *
     * @return 平台对应的调度器
     */
    @NotNull
    IScheduler scheduler();

    /**
     * 将实体传送至一个坐标
     *
     * @param entity   被传送的实体
     * @param location 传送的目标点
     * @param cause    传送的原因
     */
    Future<Boolean> teleportEntity(@NotNull Entity entity, @NotNull Location location, @NotNull PlayerTeleportEvent.TeleportCause cause);

    /**
     * 将实体传送至一个坐标
     *
     * @param entity   被传送的玩家
     * @param location 传送的目标点
     */
    Future<Boolean> teleportEntity(@NotNull Entity entity, @NotNull Location location);

    boolean isBukkit();

    boolean isPaper();

    boolean isFolia();

    /**
     * 平台的类型
     */
    enum PlatformType {
        BUKKIT, PAPER, FOLIA;
    }

}
