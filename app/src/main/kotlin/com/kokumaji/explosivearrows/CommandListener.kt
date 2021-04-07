package com.kokumaji.explosivearrows

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.util.Vector
import kotlin.random.Random

class CommandListener: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if(args[0].equals("reload", true)) {

            ExplosiveArrows.instance.reloadConfig()
            sender.sendMessage("Config reloaded")

        } else if(args[0].equals("print", true)) {

            val config = ExplosiveArrows.instance.config
            val strList = config.getList("strings")

            if (strList != null)
                sender.sendMessage("Rick Astley will never: ${strList[Random.nextInt(strList.size)]}")


        } else if(args[0].equals("yeet", true)) {

            val rX = Random.nextDouble(-10.0, 10.0)
            val rZ = Random.nextDouble(-10.0, 10.0)

            for(player in Bukkit.getOnlinePlayers())
                player.velocity = Vector(rX, 5.0, rZ)

        }

        return true
    }

}