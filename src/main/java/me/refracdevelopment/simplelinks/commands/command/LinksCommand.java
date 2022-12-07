package me.refracdevelopment.simplelinks.commands.command;

import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.command.command.BaseCommand;
import dev.rosewood.rosegarden.command.framework.CommandContext;
import dev.rosewood.rosegarden.command.framework.RoseCommandWrapper;
import dev.rosewood.rosegarden.command.framework.annotation.RoseExecutable;
import me.refracdevelopment.simplelinks.manager.LocaleManager;
import me.refracdevelopment.simplelinks.menu.Links;
import me.refracdevelopment.simplelinks.utilities.chat.Placeholders;
import me.refracdevelopment.simplelinks.utilities.config.Config;
import org.bukkit.entity.Player;

public class LinksCommand extends BaseCommand {

    public LinksCommand(RosePlugin rosePlugin, RoseCommandWrapper parent) {
        super(rosePlugin, parent);
    }

    @RoseExecutable
    public void execute(CommandContext context) {
        final LocaleManager locale = this.rosePlugin.getManager(LocaleManager.class);

        // Make sure the sender is a player.
        if (!(context.getSender() instanceof Player)) {
            locale.sendMessage(context.getSender(), "no-console", Placeholders.setPlaceholders(context.getSender()));
            return;
        }

        Player player = (Player) context.getSender();

        if (Config.MENU_ENABLED) {
            this.rosePlugin.getManager(Links.class).getLinks().openInventory(player);
        } else {
            locale.sendMessage(player, "links", Placeholders.setPlaceholders(player));
        }
    }
}