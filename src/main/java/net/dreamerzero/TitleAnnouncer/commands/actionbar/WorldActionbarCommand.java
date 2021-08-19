package net.dreamerzero.TitleAnnouncer.commands.actionbar;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dreamerzero.TitleAnnouncer.Announcer;
import net.dreamerzero.TitleAnnouncer.utils.MiniMessageUtil;
import net.dreamerzero.TitleAnnouncer.utils.SoundUtil;
import net.kyori.adventure.audience.Audience;

public class WorldActionbarCommand implements CommandExecutor {
    private Announcer plugin;
	public WorldActionbarCommand(Announcer plugin) {
		this.plugin = plugin;
	}

    //Command
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            plugin.getLogger().info("The console cannot execute this command.");
            return false;
        }

        // Get the world in which the player is located.
        Player player = (Player) sender;
        Audience audience = player.getWorld();

        StringBuilder actionbartext = new StringBuilder();
        for (byte i = 0; i < args.length; i++) {
            actionbartext = actionbartext.append(" ");
            actionbartext = actionbartext.append(args[i]); 
        }
        //Convert StringBuilder to String, Component is not compatible :nimodo:
        String actionbartoparse = actionbartext.toString();
        //Send to all
        audience.sendActionBar(
            MiniMessageUtil.miniMessageParse(actionbartoparse));
        sender.sendMessage(
            MiniMessageUtil.miniMessageParse(
                plugin.getConfig().getString("messages.actionbar.successfully")));
        //if (true) { //Momentary change, I just don't want to lose the structure.
        SoundUtil.playSound(
            "entity.experience_orb.pickup", 
            audience, 
            10f, 
            2f);
        //}
        return true;
    }
}
