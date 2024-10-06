package lol.stompy.shub;

import lol.stompy.shub.profile.ProfileHandler;
import lol.stompy.shub.queue.Queue;
import lol.stompy.shub.queue.QueueHandler;
import lol.stompy.shub.util.sFile;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class sHub extends JavaPlugin {

    @Getter
    private static sHub instance;

    private QueueHandler queueHandler;
    private ProfileHandler profileHandler;

    private sFile queueFile;

    /**
     * loading and initializing instance
     */

    @Override
    public void onLoad() {
        instance = this;
        this.saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        this.queueFile = new sFile(this.getDataFolder(), "queue.yml");
        this.queueHandler = new QueueHandler(this);

        this.profileHandler = new ProfileHandler(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
