package lol.stompy.shub.commands;

import lol.stompy.shub.profile.Profile;
import lol.stompy.shub.sHub;
import lol.stompy.shub.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class QueueCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        String[] args = s.split("\\s+");

        if (args[0].equalsIgnoreCase("queue"))
            return false;

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(CC.translate("&cYou need to be a player to do this command!"));
            return false;
        }

        final Player player = (Player) commandSender;
        final Profile profile = sHub.getInstance().getProfileHandler().get(player.getUniqueId());

        if (profile.getQueuePriority() == null) {
            player.sendMessage(CC.translate("&cPlease message your admin regarding your queue priority!"));
            return false;
        }

        if (profile.getQueue().isPresent()) {
            player.sendMessage(CC.translate("&cYou are already in a queue!"));
            return false;
        }

        if (args.length > 2) {
            player.sendMessage(CC.translate("&c/queue <queue>"));
            return false;
        }

        //
        return false;
    }
}
