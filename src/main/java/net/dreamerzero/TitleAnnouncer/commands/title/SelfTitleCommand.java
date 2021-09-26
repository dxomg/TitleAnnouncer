package net.dreamerzero.titleannouncer.commands.title;

import static net.dreamerzero.titleannouncer.utils.PlaceholderUtil.replacePlaceholders;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import net.dreamerzero.titleannouncer.Announcer;
import net.dreamerzero.titleannouncer.utils.ConfigUtils;
import net.dreamerzero.titleannouncer.utils.GeneralUtils;
import net.dreamerzero.titleannouncer.utils.MiniMessageUtil;
import net.dreamerzero.titleannouncer.utils.PlaceholderUtil;
import net.dreamerzero.titleannouncer.utils.TitleUtil;
import net.kyori.adventure.util.TriState;

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

        // Permission Check
        if (sender.permissionValue("announcer.title.test") != TriState.TRUE) {
            ConfigUtils.sendNoTitlePermission(sender);
            return true;
        }

        // The command requires arguments to work
        if(args.length == 0){
            ConfigUtils.sendNoArgumentMessage(sender);
            return true;
        }

        boolean placeholderAPISupport = PlaceholderUtil.placeholderAPIHook();

        // Concatenate the arguments provided by the command sent.
        String titleandsubtitle = GeneralUtils.getCommandString(args);

        if(!titleandsubtitle.contains(";")){
            TitleUtil.sendOnlyTitle(
                MiniMessageUtil.parse(
                MiniMessageUtil.replaceLegacy(
                    placeholderAPISupport ? PlaceholderAPI.setPlaceholders(player, titleandsubtitle) : titleandsubtitle),
                    replacePlaceholders(player)),
                    sender, 1000, 3000, 1000);
            ConfigUtils.sendTitleConfirmation(sender);
            ConfigUtils.playTitleSound(sender);
            return true;
        }

        String titleandsubtitlefinal[];

        if(TitleUtil.getTitleAndSubtitle(titleandsubtitle, sender) == null){
            return false;
        } else {
            titleandsubtitlefinal = TitleUtil.getTitleAndSubtitle(titleandsubtitle, sender);
        }

        // Send the Title
        TitleUtil.sendTitle(
            MiniMessageUtil.parse(placeholderAPISupport ? MiniMessageUtil.replaceLegacy(
                PlaceholderAPI.setPlaceholders(player, titleandsubtitlefinal[0])) : titleandsubtitlefinal[0], 
            replacePlaceholders(player)),
            MiniMessageUtil.parse(placeholderAPISupport ? MiniMessageUtil.replaceLegacy(
                PlaceholderAPI.setPlaceholders(player, titleandsubtitlefinal[1])) : titleandsubtitlefinal[1], 
            replacePlaceholders(player)),
            sender,
            1000,
            3000,
            1000);
        ConfigUtils.sendTitleConfirmation(sender);
        ConfigUtils.playTitleSound(sender);
        return true;
    }
}
