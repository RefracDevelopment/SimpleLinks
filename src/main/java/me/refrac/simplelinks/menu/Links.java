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
package me.refrac.simplelinks.menu;

import me.refrac.simplelinks.SimpleLinks;
import me.refrac.simplelinks.utilities.Manager;
import me.refrac.simplelinks.utilities.files.Config;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:  Zachary (Refrac) Baldwin
 * Created: 2022-7-7
 */
public class Links extends Manager {
    private final LinksGUI menu;
    private final Map<String, LinksItem> items;

    public Links(SimpleLinks plugin) {
        super(plugin);
        this.menu = new LinksGUI(plugin);
        this.items = new HashMap<>();
        for (String item : Config.MENU_ITEMS.getKeys(false)) {
            this.items.put(item, new LinksItem(item));
        }
    }

    public LinksGUI getLinks() {
        return this.menu;
    }

    public Map<String, LinksItem> getItems() {
        return this.items;
    }
}
