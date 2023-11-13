package crypticlib.nms.entity;

import crypticlib.CrypticLib;
import crypticlib.nms.entity.v1_13_R1.V1_13_R1ProxyEntity;
import crypticlib.nms.entity.v1_13_R2.V1_13_R2ProxyEntity;
import crypticlib.nms.entity.v1_14_R1.V1_14_R1ProxyEntity;
import crypticlib.nms.entity.v1_15_R1.V1_15_R1ProxyEntity;
import crypticlib.nms.entity.v1_16_R1.V1_16_R1ProxyEntity;
import crypticlib.nms.entity.v1_16_R2.V1_16_R2ProxyEntity;
import crypticlib.nms.entity.v1_16_R3.V1_16_R3ProxyEntity;
import crypticlib.nms.entity.v1_17_R1.V1_17_R1ProxyEntity;
import crypticlib.nms.entity.v1_18_R1.V1_18_R1ProxyEntity;
import crypticlib.nms.entity.v1_18_R2.V1_18_R2ProxyEntity;
import crypticlib.nms.entity.v1_19_R1.V1_19_R1ProxyEntity;
import crypticlib.nms.entity.v1_19_R2.V1_19_R2ProxyEntity;
import crypticlib.nms.entity.v1_19_R3.V1_19_R3ProxyEntity;
import crypticlib.nms.entity.v1_20_R1.V1_20_R1ProxyEntity;
import crypticlib.nms.entity.v1_20_R2.V1_20_R2ProxyEntity;
import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EntityProxy {

    private static final Map<String, Function<Entity, ProxyEntity>> entityProxyMap;

    static {
        entityProxyMap = new HashMap<>();
        entityProxyMap.put("v1_13_R1", V1_13_R1ProxyEntity::new);
        entityProxyMap.put("v1_13_R2", V1_13_R2ProxyEntity::new);
        entityProxyMap.put("v1_14_R1", V1_14_R1ProxyEntity::new);
        entityProxyMap.put("v1_15_R1", V1_15_R1ProxyEntity::new);
        entityProxyMap.put("v1_16_R1", V1_16_R1ProxyEntity::new);
        entityProxyMap.put("v1_16_R2", V1_16_R2ProxyEntity::new);
        entityProxyMap.put("v1_16_R3", V1_16_R3ProxyEntity::new);
        entityProxyMap.put("v1_17_R1", V1_17_R1ProxyEntity::new);
        entityProxyMap.put("v1_18_R1", V1_18_R1ProxyEntity::new);
        entityProxyMap.put("v1_18_R2", V1_18_R2ProxyEntity::new);
        entityProxyMap.put("v1_19_R1", V1_19_R1ProxyEntity::new);
        entityProxyMap.put("v1_19_R2", V1_19_R2ProxyEntity::new);
        entityProxyMap.put("v1_19_R3", V1_19_R3ProxyEntity::new);
        entityProxyMap.put("v1_20_R1", V1_20_R1ProxyEntity::new);
        entityProxyMap.put("v1_20_R2", V1_20_R2ProxyEntity::new);
    }

    public static ProxyEntity proxy(Entity entity) {
        return entityProxyMap.getOrDefault(CrypticLib.nmsVersion(), i -> {
            throw new RuntimeException("Unsupported version: " + CrypticLib.nmsVersion());
        }).apply(entity);
    }

}
