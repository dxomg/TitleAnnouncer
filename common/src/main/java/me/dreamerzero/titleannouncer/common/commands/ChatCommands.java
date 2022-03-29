package me.dreamerzero.titleannouncer.common.commands;

import java.util.Optional;
import java.util.function.Function;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class ChatCommands<A> extends AnnouncerCommand<A> {
    private final CommandAdapter adapter;

    public ChatCommands(CommandAdapter adapter, Function<A, Audience> fromAToAudience){
        this.adapter = adapter;
        this.fromAToAudience = fromAToAudience;
    }

    public LiteralArgumentBuilder<A> chat(LiteralArgumentBuilder<A> platformArgument){
        return LiteralArgumentBuilder.<A>literal("announcechat")
            .then(LiteralArgumentBuilder.<A>literal("global")
                .then(RequiredArgumentBuilder.<A, String>argument("message", StringArgumentType.string())
                    .executes(cmd -> {
                        adapter.getGlobalAudience().sendMessage(
                            MiniMessage.miniMessage().deserialize(
                                cmd.getArgument("message", String.class)));
                        return 1;
                    })
                )
            )
            .then(LiteralArgumentBuilder.<A>literal("send")
                .then(RequiredArgumentBuilder.<A, String>argument("message", StringArgumentType.string())
                    .executes(cmd -> {
                        getAudience(cmd.getSource()).sendMessage(
                            MiniMessage.miniMessage().deserialize(
                                cmd.getArgument("message", String.class)));
                        return 1;
                    })
                )
            )
            .then(LiteralArgumentBuilder.<A>literal("self")
                .then(RequiredArgumentBuilder.<A, String>argument("objetive", StringArgumentType.word())
                    .then(RequiredArgumentBuilder.<A, String>argument("message", StringArgumentType.string())
                        .executes(cmd -> {
                            Optional<Audience> aud = adapter.stringToAudience(cmd.getArgument("objetive", String.class));
                            if(aud.isPresent()){
                                aud.get().sendActionBar(
                                    MiniMessage.miniMessage().deserialize(
                                        cmd.getArgument("message", String.class)));
                                return 1;
                            } else {
                                return 0;
                            }
                        })
                    )
                )
            )
            .then(platformArgument.build());
    }
}