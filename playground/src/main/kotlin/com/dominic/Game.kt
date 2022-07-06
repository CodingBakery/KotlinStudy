package com.dominic

fun main() {
    val player = Player("Madrigal")
    player.castFireball()

    // 플레이어의 상태 출력
    printPlayerStatus(player)
}

private fun printPlayerStatus(player: Player) {
    println(
        "(Aura: ${player.auraColor()}) " +
                "(Blessed: ${if (player.isBlessed) "YES" else "NO"})"
    )
    println("${player.name} ${player.formatHealthStatus()}")
}