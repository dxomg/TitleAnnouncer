package net.dreamerzero.titleannouncer.paper.utils;

import java.util.List;

import org.bukkit.Bukkit;

import static net.kyori.adventure.text.Component.text;

import net.kyori.adventure.text.minimessage.Template;

public class PPlaceholders {
    /**
     * Replace Placeholders in Title/ActionBar
     * for the context of Console
     * @return Placeholders for console
     */
    public static List<Template> replacePlaceholders() {
        return List.of(
            Template.of("online", text(Bukkit.getServer().getOnlinePlayers().size())),
            Template.of("mspt", text(String.valueOf(Bukkit.getAverageTickTime()/20).substring(0, 3))),
            Template.of("tps", text(String.valueOf(Bukkit.getTPS()[0]).substring(0, 4))));
    }
    /**
     * Replace Placeholders in Title/ActionBar
     * for player sender
     * @param player
     * @return Placeholders for sender player
     */
    public static List<Template> replacePlaceholders(org.bukkit.entity.Player player) {
        return List.of(
            Template.of("name", text(player.getName())),
            Template.of("ping", text(String.valueOf(player.getPing()))),
            Template.of("online", text(Bukkit.getServer().getOnlinePlayers().size())),
            Template.of("world", text(player.getWorld().getName())),
            Template.of("player", text(player.getName())),
            Template.of("mspt", text(String.valueOf(Bukkit.getAverageTickTime()/20).substring(0, 3))),
            Template.of("tps", text(String.valueOf(Bukkit.getTPS()[0]).substring(0, 4))));
    }
}
