package me.refracdevelopment.simplelinks.utilities.config;

import me.refracdevelopment.simplelinks.manager.ConfigurationManager;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

public class Config {
    public static boolean MENU_ENABLED;
    public static String MENU_TITLE;
    public static int MENU_SIZE;
    public static Material MENU_FILL_MATERIAL;
    public static int MENU_FILL_DATA;
    public static String MENU_FILL_NAME;
    public static ConfigurationSection MENU_ITEMS;

    public static void loadConfig() {
        MENU_ENABLED = ConfigurationManager.Setting.MENU_ENABLED.getBoolean();
        MENU_TITLE = ConfigurationManager.Setting.MENU_TITLE.getString();
        MENU_SIZE = ConfigurationManager.Setting.MENU_SIZE.getInt();
        MENU_FILL_MATERIAL = Material.getMaterial(ConfigurationManager.Setting.MENU_FILL_MATERIAL.getString());
        MENU_FILL_DATA = ConfigurationManager.Setting.MENU_FILL_DATA.getInt();
        MENU_FILL_NAME = ConfigurationManager.Setting.MENU_FILL_NAME.getString();
        MENU_ITEMS = ConfigurationManager.Setting.MENU_ITEMS.getSection();
    }
}