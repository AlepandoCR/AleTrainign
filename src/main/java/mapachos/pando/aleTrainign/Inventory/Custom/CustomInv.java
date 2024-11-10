package mapachos.pando.aleTrainign.Inventory.Custom;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class CustomInv {

    private final Player player;
    private final Inventory inventory;

    public CustomInv(Player player){
        this.player = player;
        inventory = Bukkit.createInventory(player, 9 * 3, "Menu");
    }

    public Inventory menu() throws MalformedURLException {

        ItemStack selectHead = createSelectHead();

        inventory.setItem(11, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inventory.setItem(13, selectHead);

        return inventory;
    }

    /**This method is used for custom head creations, a head is a smaller block that can have many textures resembling animals/food/decoration and in this case a button on a menu
     *
     * @param displayName Name of the head created
     * @param textureUrl The URL of the texture used
     * @return A head ItemStack object
     * @throws MalformedURLException if the URL is wrong
     */

    private ItemStack createHead(String displayName, String textureUrl) throws MalformedURLException {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();

        // Create a new player profile
        PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID());
        PlayerTextures textures = profile.getTextures();
        textures.setSkin(new URL("https://textures.minecraft.net/texture/" + textureUrl)); // Sets the texture of the profile's skin
        profile.setTextures(textures);

        meta.setPlayerProfile(profile);
        meta.setCustomModelData(123);
        meta.setDisplayName(ChatColor.RESET.toString() + ChatColor.WHITE + ChatColor.BOLD + displayName); //Item Name
        head.setItemMeta(meta);
        return head;
    }

    private ItemStack createSelectHead() throws MalformedURLException {
        return createHead((ChatColor.GREEN.toString() + ChatColor.BOLD + "Select"), "a92e31ffb59c90ab08fc9dc1fe26802035a3a47c42fee63423bcdb4262ecb9b6"); // This is the URL to the texture on the minecraft's database
    }
}
