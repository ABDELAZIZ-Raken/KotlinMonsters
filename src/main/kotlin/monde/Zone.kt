package org.example.monde

import org.example.monstre.EspeceMonstre

class Zone(
    var id : Int,
    var nom : Int,
    var expZone : Int,
    var espacesMonstres : MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante : Zone?,
    var ZonePrecedante : Zone? = null


)
