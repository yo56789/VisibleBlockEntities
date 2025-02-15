package io.github.yo56789.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BlockEntityRenderer.class)
public interface BlockEntity<T extends net.minecraft.world.level.block.entity.BlockEntity> {
    /**
     * @author yo56789
     * @reason sets view distance to number of chunks in render distance
     */
    @Overwrite
    default int getViewDistance() {
        return Minecraft.getInstance().options.renderDistance().get() * 16;
    }

    /**
     * @author yo56789
     * @reason makes it only factor x and z into calculation
     */
    @Overwrite
    default boolean shouldRender(T blockEntity, Vec3 cameraPos) {
        return Vec3.atCenterOf(blockEntity.getBlockPos()).closerThan(new Vec3(cameraPos.x(), blockEntity.getBlockPos().getY(), cameraPos.z()), (double) this.getViewDistance());
    }
}
