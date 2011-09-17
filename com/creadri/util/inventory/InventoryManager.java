package com.creadri.util.inventory;


import java.util.HashMap;
import java.util.Iterator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 *
 * @author creadri
 */
public class InventoryManager {
    
    public static void giveItemUnsecure(Inventory inv, int typeId, short damageId, int quantity) {
        inv.addItem(new ItemStack(typeId, quantity, damageId));
    }
    
    public static boolean giveItemSecure(Inventory inv, int typeId, short damageId, int quantity) {
        if (!hasSpaceFor(inv, typeId, damageId, quantity)) {
            return false;
        }
        inv.addItem(new ItemStack(typeId, quantity, damageId));
        return true;
    }
    
    public static void giveOrDropItem(Player player, int typeId, short damageId, int quantity) {
        PlayerInventory inv = player.getInventory();
        HashMap<Integer, ItemStack> hm = inv.addItem(new ItemStack(typeId, quantity, damageId));
        Iterator<ItemStack> it = hm.values().iterator();
        while (it.hasNext()) {
            player.getWorld().dropItem(player.getLocation(), it.next());
        }
    }

    public static boolean hasSpaceFor(Inventory inv, int ressourceId, short damageId, int quantity) {
        int toAdd = Material.getMaterial(ressourceId).getMaxStackSize();

        ItemStack[] iss = inv.getContents();
        int space = 0;

        for (int i = 0; (i < iss.length && (quantity - space) > 0); i++) {
            ItemStack is = iss[i];

            if (is == null) {
                space += toAdd;
            } else if (is.getTypeId() == ressourceId && is.getDurability() == damageId && toAdd > 1) {
                space += toAdd - is.getAmount();
            }
        }

        return (quantity - space) > 0;
    }

    public static boolean isEmpty(Inventory inv) {
        ItemStack[] iss = inv.getContents();

        for (int i = iss.length - 1; i >= 0; i--) {
            if (iss[i] != null) {
                return false;
            }
        }
        return true;
    }
    
    public static void clearInventory(Inventory inv) {
        inv.clear();
    }
    
    public static SavedInventory getInventoryContent(Inventory inv) {
        
        int imax = inv.getSize();
        SavedInventory save = new SavedInventory(imax);
        for (int i = 0; i < imax; i++) {
            save.setItem(i, inv.getItem(i));
        }
        
        return save;
    }
    
    public static void setInventoryContent(SavedInventory save, Inventory inv) {

        int imax = Math.min(save.getSize(), inv.getSize());
        for (int i = 0; i < imax; i++) {
            inv.setItem(i, save.getNewStackFrom(i));
        }
    }

    public static boolean isTool(int id) {
        if (id >= 267 && id <= 286) {
            return true;
        } else if (id >= 290 && id <= 294) {
            return true;
        } else if (id >= 298 && id <= 317) {
            return true;
        }
        return false;
    }
}
