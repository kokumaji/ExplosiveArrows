package com.kokumaji.explosivearrows

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.util.Vector
import kotlin.random.Random

class CommandListener: CommandExecutor, TabCompleter {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(args.isEmpty()) return false

        when (args[0].toLowerCase()) {
            "reload" -> {
                ExplosiveArrows.instance.reloadConfig()
                sender.sendMessage("${ExplosiveArrows.getPrefix(true)} Config reloaded!")
            } "print" -> {
                val config = ExplosiveArrows.instance.config
                val strList = config.getList("strings")

                if (strList != null)
                    sender.sendMessage("${ExplosiveArrows.getPrefix(true)} Rick Astley will never: ${strList[Random.nextInt(strList.size)]}")
            } "yeet" -> {
                val rX = Random.nextDouble(-10.0, 10.0)
                val rZ = Random.nextDouble(-10.0, 10.0)

                sender.sendMessage("${ExplosiveArrows.getPrefix(true)} YEET!")

                for(player in Bukkit.getOnlinePlayers())
                    player.velocity = player.velocity + Vector(rX, 2.0, rZ)
            }
        }

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
        if (args.size == 1) return setOf("reload", "print", "yeet").filter { it.startsWith(args[0]) }.toMutableList()

        return mutableListOf()
    }

}

// idk this is just experimental
private infix operator fun Vector.plus(vector: Vector): Vector = this.add(vector)
