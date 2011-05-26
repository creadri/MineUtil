package creadri.inventory;

import creadri.exceptions.InventoryError;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
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
    
    public static void giveItemSecure(Inventory inv, int typeId, short damageId, int quantity) throws InventoryError {
        if (!hasSpaceFor(inv, typeId, damageId, quantity)) {
            throw new InventoryError("Not enough place in inventory");
        }
        inv.addItem(new ItemStack(typeId, quantity, damageId));
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
    
    public static HashMap<Integer,ItemStack> getInventoryContent(Inventory inv) {
        HashMap<Integer,ItemStack> save = new HashMap<Integer, ItemStack>();
        
        int imax = inv.getSize();
        for (int i = 0; i < imax; i++) {
            ItemStack is = inv.getItem(i);
            ItemStack newis = new ItemStack(is.getTypeId(), is.getAmount(), is.getDurability());
            newis.setData(is.getData());
            save.put(i, newis);
        }
        
        return save;
    }
    
    public static void setInventoryContent(HashMap<Integer,ItemStack> hm, Inventory inv) {
        Iterator<Entry<Integer,ItemStack>> it = hm.entrySet().iterator();
        
        while (it.hasNext()) {
            Entry<Integer,ItemStack> entry = it.next();
            inv.setItem(entry.getKey().intValue(), entry.getValue());
        }
    }

    public static boolean isDamagable(int id) {
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
