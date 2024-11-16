package net.mcbrawls.railroad.mixin;

import com.mojang.authlib.GameProfile;
import net.mcbrawls.railroad.player.RailroadPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity implements RailroadPlayer {
	private ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
		super(world, pos, yaw, gameProfile);
	}
}
