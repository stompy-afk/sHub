package lol.stompy.shub.profile;

import lol.stompy.shub.queue.Queue;
import lol.stompy.shub.queue.QueuePriority;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
public class Profile {

    private final UUID uuid;

    private Queue queue;
    private QueuePriority queuePriority;

    /**
     * creates a profile object
     * based of a player
     *
     * @param uuid uuid of player
     */

    public Profile(UUID uuid) {
        this.uuid = uuid;
        this.queue = null;
    }

    /**
     * gets the queue of the profile
     *
     * @return {@link Optional<Queue>}
     */

    public final Optional<Queue> getQueue() {
        return Optional.ofNullable(queue);
    }


    /**
     * suspends a profile
     */

    public final void suspend() {
        if (queue != null)
            queue.remove(this);
    }

}
