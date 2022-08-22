package com.curlyd.statesandplates.model

import java.util.*

public class Player(var name: String? = "Player", var avatar: Avatar? = Avatar.Avatar1) {
    val uniqueID = UUID.randomUUID().toString()
    var lifetimePoints: Double = 0.0
    var points: Double = 0.0
    val claims: MutableList<Claim> = mutableListOf()

    fun updatePoints(){
        apply {
            lifetimePoints += points
            //might not need to clear points and claims
            points = 0.0
            claims.clear()
        }
    }
}



