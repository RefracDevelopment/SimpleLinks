package me.refracdevelopment.simplelinks.menu;

import ca.tweetzy.skulls.Skulls;
import ca.tweetzy.skulls.api.interfaces.Skull;
import com.cryptomorin.xseries.XMaterial;
import dev.rosewood.rosegarden.utils.NMSUtil;
import lombok.Getter;
import lombok.Setter;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import me.refracdevelopment.simplelinks.SimpleLinks;
import me.refracdevelopment.simplelinks.manager.LocaleManager;
import me.refracdevelopment.simplelinks.utilities.ItemBuilder;
import me.refracdevelopment.simplelinks.utilities.Utilities;
import me.refracdevelopment.simplelinks.utilities.chat.Color;
import me.refracdevelopment.simplelinks.utilities.config.Config;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@Getter
@Setter
public class LinksItem {
    
    private final XMaterial material;
    private final String skullOwner, name;
    private final boolean skulls, headDatabase, customData, glow;
    private final int data, slot, customModelData;
    private final List<String> lore, messages;

    public LinksItem(String item) {
        this.material = Utilities.getMaterial(Config.MENU_ITEMS.getString(item + ".material"));
        if (Config.MENU_ITEMS.getBoolean(item + ".head-database")) {
            this.headDatabase = Config.MENU_ITEMS.getBoolean(item + ".head-database", false);
        } else {
            this.headDatabase = false;
        }
        if (Config.MENU_ITEMS.getBoolean(item + ".skulls")) {
            this.skulls = Config.MENU_ITEMS.getBoolean(item + ".skulls", false);
        } else {
            this.skulls = false;
        }
        if (Config.MENU_ITEMS.contains(item + ".customData")) {
            this.customData = Config.MENU_ITEMS.getBoolean(item + ".customData", false);
        } else {
            this.customData = false;
        }
        this.skullOwner = Config.MENU_ITEMS.getString(item + ".skullOwner");
        this.data = Config.MENU_ITEMS.getInt(item + ".data");
        this.customModelData = Config.MENU_ITEMS.getInt(item + ".customModelData");
        this.name = Config.MENU_ITEMS.getString(item + ".name");
        this.lore = Config.MENU_ITEMS.getStringList(item + ".lore");
        this.messages = Config.MENU_ITEMS.getStringList(item + ".message.text");
        if (Config.MENU_ITEMS.contains(item + ".glow")) {
            this.glow = Config.MENU_ITEMS.getBoolean(item + ".glow");
        } else {
            this.glow = false;
        }
        this.slot = Config.MENU_ITEMS.getInt(item + ".slot");
    }

    public void sendMessage(Player player) {
        messages.forEach(s -> SimpleLinks.getInstance().getManager(LocaleManager.class).sendCustomMessage(player, s));
    }

    public ItemStack getItem(Player player) {
        if (headDatabase) {
            HeadDatabaseAPI api = new HeadDatabaseAPI();
            ItemBuilder item = new ItemBuilder(api.getItemHead(this.skullOwner));

            if (glow) {
                item.addEnchant(Enchantment.ARROW_DAMAGE, 1);
                ItemMeta itemMeta = item.toItemStack().getItemMeta();
                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                item.toItemStack().setItemMeta(itemMeta);
            }

            item.setName(Color.translate(player, this.name));
            this.lore.forEach(s -> item.addLoreLine(Color.translate(player, s)));
            item.setDurability(this.data);
            item.setSkullOwner(this.skullOwner);

            return item.toItemStack();
        } else if (skulls) {
            Skull api = Skulls.getAPI().getSkull(Integer.parseInt(this.skullOwner));
            ItemBuilder item = new ItemBuilder(api.getItemStack());

            if (glow) {
                item.addEnchant(Enchantment.ARROW_DAMAGE, 1);
                ItemMeta itemMeta = item.toItemStack().getItemMeta();
                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                item.toItemStack().setItemMeta(itemMeta);
            }

            item.setName(Color.translate(player, this.name));
            this.lore.forEach(s -> item.addLoreLine(Color.translate(player, s)));
            item.setDurability(this.data);
            item.setSkullOwner(this.skullOwner);

            return item.toItemStack();
        } else if (customData) {
            ItemBuilder item = new ItemBuilder(this.material.parseMaterial());

            if (glow) {
                item.addEnchant(Enchantment.ARROW_DAMAGE, 1);
                ItemMeta itemMeta = item.toItemStack().getItemMeta();
                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                item.toItemStack().setItemMeta(itemMeta);
            }

            if (NMSUtil.getVersionNumber() > 13) {
                item.setCustomModelData(this.customModelData);
            } else {
                Color.log("&cAn error occurred when trying to set custom model data. Make sure your only using custom model data when on 1.14+.");
            }
            item.setName(this.name);
            this.lore.forEach(s -> item.addLoreLine(Color.translate(player, s)));
            item.setDurability(this.data);
            item.setSkullOwner(this.skullOwner);

            return item.toItemStack();
        } else {
            ItemBuilder item = new ItemBuilder(this.material.parseMaterial());

            if (glow) {
                item.addEnchant(Enchantment.ARROW_DAMAGE, 1);
                ItemMeta itemMeta = item.toItemStack().getItemMeta();
                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                item.toItemStack().setItemMeta(itemMeta);
            }

            item.setName(Color.translate(player, this.name));
            this.lore.forEach(s -> item.addLoreLine(Color.translate(player, s)));
            item.setDurability(this.data);
            item.setSkullOwner(this.skullOwner);

            return item.toItemStack();
        }
    }
}