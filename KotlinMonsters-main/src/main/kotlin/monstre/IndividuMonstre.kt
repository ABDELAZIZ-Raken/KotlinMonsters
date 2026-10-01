package org.example.monstre
import jdk.internal.vm.StackChunk.init
import org.example.dresseur.Entraineur
import kotlin.math.pow
import kotlin.math.roundToInt

class IndividuMonstre(

    val id : Int,
    var nom : String,
    val espece : EspeceMonstre,
    var entraineur: Entraineur? = null,
    expInit : Double


) {
    var niveau: Int = 1
    var attaque: Int = this.espece.baseAttaque + (-2..2).random()
    var defense: Int = this.espece.baseDefense + (-2..2).random()
    var vitesse: Int = this.espece.baseVitesse + (-2..2).random()
    var attaqueSpe: Int = this.espece.baseAttaqueSpe + (-2..2).random()
    var defenseSpe: Int = this.espece.baseDefenseSpe + (-2..2).random()
    var pvMax: Int = this.espece.basePv + (-5..5).random()
    var potentiel: Double = 0.5 + Math.random() * 1.5
    var exp: Double = 0.0
        set(nouveauexp) {

            field = nouveauexp

            var estniveau1 = false

            if(this.niveau == 1){
                estniveau1 = true
            }
            while(field >= palierExp()){
                levelUp()


            if(estniveau1 == false){
                println("Le monstre $nom est maintenant niveau $niveau")
                println("⭐ $nom monte au niveau $niveau !")
                println("   Attaque=$attaque  Défense=$defense  Vitesse=$vitesse")
                println("   AttaqueSpe=$attaqueSpe  DéfenseSpe=$defenseSpe")
                println("   PV=$pv / PVMax=$pvMax")
            }
            }

        }
    var pv: Int = pvMax
        get() = field
        set(nouveauPv) {

            field = nouveauPv.coerceIn(0, pvMax)
        }

    init {
        this.exp = expInit
    }


    fun palierExp(): Int {
        return (100 * ((this.niveau - 1).toDouble().pow(2.0))).toInt()
    }

    fun levelUp() {
        // Caractéristiques (sauf PV max)
        this.attaque += (espece.baseAttaque * potentiel).roundToInt() + (-2..2).random()
        this.defense += (espece.baseDefense * potentiel).roundToInt() + (-2..2).random()
        this.vitesse += (espece.baseVitesse * potentiel).roundToInt() + (-2..2).random()
        this.attaqueSpe += (espece.baseAttaqueSpe * potentiel).roundToInt() + (-2..2).random()
        this.defenseSpe += (espece.baseDefenseSpe * potentiel).roundToInt() + (-2..2).random()

        // PV max
        val gainPvMax = (espece.basePv * potentiel).roundToInt() + (-5..5).random()
        this.pvMax += gainPvMax
        this.pv += gainPvMax

        // Niveau
        this.niveau++


    }
    fun attaquer(cible: IndividuMonstre){
        var degatbrut = this.attaque
        var degatTotal = degatbrut - (this.defense / 2)

        if(degatTotal < 1){
            degatTotal = 1
        }
        else{
            var pvAvant = cible.pv
            cible.pv -= degatTotal
            var pvApres = cible.pv
            var pvFinaux = pvAvant - pvApres
            println("=== ${this.nom} inflige $pvFinaux dégâts à ${cible.nom}")
        }

    }

    fun renommer(){
        println("Voulez vous renommer (O ou N) -> ${this.nom} : ?")
        var réponse : String = readln()
        if(réponse == "O" || réponse == "o"){
            println("Saisir le nouveau nom ici -> ")
            var nvnom : String = readln()
            this.nom = nvnom
        }
        else{
            this.nom = this.nom
        }




    }
    fun afficherDetails() {

        // 1. Obtenir l'art ASCII
        val art = espece.afficheArt(true)

        // 2. Découper l'art en plusieurs lignes
        val artLines = art.lines()

        // 3. Construire la liste des détails
        val details = listOf(
            "",
            "Nom : $nom",
            "Espèce : ${espece.nom}",
            "Type : ${espece.type}",
            "Niveau : $niveau",
            "PV : $pv / $pvMax",
            "Attaque : $attaque",
            "Défense : $defense",
            "Vitesse : $vitesse",
            "Attaque spéciale : $attaqueSpe",
            "Défense spéciale : $defenseSpe",
            "Expérience : $exp"
        )

        // 4. Largeur de la plus grande ligne de l'art
        val maxArtWidth = artLines.maxOf { it.length }

        // 5. Nombre total de lignes à afficher
        val maxLines = maxOf(artLines.size, details.size)

        // 6. Parcourir toutes les lignes
        for (i in 0 until maxLines) {

            // Si l'art possède encore une ligne, on la prend
            val artLine =
                if (i < artLines.size) {
                    artLines[i]
                } else {
                    ""
                }

            // Si les détails possèdent encore une ligne, on la prend
            val detailLine =
                if (i < details.size) {
                    details[i]
                } else {
                    ""
                }

            // 7. Ajouter des espaces après l'art
            val paddedArt = artLine.padEnd(maxArtWidth + 4)

            // 8. Afficher l'art + les détails
            println(paddedArt + detailLine)
        }
    }



}




