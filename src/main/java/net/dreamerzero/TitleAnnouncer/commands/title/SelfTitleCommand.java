package net.dreamerzero.titleannouncer.commands.title;

import static net.dreamerzero.titleannouncer.utils.PlaceholderUtil.replacePlaceholders;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import net.dreamerzero.titleannouncer.Announcer;
import net.dreamerzero.titleannouncer.utils.MiniMessageUtil;
import net.dreamerzero.titleannouncer.utils.PlaceholderUtil;
import net.dreamerzero.titleannouncer.utils.SoundUtil;
import net.dreamerzero.titleannouncer.utils.TitleUtil;
import net.kyori.adventure.text.Component;

/*
This command will be executed as a test of the "/anunciarevento" command.
It will only be sent for the same player.
*/
public class SelfTitleCommand implements CommandExecutor {
    private final Announcer plugin;
    public SelfTitleCommand(Announcer plugin) {
        this.plugin = plugin;
    }

    // Command
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // It will send an title to the one who executes the command,
        // it makes no sense for the console to execute it.
        if (!(sender instanceof Player player)) {
            plugin.getLogger().info("The console cannot execute this command.");
            return false;
        }

        var enabledPrefix = plugin.getConfig().getBoolean("messages.prefix.enabled", true);
        Component prefix = Component.text("");

        if (enabledPrefix) {
            prefix = MiniMessageUtil.parse(plugin.getConfig().getString(
                "messages.prefix.line",
                "<gray>[</gray><gradient:yellow:blue>TitleAnnouncer</gradient><gray>]</gray> "));
        }

        // Permission Check
        if (!sender.hasPermission("announcer.title.test")) {
            sender.sendMessage(
                prefix.append(MiniMessageUtil.parse(
                    plugin.getConfig().getString(
                        "messages.title.no-permission",
                        "<red>You do not have permission to execute this command</red>"))));
            return true;
        }

        // The command requires arguments to work
        switch (args.length) {
            case 0 -> {
                sender.sendMessage(
                prefix.append(MiniMessageUtil.parse(
                    plugin.getConfig().getString(
                        "messages.title.without-argument",
                        "<red>You need to enter the title and subtitle arguments.</red>"))));
                return true;
            }
            case 1 -> {
                sender.sendMessage(
                prefix.append(MiniMessageUtil.parse(
                    plugin.getConfig().getString(
                        "messages.title.single-argument",
                        "<gray>You need to enter the title, the subtitle and the separator ';' in orden to send the title.</gray>"))));
                return true;
            }
        }

        // Concatenate the arguments provided by the command sent.
        var titleandsubtitle = new StringBuilder();
        for (byte i = 0; i < args.length; i++) {
            titleandsubtitle = titleandsubtitle.append(" ");
            titleandsubtitle = titleandsubtitle.append(args[i]);
        }

        String soundToPlay = plugin.getConfig().getString("sounds.title.sound-id", "entity.experience_orb.pickup");
        boolean soundEnabled = plugin.getConfig().getBoolean("sounds.title.enabled", true);
        float volume = plugin.getConfig().getInt("sounds.title.volume", 10);
        float pitch = plugin.getConfig().getInt("sounds.title.pitch", 2);

        String titleandsubtitlefinal[];

        try {
            // Convert StringBuilder to String, Component is not compatible :nimodo:
            titleandsubtitlefinal = titleandsubtitle.toString().split(";");
        // In case the command does not contain a separator ";",
        // it will catch the error in the console and send an error message to the sender.
        } catch (Exception e) {
            // Send an error message to the sender using the command
            sender.sendMessage(
                prefix.append(MiniMessageUtil.parse(
                    plugin.getConfig().getString(
                        "messages.title.error",
                        "<dark_red>An error occurred while sending the title. Be sure to use the ';' to separate the title and the subtitle.</dark_red>"))));
            return false;
        }
        String title = PlaceholderUtil.replaceLegacy(PlaceholderAPI.setPlaceholders(player, titleandsubtitlefinal[0]));
        String subtitle = PlaceholderUtil.replaceLegacy(PlaceholderAPI.setPlaceholders(player, titleandsubtitlefinal[1]));
        // Send the Title
        TitleUtil.sendTitle(
            MiniMessageUtil.parse(title, replacePlaceholders(player)), 
            MiniMessageUtil.parse(subtitle, replacePlaceholders(player)), 
            sender,
            1000,
            3000,
            1000);

        // Send message to the sender
        sender.sendMessage(
            prefix.append(MiniMessageUtil.parse(
                plugin.getConfig().getString(
                    "messages.title.successfully",
                    "<green>Title succesfully sended</green>"))));

        if (soundEnabled) {
            //Play the sound
            SoundUtil.playSound(
                soundToPlay,
                sender,
                volume,
                pitch);
        }
        return true;
    }
}
