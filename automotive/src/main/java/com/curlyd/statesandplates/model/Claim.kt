package com.curlyd.statesandplates.model

import java.time.Instant
import java.util.*

data class Claim(
    val name: String,
    val id: String,
    val image: String,
    val coordinates: Location
) {
    var points: Double  = 0.0
    var time: Date = Date.from(Instant.now()) //date
    var playerId: String = ""
}

object Location {
    const val latitude = 42.7325
    const val longitude = -84.5555
}

