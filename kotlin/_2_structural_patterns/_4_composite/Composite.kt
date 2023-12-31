package _2_structural_patterns._4_composite

import _2_structural_patterns._3_bridge.RegularLegs
import _2_structural_patterns._3_bridge.Rifle
import _2_structural_patterns._3_bridge.StormTrooper
import _2_structural_patterns._3_bridge.Trooper

class Squad(val units: List<Trooper>) {
    constructor(vararg units: Trooper):
            this(units.toList())

    fun move(x: Long, y: Long) {
        for (u in units) {
            u.move(x, y)
        }
    }
    fun attack(x: Long, y: Long) {
        for (u in units) {
            u.attackRebel(x, y)
        }
    }
}

fun main() {
    val bobaFett = StormTrooper(Rifle(), RegularLegs())
    val squad = Squad(bobaFett.copy(), bobaFett.copy(), bobaFett.copy())
}
