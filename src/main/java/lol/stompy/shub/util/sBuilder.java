package lol.stompy.shub.util;

import com.google.common.base.Preconditions;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class sBuilder implements Listener {

    private final ItemStack is;

    public sBuilder(final Material mat) {
        this.is = new ItemStack(mat);
    }

    public sBuilder(final ItemStack is) {
        this.is = is;
    }

    public sBuilder(final Material material, final int amount) {
        this(material, amount, (byte) 0);
    }

    public sBuilder(final Material material, final int amount, final byte data) {
        Preconditions.checkNotNull((Object) material, "Material cannot be null");
        Preconditions.checkArgument(amount > 0, "Amount must be positive");
        this.is = new ItemStack(material, amount, data);
    }

    public sBuilder(final Material material, final int amount, final int data) {
        Preconditions.checkNotNull((Object) material, "Material cannot be null");
        Preconditions.checkArgument(amount > 0, "Amount must be positive");
        this.is = new ItemStack(material, amount, (short) data);
    }

    public sBuilder amount(final int amount) {
        this.is.setAmount(amount);
        return this;
    }


    public sBuilder name(final String name) {
        final ItemMeta meta = this.is.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        this.is.setItemMeta(meta);
        return this;
    }

    public sBuilder lore(final String name) {
        final ItemMeta meta = this.is.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore == null) {
            lore = new ArrayList<String>();
        }
        lore.add(ChatColor.translateAlternateColorCodes('&', name));
        meta.setLore(lore);
        this.is.setItemMeta(meta);
        return this;
    }

    public sBuilder lore(final List<String> lore) {
        final List<String> toSet = new ArrayList<String>();
        final ItemMeta meta = this.is.getItemMeta();
        for (final String string : lore) {
            toSet.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        meta.setLore(toSet);
        this.is.setItemMeta(meta);
        return this;
    }

    public sBuilder lore(final String... lore) {
        ItemMeta meta = this.is.getItemMeta();
        if (meta == null) {
            meta = this.is.getItemMeta();
        }
        meta.setLore(Arrays.asList(lore));
        return this;
    }

    public sBuilder durability(final int durability) {
        this.is.setDurability((short) durability);
        return this;
    }

    public sBuilder enchant(final Enchantment enchantment, final int level) {
        this.is.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public sBuilder enchant(final Enchantment enchantment) {
        this.is.addUnsafeEnchantment(enchantment, 1);
        return this;
    }

    public sBuilder type(final Material material) {
        this.is.setType(material);
        return this;
    }

    public sBuilder clearLore() {
        final ItemMeta meta = this.is.getItemMeta();
        meta.setLore(new ArrayList());
        this.is.setItemMeta(meta);
        return this;
    }

    public sBuilder clearEnchantments() {
        for (final Enchantment e : this.is.getEnchantments().keySet()) {
            this.is.removeEnchantment(e);
        }
        return this;
    }

    public sBuilder data(final short data) {
        this.is.setDurability(data);
        return this;
    }

    public sBuilder color(final Color color) {
        if (this.is.getType() == Material.LEATHER_BOOTS || this.is.getType() == Material.LEATHER_CHESTPLATE || this.is.getType() == Material.LEATHER_HELMET || this.is.getType() == Material.LEATHER_LEGGINGS) {
            final LeatherArmorMeta meta = (LeatherArmorMeta) this.is.getItemMeta();
            meta.setColor(color);
            this.is.setItemMeta(meta);
            return this;
        }
        throw new IllegalArgumentException("color() only applicable for leather armor!");
    }

    public sBuilder meta(final ItemMeta itemMeta) {
        this.is.setItemMeta(itemMeta);
        return this;
    }

    public ItemStack build() {
        return this.is;
    }
}