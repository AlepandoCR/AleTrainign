package mapachos.pando.aleTrainign.Events.Listeners;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import mapachos.pando.aleTrainign.AleTrainign;
import mapachos.pando.aleTrainign.Inventory.Custom.CustomInv;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.net.MalformedURLException;

public class PlayerEventListener implements Listener {

    /**
     * Events are the second thing when it comes to executing your code, certain functions you may add may be triggered by an event
     */

    private final AleTrainign plugin;

    public PlayerEventListener(AleTrainign plugin) {

        this.plugin = plugin;

    }


    @EventHandler
    private void playerJumpListener(PlayerJumpEvent event){

        Player player = event.getPlayer();

        player.sendMessage("Jump listened c:");

    }

    @EventHandler
    private void playerMoveListener(PlayerMoveEvent event){

        Player player = event.getPlayer();

        // We add a second condition, if the player moves and is also holding an anvil, then the player won't be able to move and be notified for it

        if(player.getItemInHand().asOne().equals(new ItemStack(Material.ANVIL))){

            player.sendMessage("You cant walk with an anvil, its too heavy!");

            // We can cancel certain events

            event.setCancelled(true);
        }
    }

    /**
     * We can have two listeners for the same event, but its recommended only having one that calls multiple functions
     *
     */

    @EventHandler
    private void playerCoordinatesRequest(PlayerJumpEvent event){

        Player player = event.getPlayer();

        Location location = player.getLocation();

        // We also verify that the player's model current state is SNEAKING
        if(player.getPose().equals(Pose.SNEAKING)){

            Rabbit rabbit = (Rabbit) location.getWorld().spawnEntity(player.getLocation(), EntityType.RABBIT);
            rabbit.setCustomName("My Custom Rabbit"); // You can add custom names to entities
            rabbit.setCustomNameVisible(true); // Option that toggles the custom name
            rabbit.setAdult(); // Makes the rabbit's model an adult in game
        }
    }

    /**
     *  We can filter certain actions of an event, in this case, wether it's a right click or a left click
     * We're also making use of a custom Inventory, go to {@link CustomInv} to learn more about the creation of Inventories
     */

    @EventHandler
    private void playerInteractListener(PlayerInteractEvent event) throws MalformedURLException {

        if(event.getItem() != null){

            if(event.getItem().asOne().equals(new ItemStack(Material.DIRT)) && event.getAction().isRightClick()){
                CustomInv inv = new CustomInv(event.getPlayer());
                event.getPlayer().openInventory(inv.menu());
            }

        }
    }

    /**
     * This listener is meant for reading the options clicked on a menu with a specific title in this case "menu", you can set the title of a menu when creating it {@link CustomInv}, in this we case send a message to the player when clicking a red stained-glass pane
     *
     */

    @EventHandler
    private void playerUseMenu(InventoryClickEvent event){

        Player player = (Player) event.getWhoClicked();

        ItemStack item = event.getCurrentItem();

        assert item != null;

        if(event.getView().getTitle().equalsIgnoreCase("menu")){

            event.setCancelled(true); // Cancel the event so the inventory appears as a menu and items can only be clicked but not moved or dragged

            if(item.equals(new ItemStack(Material.RED_STAINED_GLASS_PANE))){
                player.sendMessage("You clicked the red glass!");
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BANJO, 10,1f);

                // Example of setting a block to a different material

                player.getLocation().subtract(0,1,0).getBlock().setType(Material.RED_STAINED_GLASS);
            }
        }
    }
}
