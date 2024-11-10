package mapachos.pando.aleTrainign.Commands;

import mapachos.pando.aleTrainign.AleTrainign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Manager implements CommandExecutor, TabCompleter {

    AleTrainign plugin;

    /**
     * Commands are the main way in which we run our code, if it's a game then you should have a command to play it
     */

    public Manager(AleTrainign plugin){

        // Calling the plugin is always a good practice

        this.plugin = plugin;

    }

    /**
     * {@link #onCommand(CommandSender, Command, String, String[])}
     * Executes a command when issued by a player or the console.
     *
     * @param commandSender The sender of the command, which could be a player or the console.
     * @param command       The command object, which must be registered in the {@code plugin.yml} file.
     * @param s             The label of the command.
     * @param strings       The arguments provided with the command; for example, {@code /Example peter} will have
     *                      {@code strings[0]} as "peter".
     * @return              {@code true} if the command was handled successfully, {@code false} otherwise.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(strings.length == 0){
            commandSender.sendMessage("You executed the Example command! :)");
            return true;
        }

        switch (strings[0].toLowerCase()){

            case "subexample":

                // We can also call another method or class to handle the sub commands

                commandSender.sendMessage("You executed the sub command of the Example command");

                return true;

            default:

                commandSender.sendMessage("You executed the Example command! :)");

                return true;
        }
    }

    /**
     * {@link #onTabComplete(CommandSender, Command, String, String[])}
     *  Gives the user an easier way to identify your command by showing a preview of it when typing its characters
     *
     * @param commandSender The sender of the command, which could be a player or the console.
     * @param command       The command object, which must be registered in the {@code plugin.yml} file.
     * @param s             The label of the command.
     * @param strings       The arguments provided with the command; for example, {@code /Example peter} will have
     *                      {@code strings[0]} as "peter".
     * @return  The list of completions
     */

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings){

        List<String> completions = new ArrayList<>();

        Player player = null;

        // Extract the player object in case we have to verify permissions

        if (commandSender instanceof Player) {

            player = (Player) commandSender;

        }

        // The main command is "Example", if the player writes this command, the completions will be shown to the player

        if (command.getName().equalsIgnoreCase("Example")) {

            completions.add("SubExample");

        }

        return completions;
    }
}
