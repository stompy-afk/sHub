package lol.stompy.shub.scoreboard;

import lol.stompy.shub.profile.Profile;
import lol.stompy.shub.queue.Queue;
import lol.stompy.shub.util.assemble.AssembleAdapter;
import lombok.AllArgsConstructor;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.List;

@AllArgsConstructor
public class Scoreboard implements AssembleAdapter {

    private final ScoreboardHandler scoreboardHandler;

    @Override
    public String getTitle(Player player) {
        final String title = scoreboardHandler.getTitle().replace("<player>", player.getName());

        if (scoreboardHandler.isPapi())
            return PlaceholderAPI.setPlaceholders(player, title);

        return title;
    }

    @Override
    public List<String> getLines(Player player) {
        final Profile profile = scoreboardHandler.getShub().getProfileHandler().get(player.getUniqueId());

        if (profile.getQueue().isEmpty()) {
            final List<String> notQueue = scoreboardHandler.getNotQueue();
            notQueue.forEach(s -> s.replace("<player>", player.getName()));

            if (scoreboardHandler.isPapi())
                return PlaceholderAPI.setPlaceholders(player, notQueue);

            return notQueue;
        }

        final List<String> queue = scoreboardHandler.getQueue();
        final Queue playerQueue = profile.getQueue().get();

        queue.forEach(s -> s.replace("<player>", player.getName()).replace("<queue_name>", playerQueue.getName())
                .replace("<queue_server>", playerQueue.getServer()).replace("<queue_position>", String.valueOf(playerQueue.getPlacement(profile))));

        if (scoreboardHandler.isPapi())
            return PlaceholderAPI.setPlaceholders(player, queue);

        return queue;
    }

}
