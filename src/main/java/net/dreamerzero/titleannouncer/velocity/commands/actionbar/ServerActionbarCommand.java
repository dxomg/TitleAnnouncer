package net.dreamerzero.titleannouncer.velocity.commands.actionbar;

import java.util.List;
import java.util.Optional;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;

import net.dreamerzero.titleannouncer.common.utils.ConfigUtils;
import net.dreamerzero.titleannouncer.common.utils.GeneralUtils;
import net.dreamerzero.titleannouncer.common.utils.MiniMessageUtil;
import net.dreamerzero.titleannouncer.common.utils.ComponentType;
import net.dreamerzero.titleannouncer.velocity.utils.SoundUtils;
import net.dreamerzero.titleannouncer.velocity.utils.VPlaceholders;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class ServerActionbarCommand implements SimpleCommand {
    private final ProxyServer server;
    private final MiniMessage mm;
    private SoundUtils sUtils;
    private VPlaceholders vPlaceholders;
    public ServerActionbarCommand(ProxyServer server, MiniMessage mm){
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
            ConfigUtils.noActionbarArgumentProvided(sender);
            return;
        }else if (args.length < 2) {
            ConfigUtils.noServerArgumentProvided(sender);
            return;
        }

        Optional<RegisteredServer> optionalServerObjetive = server.getServer(args[0]);
        if(!optionalServerObjetive.isPresent()) {
            ConfigUtils.noServerFound(sender);
            return;
        }
        RegisteredServer serverObjetive = optionalServerObjetive.get();

        // Concatenate the arguments provided by the command sent.
        String actionbartext = GeneralUtils.getCommandString(args, 1);

        serverObjetive.sendActionBar(
            mm.deserialize(
                MiniMessageUtil.replaceLegacy(
                    actionbartext),
                    vPlaceholders.replaceProxyPlaceholders()));
        sUtils.playProxySound(serverObjetive, ComponentType.ACTIONBAR);
        ConfigUtils.sendConfirmation(ComponentType.ACTIONBAR, sender);
    }

    @Override
    public List<String> suggest(final Invocation invocation) {
        if(invocation.arguments().length <= 1){
            return server.getAllServers().stream()
                .map(server -> server.getServerInfo().getName()).toList();
        }
        return List.of("[message]");
    }

    @Override
    public boolean hasPermission(final Invocation invocation) {
        return invocation.source().hasPermission("titleannouncer.actionbar.server");
    }
}
