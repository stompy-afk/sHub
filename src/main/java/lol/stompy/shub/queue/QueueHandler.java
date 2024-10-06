package lol.stompy.shub.queue;

import lol.stompy.shub.profile.Profile;
import lol.stompy.shub.sHub;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class QueueHandler {

    private final List<Queue> queues;
    private final List<QueuePriority> queuePriorities;

    private final sHub shub;

    /**
     * queue handler: handles all queue related methods
     *
     * @param shub instance of main class
     */

    public QueueHandler(sHub shub) {
        this.queues = new ArrayList<>();
        this.queuePriorities = new LinkedList<>();

        this.shub = shub;
        this.load(true);
    }

    /**
     * loads all queues and queue priorities
     *
     * @param async to do the task async or not
     */

    private void load(boolean async) {
        if (async) {
            shub.getServer().getScheduler().runTaskAsynchronously(shub, () -> load(false));
            return;
        }

        final FileConfiguration fileConfiguration = shub.getQueueFile().getConfig();

        fileConfiguration.getConfigurationSection("queues").getKeys(false).forEach(s -> {
            queues.add(new Queue(s,
                    fileConfiguration.getString("queues." + s + ".server"),
                    fileConfiguration.getInt("queues." + s + ".size")));
        });

        fileConfiguration.getConfigurationSection("queue-priority").getKeys(false).forEach(s -> {
            queuePriorities.add(new QueuePriority(fileConfiguration.getString("queue-priority." + s + ".permission"),
                    fileConfiguration.getInt("queue-priority." + s + ".priority")));
        });
    }

    /**
     * finds the queue priority of a player
     *
     * @param player player to find the queue priority off
     * @return {@link Optional<QueuePriority>}
     */

    public final Optional<QueuePriority> findQueuePriority(Player player) {
        return queuePriorities.stream().filter(queuePriority1 -> player.hasPermission(queuePriority1.getPermission())).findFirst();
    }

    /**
     * finds a queue with a specific name
     *
     * @param name of queue
     * @return {@link Optional<Queue>}
     */

    public final Optional<Queue> getQueue(String name) {
        return queues.stream().filter(queue -> queue.getName().equalsIgnoreCase(name)).findFirst();
    }


}
