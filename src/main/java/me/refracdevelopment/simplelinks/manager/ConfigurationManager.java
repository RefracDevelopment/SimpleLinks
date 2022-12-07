package me.refracdevelopment.simplelinks.manager;

import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.config.CommentedFileConfiguration;
import dev.rosewood.rosegarden.config.RoseSetting;
import dev.rosewood.rosegarden.manager.AbstractConfigurationManager;
import me.refracdevelopment.simplelinks.SimpleLinks;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Arrays;

public class ConfigurationManager extends AbstractConfigurationManager {

    public enum Setting implements RoseSetting {
        // Config Settings
        MENU_ENABLED("menu.enabled", true),
        MENU_TITLE("menu.title", "&dServer Links"),
        MENU_SIZE("menu.size", 36),
        MENU_FILL_MATERIAL("menu.fill.material", "BLACK_STAINED_GLASS_PANE"),
        MENU_FILL_DATA("menu.fill.data", 0),
        MENU_FILL_NAME("menu.fill.name", "&7"),
        MENU_ITEMS("menu.items", ConfigurationSection.class),
        MENU_ITEMS_WEBSITE_MATERIAL("menu.items.website.material", "SUNFLOWER"),
        MENU_ITEMS_WEBSITE_DATA("menu.items.website.data", 0),
        MENU_ITEMS_WEBSITE_NAME("menu.items.website.name", "&eWebsite"),
        MENU_ITEMS_WEBSITE_LORE("menu.items.website.lore", Arrays.asList("&7Click to receive a link to the website.")),
        MENU_ITEMS_WEBSITE_MESSAGE("menu.items.website.message.text", Arrays.asList("&eWebsite: &dhttps://yourserver.com")),
        MENU_ITEMS_WEBSITE_SLOT("menu.items.website.slot", 10),
        MENU_ITEMS_DISCORD_MATERIAL("menu.items.discord.material", "SUNFLOWER"),
        MENU_ITEMS_DISCORD_DATA("menu.items.discord.data", 0),
        MENU_ITEMS_DISCORD_NAME("menu.items.discord.name", "&eDiscord"),
        MENU_ITEMS_DISCORD_LORE("menu.items.discord.lore", Arrays.asList("&7Click to receive a link to the discord.")),
        MENU_ITEMS_DISCORD_MESSAGE("menu.items.discord.message.text", Arrays.asList("&eDiscord: &dhttps://discord.gg/yourservercode")),
        MENU_ITEMS_DISCORD_SLOT("menu.items.discord.slot", 11),
        MENU_ITEMS_STORE_MATERIAL("menu.items.store.material", "SUNFLOWER"),
        MENU_ITEMS_STORE_DATA("menu.items.store.data", 0),
        MENU_ITEMS_STORE_NAME("menu.items.store.name", "&eStore"),
        MENU_ITEMS_STORE_LORE("menu.items.store.lore", Arrays.asList("&7Click to receive a link to the store.")),
        MENU_ITEMS_STORE_MESSAGE("menu.items.store.message.text", Arrays.asList("&eStore: &dhttps://store.yourserver.net")),
        MENU_ITEMS_STORE_SLOT("menu.items.store.slot", 12),
        MENU_ITEMS_TWITTER_MATERIAL("menu.items.twitter.material", "SUNFLOWER"),
        MENU_ITEMS_TWITTER_DATA("menu.items.twitter.data", 0),
        MENU_ITEMS_TWITTER_NAME("menu.items.twitter.name", "&eTwitter"),
        MENU_ITEMS_TWITTER_LORE("menu.items.twitter.lore", Arrays.asList("&7Click to receive a link to the twitter.")),
        MENU_ITEMS_TWITTER_MESSAGE("menu.items.twitter.message.text", Arrays.asList("&eTwitter: &dhttps://twitter.com/yourserver")),
        MENU_ITEMS_TWITTER_SLOT("menu.items.twitter.slot", 13)
        ;

        private final String key;
        private final Object defaultValue;
        private final String[] comments;
        private Object value = null;

        Setting(String key, Object defaultValue, String... comments) {
            this.key = key;
            this.defaultValue = defaultValue;
            this.comments = comments != null ? comments : new String[0];
        }

        @Override
        public String getKey() {
            return this.key;
        }

        @Override
        public Object getDefaultValue() {
            return this.defaultValue;
        }

        @Override
        public String[] getComments() {
            return this.comments;
        }

        @Override
        public Object getCachedValue() {
            return this.value;
        }

        @Override
        public void setCachedValue(Object value) {
            this.value = value;
        }

        @Override
        public CommentedFileConfiguration getBaseConfig() {
            return SimpleLinks.getInstance().getManager(ConfigurationManager.class).getConfig();
        }
    }

    public ConfigurationManager(RosePlugin rosePlugin) {
        super(rosePlugin, Setting.class);
    }

    @Override
    protected String[] getHeader() {
        return new String[]{
                "  ___________                __          ___     __        __           ",
                " /   _____/__| _____ ______ |  |   ____ |   |   |__| ____ |  | __ ______",
                " \\_____  \\|  |/     \\\\____ \\|  | _/ __ \\|   |   |  |/    \\|  |/ //  ___/",
                " /        \\  |  | |  \\  |_\\ \\  |__  ___/_   |___|  |   |  \\    \\ \\___ \\ ",
                "/_______  /__|__|_|  /   ___/____/\\___  /______ \\__|___|  /__|_ \\____  \\",
                "        \\/         \\/|__|             \\/       \\/       \\/     \\/    \\/"
        };
    }

}