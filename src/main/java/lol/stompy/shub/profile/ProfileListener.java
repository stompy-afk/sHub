package lol.stompy.shub.profile;

import lol.stompy.shub.queue.QueuePriority;
import lol.stompy.shub.util.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Optional;

public class ProfileListener implements Listener {

    private final ProfileHandler profileHandler;

    /**
     * profile listener: handles all events for profile registration
     *
     * @param profileHandler instance of {@link ProfileHandler}
     */

    public ProfileListener(ProfileHandler profileHandler) {
        this.profileHandler = profileHandler;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public final void onPlayerJoinEvent(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final Profile profile = profileHandler.get(player.getUniqueId());

        final Optional<QueuePriority> queuePriority = profileHandler.getShub().getQueueHandler().findQueuePriority(player);

        if (queuePriority.isEmpty()) {
            profile.setQueuePriority(null);
            player.sendMessage(CC.translate("&cYou do not have a queue priority! Please contact the administrator to join queues!"));
            return;
        }

        profile.setQueuePriority(queuePriority.get());
    }

    @EventHandler
    public final void onPlayerQuitEvent(PlayerQuitEvent event) {
        profileHandler.remove(event.getPlayer().getUniqueId());
    }

}
