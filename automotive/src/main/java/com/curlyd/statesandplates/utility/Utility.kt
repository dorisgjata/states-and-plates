package com.curlyd.statesandplates.utility

import com.curlyd.statesandplates.model.Claim
import com.curlyd.statesandplates.model.Location
import com.curlyd.statesandplates.model.Player
import java.time.Instant
import java.util.*
import kotlin.math.*


const val MULTIPLIER = 4


fun addPoints(player: Player, claim: Claim, claims: List<Claim>) {
    var points = calcPoints(player, claim, claims)
    //if given claim id
    //val claim = claims.find { it.id == id }

//    what's the benefit of using apply
//    claim.apply {
//        points = points //do we need this?
//        time = Date.from(Instant.now())
//    }
    claim.apply {
        this.playerId = player.uniqueID
        this.points = points
        this.time = Date.from(Instant.now())
    }
    player.apply {
        this.claims.add(claim)
        this.points += points
    }
}

fun removePoints(player: Player, claim: Claim) {
    player.apply {
        this.claims.remove(claim)
        this.points -= claim.points
    }

//        might need to change it to the following
//        val gamePlayer = players.find { it.uniqueID == player.uniqueID }
//        gamePlayer?.claims!!.remove(claim)
//        gamePlayer?.points -= claim.points
}

fun calcPoints(player: Player, claim: Claim, claims: List<Claim>): Double {
    val position = getLocation() as Location //TODO later
    val distance = calcDistance(position, claim)
    return pointMultiplier(player, claims) * distance
    // maybe throw a catch for the location stuff later
}

fun pointMultiplier(player: Player, claims: List<Claim>): Double {
    var streak: Double = 1.0
    claims.forEach {
        if (it.playerId === player.uniqueID) streak += 0.5
        //else return false
    }
    return MULTIPLIER + streak
}

/**
 * Calculate distance "as the crow flies" between 2 locations
 */
fun calcDistance(position: Location, claim: Claim): Double {
    val radian = Math.PI
    val radius = 6371 //Earth's radius
    val radLat1 = Location.latitude * radian//TODO later
    val radLat2 = claim.coordinates.latitude * radian
    val deltaLat = (claim.coordinates.latitude - Location.latitude) * radian//TODO later
    val deltaLong = (claim.coordinates.longitude - Location.longitude) * radian//TODO later
    val sqLen =
        sin(deltaLat / 2) * sin(deltaLat / 2) + cos(radLat1) * cos(radLat2) * sin(deltaLong / 2) * sin(
            deltaLong
        );
    val radianDistance = 2 * atan2(sqrt(sqLen), sqrt(1 - sqLen))
    return floor(radius * radianDistance)
}

fun getLocation(): Any {
    //TODO implementation
    return object {
        val latitude = 42.7325
        val longitude = -84.5555
    }
}
