package com.curlyd.statesandplates
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import com.curlyd.statesandplates.model.*

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }

}
fun main(){
//    var doris = Player("Doris")
//    var niki = Player("Niki", Avatar.Avatar3)
//    var players = mutableListOf<Player>(doris, niki)
    var game = Game(2)
//    doris.points = 30
    var MI = Claim("Michigan", "MI", "", Location)
//    doris.claims.add(MI)
    game.players.forEach {
        println("${it.name}")

    }
    println("${game.players}, ${game.noOfPlayers}")

//    game.removePoints(doris, MI)
//    println("${doris.claims}, ${doris.points}")
}