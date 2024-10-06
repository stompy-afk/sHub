package lol.stompy.shub.queue;

import lol.stompy.shub.profile.Profile;
import lombok.Getter;

import java.util.Comparator;
import java.util.PriorityQueue;

@Getter
public class Queue {

    private final PriorityQueue<Profile> profiles = new PriorityQueue<>(Comparator.comparingInt(o -> o.getQueuePriority().getPriority()));

    private final int size;
    private final String server, name;

    /**
     * sets the parameters for the queue
     *
     * @param name name of the queue
     * @param server name of the server
     * @param size size of the queue
     */

    public Queue(String name, String server, int size) {
        this.name = name;
        this.size = size;
        this.server = server;
    }

    /**
     * adds a profile to the queue
     *
     * @param profile profile to add
     */

    public final void add(Profile profile) {
        profiles.add(profile);
    }

    /**
     * removes a profile from the queue
     *
     * @param profile profile to remove
     */

    public final void remove(Profile profile) {
        profiles.remove(profile);
        profile.setQueue(null);
    }

    /**
     * Gets the placement of a profile in the queue
     *
     * @param profile profile to find the placement off
     * @return {@link Integer}
     */

    public final int getPlacement(Profile profile) {
        Profile[] profilesArray = profiles.toArray(new Profile[0]);

        for (int i = 0; i < profilesArray.length; i++) {
            if (profilesArray[i].equals(profile)) {
                return i;
            }
        }

        return 0;
    }

}
