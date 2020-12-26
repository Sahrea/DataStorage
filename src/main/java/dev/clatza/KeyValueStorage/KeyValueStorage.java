package dev.clatza.KeyValueStorage;

import dev.clatza.KeyValueStorage.Database.KeyValueDatabase;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.plugin.java.JavaPlugin;

public class KeyValueStorage extends JavaPlugin implements Listener {
    @Override
    public void onEnable()
    {
        GlobalDataStorage.Plugin = this;
        GlobalDataStorage.ConfigReader = new ConfigReader(this);

        if(GlobalDataStorage.Database == null)
            GlobalDataStorage.Database = new KeyValueDatabase();

        getServer().getPluginManager().registerEvents(this, this);
    }

    public KeyValueDatabase getStorage()
    {
        return GlobalDataStorage.Database;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (cmd.getName().equalsIgnoreCase("keyvalue")) {
            if (sender instanceof BlockCommandSender)
            {
                BlockCommandSender commandblock = (BlockCommandSender)sender;

                if(args.length < 2)
                {
                    commandblock.sendMessage("You must enter a key to get or set a value.");
                    return true;
                }

                if(args[0].equals("get") || args[0].equals("set"))
                {
                    if(args[0].equals("get"))
                    {
                        String message = GlobalDataStorage.Database.getEntry(args[1]);

                        if(message != null)
                        {
                            commandblock.sendMessage(message);
                            return true;
                        }

                        commandblock.sendMessage("");

                        return true;
                    }

                    if(args[0].equals("set"))
                    {
                        if(args.length < 3)
                        {
                            commandblock.sendMessage("You must enter a data value to set.");
                            return true;
                        }

                        GlobalDataStorage.Database.setEntry(args[1], args[2]);
                        return true;
                    }
                }else{
                    commandblock.sendMessage("You must define a command mode. GET or SET");
                    return true;
                }
            }

            return true;
        }

        return true;
    }
}
