package me.refracdevelopment.simplelinks.commands.command;

import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.command.framework.CommandContext;
import dev.rosewood.rosegarden.command.framework.RoseCommand;
import dev.rosewood.rosegarden.command.framework.RoseCommandWrapper;
import dev.rosewood.rosegarden.command.framework.annotation.RoseExecutable;
import me.refracdevelopment.simplelinks.SimpleLinks;
import me.refracdevelopment.simplelinks.manager.LocaleManager;
import me.refracdevelopment.simplelinks.menu.LinksItem;
import me.refracdevelopment.simplelinks.utilities.Permissions;
import me.refracdevelopment.simplelinks.config.Config;

public class ReloadCommand extends RoseCommand {

    public ReloadCommand(RosePlugin rosePlugin, RoseCommandWrapper parent) {
        super(rosePlugin, parent);
    }

    @RoseExecutable
    public void execute(CommandContext context) {
        this.rosePlugin.reload();
        Config.loadConfig();
        SimpleLinks.getInstance().getMenu().getItems().clear();
        Config.MENU_ITEMS.getKeys(false).forEach(item -> SimpleLinks.getInstance().getMenu().getItems().put(item, new LinksItem(item)));
        this.rosePlugin.getManager(LocaleManager.class).sendMessage(context.getSender(), "command-reload-success");
    }

    @Override
    public String getDefaultName() {
        return "reload";
    }

    @Override
    public String getDescriptionKey() {
        return "command-reload-description";
    }

    @Override
    public String getRequiredPermission() {
        return Permissions.LINKS_RELOAD_COMMAND;
    }

}