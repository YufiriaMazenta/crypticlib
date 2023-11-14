package crypticlib.nms.entity.v1_18_R2;

import crypticlib.nms.entity.ProxyEntity;
import crypticlib.nms.nbt.v1_18_R2.V1_18_R2NbtTagCompound;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftEntity;
import org.bukkit.entity.Entity;

public class V1_18_R2ProxyEntity extends ProxyEntity {

    public V1_18_R2ProxyEntity(Entity entity) {
        super(entity);
    }

    @Override
    public ProxyEntity saveNbtToEntity() {
        net.minecraft.world.entity.Entity nmsEntity = ((CraftEntity) bukkitEntity()).getHandle();
        nmsEntity.g((NBTTagCompound) nbtTagCompound().toNms());
        return this;
    }

    @Override
    public void fromNms() {
        net.minecraft.world.entity.Entity nmsEntity = ((CraftEntity) bukkitEntity()).getHandle();
        NBTTagCompound nmsNbt = new NBTTagCompound();
        this.setNbtTagCompound(new V1_18_R2NbtTagCompound(nmsEntity.f(nmsNbt)));
    }

}
