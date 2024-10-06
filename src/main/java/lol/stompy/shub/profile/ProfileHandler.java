package lol.stompy.shub.profile;

import lol.stompy.shub.sHub;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileHandler {

    private final Map<UUID, Profile> profileMap;

    @Getter
    private final sHub shub;

    /**
     * profile handler: handles all profile related methods
     *
     * @param shub instance of main class
     */

    public ProfileHandler(sHub shub) {
        this.profileMap = new HashMap<>();
        this.shub = shub;

        shub.getServer().getPluginManager().registerEvents(new ProfileListener(this), shub);
    }

    /**
     * gets a profile if available in the map
     * and creates it if not available
     *
     * @param uuid uuid of player
     * @return {@link Profile}
     */

    public final Profile get(UUID uuid) {
        if (!profileMap.containsKey(uuid))
            return profileMap.put(uuid, new Profile(uuid));

        return profileMap.get(uuid);
    }

    /**
     * removes a profile
     *
     * @param uuid
     */

    public final void remove(UUID uuid) {
        if (!profileMap.containsKey(uuid))
            return;

        final Profile profile = profileMap.get(uuid);
        profile.suspend();

        profileMap.remove(uuid);
    }


}
