/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022 RefracDevelopment
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package me.refrac.simplelinks.utilities.files;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

/**
 * Author:  Zachary (Refrac) Baldwin
 * Created: 2022-7-7
 */
public class Config {
    public static String NO_PERMISSION;
    public static String RELOAD;
    public static List<String> LINKS;
    public static boolean MENU_ENABLED;
    public static String MENU_TITLE;
    public static int MENU_SIZE;
    public static Material MENU_FILL_MATERIAL;
    public static int MENU_FILL_DATA;
    public static String MENU_FILL_NAME;
    public static List<String> MENU_FILL_LORE;
    public static ConfigurationSection MENU_ITEMS;

    public static void loadConfig() {
        // Messages
        NO_PERMISSION = Files.getConfig().getString("messages.no-permission");
        RELOAD = Files.getConfig().getString("messages.reload");

        // Links
        LINKS = Files.getConfig().getStringList("links");

        // Menu
        MENU_ENABLED = Files.getConfig().getBoolean("menu.enabled");
        MENU_TITLE = Files.getConfig().getString("menu.title");
        MENU_SIZE = Files.getConfig().getInt("menu.size");
        MENU_FILL_MATERIAL = Material.getMaterial(Files.getConfig().getString("menu.fill.material"));
        MENU_FILL_DATA = Files.getConfig().getInt("menu.fill.data");
        MENU_FILL_NAME = Files.getConfig().getString("menu.fill.name");
        MENU_FILL_LORE = Files.getConfig().getStringList("menu.fill.lore");
        MENU_ITEMS = Files.getConfig().getConfigurationSection("menu.items");
    }
}