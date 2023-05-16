package me.refracdevelopment.simplelinks.utilities.chat;

import dev.rosewood.rosegarden.utils.StringPlaceholders;
import me.refracdevelopment.simplelinks.SimpleLinks;
import me.refracdevelopment.simplelinks.manager.LocaleManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Placeholders {

    public static String setPlaceholders(CommandSender sender, String placeholder) {
        final LocaleManager locale = SimpleLinks.getInstance().getManager(LocaleManager.class);

        placeholder = placeholder.replace("%prefix%", locale.getLocaleMessage("prefix"));
        if (sender instanceof Player) {
            Player player = (Player) sender;

            placeholder = placeholder.replace("%player%", player.getName());
            placeholder = placeholder.replace("%displayname%", player.getDisplayName());
        }
        placeholder = placeholder.replace("%arrow%", "\u00BB");
        placeholder = placeholder.replace("%arrow2%", "\u27A5");
        placeholder = placeholder.replace("%arrow_2%", "\u27A5");
        placeholder = placeholder.replace("%star%", "\u2726");
        placeholder = placeholder.replace("%circle%", "\u2219");
        placeholder = placeholder.replace("|", "\u239F");

        return placeholder;
    }

    public static StringPlaceholders setPlaceholders(CommandSender sender) {
        StringPlaceholders.Builder placeholders = StringPlaceholders.builder();
        final LocaleManager locale = SimpleLinks.getInstance().getManager(LocaleManager.class);

        placeholders.add("prefix", locale.getLocaleMessage("prefix"));
        if (sender instanceof Player) {
            Player player = (Player) sender;

            placeholders.add("player", player.getName());
            placeholders.add("displayname", player.getDisplayName());
        }
        placeholders.add("arrow", "\u00BB");
        placeholders.add("arrow2", "\u27A5");
        placeholders.add("arrow_2", "\u27A5");
        placeholders.add("star", "\u2726");
        placeholders.add("circle", "\u2219");
        placeholders.add("|", "\u239F");

        return placeholders.build();
    }
}
