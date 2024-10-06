package lol.stompy.shub.scoreboard;

import lol.stompy.shub.sHub;
import lol.stompy.shub.util.CC;
import lol.stompy.shub.util.assemble.Assemble;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

@Getter
public class ScoreboardHandler {

    private final sHub shub;

    private final String title;
    private final boolean papi;
    private final List<String> queue, notQueue;

    public ScoreboardHandler(sHub shub) {
        this.shub = shub;

        final FileConfiguration fileConfiguration = shub.getConfig();

        this.title = CC.translate(fileConfiguration.getString("scoreboard.title"));
        this.queue = CC.translate(fileConfiguration.getStringList("scoreboard.queue"));
        this.notQueue = CC.translate(fileConfiguration.getStringList("scoreboard.notqueue"));

        this.papi = fileConfiguration.getBoolean("papi");
        new Assemble(shub, new Scoreboard(this));
    }

}
