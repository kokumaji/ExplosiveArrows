package com.kokumaji.explosivearrows

import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.entity.Creeper
import org.bukkit.entity.Monster
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileHitEvent

class ArrowListener : Listener {

    @EventHandler
    fun onArrowHit(event: ProjectileHitEvent) {
        val projectile = event.entity
        val loc = projectile.location

        if(projectile !is Arrow) {
            return
        }

        val shooter = projectile.shooter
        val hitEntity = event.hitEntity ?: return
        val hitBlock = event.hitBlock ?: return

        if(shooter !is Player) {
            return
        }

        loc.world.spawnParticle(Particle.BLOCK_CRACK, loc, 5, hitBlock.blockData)
        projectile.remove()

        if(hitBlock.type != Material.GRASS_BLOCK) {
            loc.world.createExplosion(loc, 4f)
        }

        projectile.remove()

        if(hitEntity is Creeper) {
            hitEntity.explode()
        } else if (hitEntity is Monster) {
            loc.world.createExplosion(loc, 4f)
        }
    }
}