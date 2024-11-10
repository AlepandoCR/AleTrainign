package mapachos.pando.aleTrainign;

import mapachos.pando.aleTrainign.Commands.Manager;
import mapachos.pando.aleTrainign.Events.Listeners.PlayerEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class AleTrainign extends JavaPlugin {

    /**
     * This is the main file of a plugin, all things such as commands, recipes, event listeners, etc... have to be loaded on {@link #onEnable()} for the server to load them at the startup of itself.
     */

    @Override
    public void onEnable() {

        this.getServer().getLogger().info("The AleTraining plugin has loaded without any problem :D");
        registerCommandExecutors(this);
        registerEventListeners(this);
    }

    /** Some other instructions may be performed on {@link #onDisable()} to unload things of your plugin in case the server restarts/crashes/stops this can be useful when working with temporary entities or any data we need to save
     **/

    @Override
    public void onDisable() {

        // Plugin shutdown logic

    }

    /**Common method to register one or more CommandExecutors. Go to {@link mapachos.pando.aleTrainign.Commands.Manager} to learn more about Commands and how to get use from them.
     * @param plugin This method calls for an AleTraining object which is heir to the JavaPlugin class, meaning its calling for the plugin itself**/

    private void registerCommandExecutors(AleTrainign plugin){

        this.getCommand("Example").setExecutor(new Manager(plugin));

    }

    /**Common method to register one or more EventListeners. Go to {@link PlayerEventListener} to learn more about Events and how to get use from them.
     * @param plugin This method calls for an AleTraining object which is heir to the JavaPlugin class, meaning its calling for the plugin itself**/

    private void registerEventListeners(AleTrainign plugin){

        plugin.getServer().getPluginManager().registerEvents(new PlayerEventListener(plugin), plugin);

    }
}


