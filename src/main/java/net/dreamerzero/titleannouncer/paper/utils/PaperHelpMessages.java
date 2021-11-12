package net.dreamerzero.titleannouncer.paper.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;

import static net.kyori.adventure.text.Component.*;
import static net.kyori.adventure.text.format.NamedTextColor.*;

public class PaperHelpMessages {
    private PaperHelpMessages(){
        throw new IllegalAccessError("Utility Class");
    }
    private static final MiniMessage mm = MiniMessage.miniMessage();
    public static final Component titleArguments = text("[Title]; [SubTitle]", AQUA);
    public static final Component actionbarArguments = text("[ActionBar]", AQUA);
    public static final Component bossbarArguments = text("[Time] [Color] [Style] [BossBar]", AQUA);
    public static final Component chatArguments = text("[Chat]", AQUA);
    public static final Component titleHelpMessage = text()
        .append(text()
            .append(text("Title", YELLOW))
        )
        .append(newline())
        .append(text()
            .append(text("/announcetitle", GOLD))
        )
        .append(space())
        .append(text()
            .append(titleArguments)
        )
        .append(newline())
        .append(text()
            .append(text("/selftitle", GOLD))
        )
        .append(space())
        .append(text()
            .append(titleArguments)
        )
        .append(newline())
        .append(text()
            .append(text("/worldtitle", GOLD))
        )
        .append(space())
        .append(text()
            .append(titleArguments)
        )
        .append(newline())
        .append(text()
            .append(text("/sendtitle", GOLD))
        )
        .append(space())
        .append(text()
            .append(text("[Player]", AQUA))
        )
        .append(space())
        .append(text()
            .append(titleArguments)
        )
        .build();

    public static final Component actionbarHelpMessage = text()
        .color(YELLOW)
        .append(text()
            .append(text("ActionBar"))
        )
        .append(newline())
        .append(text()
            .append(text("/announceactionbar", GOLD))
        )
        .append(space())
        .append(text()
            .append(actionbarArguments)
        )
        .append(newline())
        .append(text()
            .append(text("/selfactionbar", GOLD))
        )
        .append(space())
        .append(text()
            .append(actionbarArguments)
        )
        .append(newline())
        .append(text()
            .append(text("/worldactionbar", GOLD))
        )
        .append(space())
        .append(text()
            .append(actionbarArguments)
        )
        .append(newline())
        .append(text()
            .append(text("/sendactionbar", GOLD))
        )
        .append(space())
        .append(text()
            .append(text("[Player]", AQUA))
        )
        .append(space())
        .append(text()
            .append(actionbarArguments)
        )
        .build();

    public static final Component chatHelpMessage = text()
        .color(YELLOW)
        .append(text()
            .append(text("Chat"))
        )
        .append(newline())
        .append(text()
            .append(text("/announcechat", GOLD))
        )
        .append(space())
        .append(text()
            .append(chatArguments)
        )
        .append(newline())
        .append(text()
            .append(text("/selfchat", GOLD))
        )
        .append(space())
        .append(text()
            .append(chatArguments)
        )
        .append(newline())
        .append(text()
            .append(text("/worldchat", GOLD))
        )
        .append(space())
        .append(text()
            .append(chatArguments)
        )
        .append(newline())
        .append(text()
            .append(text("/sendchat", GOLD))
        )
        .append(space())
        .append(text()
            .append(text("[Player]", AQUA))
        )
        .append(space())
        .append(text()
            .append(chatArguments)
        )
        .build();

    public static final Component bossbarHelpMessage = text()
        .color(YELLOW)
        .append(text()
            .append(text("BossBar"))
        )
        .append(newline())
        .append(text()
            .append(text("/announcebossbar", GOLD))
        )
        .append(space())
        .append(text()
            .append(bossbarArguments)
        )
        .append(newline())
        .append(text()
            .append(text("/selfbossbar", GOLD))
        )
        .append(space())
        .append(text()
            .append(bossbarArguments)
        )
        .append(newline())
        .append(text()
            .append(text("/worldbossbar", GOLD))
        )
        .append(space())
        .append(text()
            .append(bossbarArguments)
        )
        .append(newline())
        .append(text()
            .append(text("/sendbossbar", GOLD))
        )
        .append(space())
        .append(text()
            .append(text("[Player]", AQUA))

        )
        .append(space())
        .append(text()
            .append(bossbarArguments)
        )
        .build();

    public static final Component fullwikilink = text()
        .append(text()
            .append(Component.text("Visit full guide on")))
        .append(space())
        .append(text()
            .append(text("WIKI"))
            .clickEvent(ClickEvent.openUrl("https://github.com/4drian3d/TitleAnnouncer/wiki"))
            .hoverEvent(HoverEvent.showText(
                mm.deserialize("<gradient:red:blue>Click Here</gradient>"))))
        .build();
    public static final Component titlewikilink = text()
        .append(text()
            .append(Component.text("Visit full guide on")))
        .append(space())
        .append(text()
            .append(text("WIKI"))
            .clickEvent(ClickEvent.openUrl("https://github.com/4drian3d/TitleAnnouncer/wiki/Title-Commands"))
            .hoverEvent(HoverEvent.showText(
                mm.deserialize("<gradient:red:blue>Click Here</gradient>"))))
            .build();
    public static final Component actionbarwikilink = text()
        .append(text()
            .append(Component.text("Visit full guide on")))
        .append(space())
        .append(text()
            .append(text("WIKI"))
            .clickEvent(ClickEvent.openUrl("https://github.com/4drian3d/TitleAnnouncer/wiki/ActionBar-Commands"))
            .hoverEvent(HoverEvent.showText(
                mm.deserialize("<gradient:red:blue>Click Here</gradient>"))))
            .build();

    public static final Component chatwikilink = text()
        .append(text()
            .append(Component.text("Visit full guide on")))
        .append(space())
        .append(text()
            .append(text("WIKI"))
            .clickEvent(ClickEvent.openUrl("https://github.com/4drian3d/TitleAnnouncer/wiki/Chat-Commands"))
            .hoverEvent(HoverEvent.showText(
                mm.deserialize("<gradient:red:blue>Click Here</gradient>"))))
        .build();

    public static final Component bossbarwikilink = text()
        .append(text()
            .append(Component.text("Visit full guide on")))
        .append(space())
        .append(text()
            .append(text("WIKI"))
            .clickEvent(ClickEvent.openUrl("https://github.com/4drian3d/TitleAnnouncer/wiki/Bossbar-Commands"))
            .hoverEvent(HoverEvent.showText(
                mm.deserialize("<gradient:red:blue>Click Here</gradient>"))))
            .build();
}
