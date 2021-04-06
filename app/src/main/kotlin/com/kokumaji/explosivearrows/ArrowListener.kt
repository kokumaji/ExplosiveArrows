package com.kokumaji.explosivearrows

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.block.Block
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.projectiles.ProjectileSource

class ArrowListener : Listener {

    @EventHandler
    fun onArrowHit(event: ProjectileHitEvent) {
        var projectile : Projectile = event.entity
        var loc: Location = projectile.location

        if(projectile is Arrow) {
            var shooter: ProjectileSource? = projectile.shooter
            if(!(shooter is Player)) return

            var hitEntity: Entity? = event.hitEntity
            var hitBlock: Block? = event.hitBlock

            if(hitBlock != null) {
                var loc: Location = projectile.getLocation()

                loc.world.spawnParticle(Particle.BLOCK_CRACK, loc, 5, hitBlock.blockData)
                projectile.remove()

                if(!(hitBlock.type == Material.GRASS_BLOCK)) {
                    loc.world.createExplosion(loc, 4f)
                }
            }

            if(hitEntity != null) {
                projectile.remove()
                if(hitEntity is Creeper) {
                    hitEntity.explode()
                } else if (hitEntity is Monster) {
                    loc.world.createExplosion(loc, 4f)
                }
            }

        }
    }

}
