package me.refracdevelopment.simplelinks.menu;

import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.manager.Manager;
import me.refracdevelopment.simplelinks.utilities.config.Config;

import java.util.HashMap;
import java.util.Map;

public class Links {
    private final LinksGUI menu;
    private final Map<String, LinksItem> items;

    public Links() {
        this.menu = new LinksGUI();
        this.items = new HashMap<>();
        Config.MENU_ITEMS.getKeys(false).forEach(item -> this.items.put(item, new LinksItem(item)));
    }

    public LinksGUI getLinks() {
        return this.menu;
    }

    public Map<String, LinksItem> getItems() {
        return this.items;
    }

}