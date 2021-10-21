package net.dreamerzero.titleannouncer.velocity.commands.chat;

import java.util.List;
import java.util.Optional;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;

import net.dreamerzero.titleannouncer.common.utils.ComponentType;
import net.dreamerzero.titleannouncer.common.utils.ConfigUtils;
import net.dreamerzero.titleannouncer.common.utils.GeneralUtils;
import net.dreamerzero.titleannouncer.common.utils.MiniMessageUtil;
import net.dreamerzero.titleannouncer.velocity.utils.SoundUtils;
import net.dreamerzero.titleannouncer.velocity.utils.VPlaceholders;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class SendChatCommand implements SimpleCommand {
    private final ProxyServer server;
    private final MiniMessage mm;
    private SoundUtils sUtils;
    private VPlaceholders vPlaceholders;
    public SendChatCommand(ProxyServer server, MiniMessage mm){
        this.server = server;
        this.mm = mm;
        this.sUtils = new SoundUtils(server);
        this.vPlaceholders = new VPlaceholders(server);
    }

    @Override
    public void execute(Invocation invocation) {
        CommandSource sender = invocation.source();
        String[] args = invocation.arguments();

        if(args.length == 0) {
            ConfigUtils.noChatArgumentProvided(sender);
            return;
        }else if (args.length < 2) {
            ConfigUtils.noChatPlayerArgumentProvided(sender);
            return;
        }

        Optional<Player> optionalPlayerObjetive = server.getPlayer(args[0]);
        if(!optionalPlayerObjetive.isPresent()) {
            ConfigUtils.playerNotFoundMessage(sender);
            return;
        }
        Player playerObjetive = optionalPlayerObjetive.get();

        // Concatenate the arguments provided by the command sent.
        String chattext = GeneralUtils.getCommandString(args, 1);

        playerObjetive.sendMessage(
            mm.deserialize(
                MiniMessageUtil.replaceLegacy(
                    chattext),
                    vPlaceholders.replaceProxyPlaceholders(playerObjetive)));
        sUtils.playProxySound(playerObjetive, ComponentType.CHAT);
        ConfigUtils.sendConfirmation(ComponentType.CHAT, sender);
    }

    @Override
    public List<String> suggest(final Invocation invocation) {
        if(invocation.arguments().length < 1){
            return server.getAllPlayers().stream().map(Player::getUsername).toList();
        }
        return List.of("[message]");
    }

    @Override
    public boolean hasPermission(final Invocation invocation) {
        return invocation.source().hasPermission("titleannouncer.chat.send");
    }
}