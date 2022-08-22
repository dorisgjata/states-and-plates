package com.curlyd.statesandplates.model

import com.curlyd.statesandplates.utility.addPoints
import com.curlyd.statesandplates.utility.removePoints
import java.time.Instant
import java.util.*

class Game(val noOfPlayers: Int? = 1) {
    var players: MutableList<Player> = (1..noOfPlayers!!).map() {
        Player("Player#${it}")
    }.toMutableList()

    val uniqueID: String = UUID.randomUUID().toString()
    var isSuddenDeath: Boolean = false
    val start: Date = Date.from(Instant.now())
    var end: Date = Date.from(Instant.now())
    val claims: MutableList<Claim>
        get() {
            players.forEach {
                it.apply {
                    claims.addAll(it.claims)
                }
            }
            claims.sortByDescending { it.time }
            return claims
        }

    fun end() {
        players.forEach {
            it.updatePoints()
        }
        end = Date.from(Instant.now())
        //TODO add to history
        //clear state and game data
    }

    fun addClaim(player: Player, claim: Claim) {
        //check for sudden death mode
        if (isSuddenDeath) {
            claim.apply {
                points = 1.0
            }
            player.apply {
                claims.add(claim)
                points += 1
            }

            return
        }
        addPoints(player, claim, claims)
    }

    fun removeClaim(player: Player, claim: Claim) {
        /// might need to do some changes
        removePoints(player, claim)
    }

    fun beginSuddenDeath(players: List<Player>) {
        isSuddenDeath = true
        players.forEach {
            it.apply {
                claims.clear()
                points = 0.0
            }

        }
    }

    /**
     *
     */
    fun resolveSuddenDeath(): Boolean {
        val sortedPlayers = players.sortedByDescending { it.points }
        val highPlayers = sortedPlayers
            .filter { it.points === sortedPlayers[0].points }
        //do I really need to do this?
        isSuddenDeath = false
        //don't add points if players are tied
        if (highPlayers.size == sortedPlayers.size) return true
        highPlayers.forEach {
            it.apply {
                points += 120
            }
        }
        return true
    }
}
