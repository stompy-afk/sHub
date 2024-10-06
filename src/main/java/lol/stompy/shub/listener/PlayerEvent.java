package lol.stompy.shub.listener;

import io.papermc.paper.event.block.BlockBreakBlockEvent;
import lol.stompy.shub.sHub;
import lol.stompy.shub.util.CC;
import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.List;

public class PlayerEvent implements Listener {

    private final List<String> welcomeMessage = sHub.getInstance().getConfig().getStringList("welcome-message");
    private final boolean doubleJump = sHub.getInstance().getConfig().getBoolean("double-jump");

    @EventHandler
    public final void onPlayerJoinEvent(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if (doubleJump) {
            player.setAllowFlight(true);
            player.setFlying(false);
        }

        welcomeMessage.forEach(s -> player.sendMessage(CC.translate(s.replace("<player>", player.getName()))));
    }

    @EventHandler
    public final void onPlayerFlightEvent(PlayerToggleFlightEvent event) {
        if (!doubleJump)
            return;

        final Player player = event.getPlayer();

        if (player.hasPermission("shub.fly") || player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR) || player.isFlying()) {
            return;
        }

        event.setCancelled(true);

        player.setAllowFlight(false);
        player.setFlying(false);

        player.spawnParticle(Particle.ASH, player.getLocation(), 1, 1);
        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);

        player.setVelocity(player.getLocation().toVector().multiply(1.5).setY(1));
        player.setAllowFlight(true);
    }

    @EventHandler
    public final void onPlayerBreakBlock(BlockBreakEvent event) {
        if (!event.getPlayer().hasPermission("shub.build"))
            event.setCancelled(true);
    }

    @EventHandler
    public final void onBlockBuilderEvent(BlockCanBuildEvent event) {
        if (!event.getPlayer().hasPermission("shub.build"))
            event.setBuildable(false);
    }

    @EventHandler
    public final void onPlayerDamageEvent(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public final void onEntitySpawnEvent(EntitySpawnEvent event) {
        event.setCancelled(true);
    }

}
