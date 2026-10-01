package org.example

import org.example.Item.Badge
import org.example.Item.MonsterKube
import org.example.dresseur.Entraineur
import org.example.monde.Zone
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre


// Création des classes

var joueur = Entraineur(1, "Sacha", 100)
var rival = Entraineur(2, "Regis", 200)

// Création espèce monsters

var espece_Springleaf = EspeceMonstre(1, "Springleaf", "Graine", 9, 11, 10, 12, 14, 60, 6.5, 9.0, 8.0, 7.0, 10.0, 34.0, "Petit monstre espiègle rond comme une graine, adore le soleil.", "Sa feuille sur la tête indique son humeur.", "Curieux, amical, timide")
var espece_Flamkip = EspeceMonstre(4, "Flamkip", "Animal", 12, 8, 13, 16, 7, 50, 10.0, 5.5, 9.5, 9.5, 6.5, 22.0, "Petit animal entouré de flammes, déteste le froid.", "Sa flamme change d'intensité selon son énergie.", "Impulsif, joueur, loyal")
var espece_Aquamy = EspeceMonstre(7, "Aquamy", "Meteo", 10, 11, 9, 14, 14, 55, 9.0, 10.0, 7.5, 12.0, 12.0, 27.0, "Créature vaporeuse semblable à un nuage, produit des gouttes pures.", "Fait baisser la température en s'endormant.", "Calme, rêveur, mystérieux")
var espece_Laoumi = EspeceMonstre(8, "Laoumi", "Animal", 11, 10, 9, 8, 11, 58, 11.0, 8.0, 7.0, 6.0, 11.5, 23.0, "Petit ourson au pelage soyeux, aime se tenir debout.", "Son grognement est mignon mais il protège ses amis.", "Affectueux, protecteur, gourmand")
var espece_Bugsyface = EspeceMonstre(10, "Bugsyface", "Insecte", 10, 13, 8, 7, 13, 45, 7.0, 11.0, 6.5, 8.0, 11.5, 21.0, "Insecte à carapace luisante, se déplace par bonds et vibre des antennes.", "Sa carapace devient plus dure après chaque mue.", "Travailleur, sociable, infatigable")
var espece_Galum = EspeceMonstre(13, "Galum", "Minéral", 12, 15, 6, 8, 12, 55, 9.0, 13.0, 4.0, 6.5, 10.5, 13.0, "Golem ancien de pierre, yeux lumineux en garde.", "Peut rester immobile des heures comme une statue.", "Sérieux, stoïque, fiable")

// Création de route

var route1 = Zone(1,"Melun",150, especesMonstres = mutableListOf(espece_Springleaf), null, null)
var route2 = Zone(1,"Plaine Belaid",150, especesMonstres = mutableListOf(espece_Aquamy), null, null)

// Créer 3 individus qui sont nos 3 starters

val monstre1 = IndividuMonstre(1, "springleaf", espece_Springleaf, joueur, 1500.0)
val monstre2 = IndividuMonstre(2, "flamkip",    espece_Flamkip,    rival,  1350.0)
val monstre3 = IndividuMonstre(3, "aquamy",     espece_Aquamy,     joueur, 1500.0)

// Création de Kube

val kube = MonsterKube(
    id = 1,
    nom = "Kube Rouge",
    description = "Permet de capturer un monstre",
    chanceCapture = 50.0
)

val monstreSauvage = IndividuMonstre(
    id = 99,
    nom = "Springleaf sauvage",
    espece = espece_Springleaf,
    entraineur = null,
    expInit = 0.0
)

fun main() {

    route1.zoneSuivante = route2
    route2.zonePrecedente = route1



    println("=== Test : on ajoute 250 exp ===")
    monstre1.exp += 2500.0
    println()

    println("=== État après gain d'exp ===")
    println("Niveau : ${monstre1.niveau}")
    println("Attaque : ${monstre1.attaque}")
    println("PV : ${monstre1.pv} / ${monstre1.pvMax}")
    println()

    // --- Test 2 : vérifier que pv ne descend pas en dessous de 0 ---
    println("=== Test : pv = -50 ===")
    monstre1.pv = -50
    println("PV : ${monstre1.pv}  (attendu : 0)")
    println()

    // --- Test 3 : vérifier que pv ne monte pas au-dessus de pvMax ---
    println("=== Test : pv = 99999 ===")
    monstre1.pv = 99999
    println("PV : ${monstre1.pv}  (attendu : ${monstre1.pvMax})")



    // --- Test 4 : vérifier que les dégâts infligés sont les bons ---

    println("=== Test : dégâts = 150000 ===")
    monstre1.attaque += 15000
    println(monstre2.pv)
    println(monstre1.attaquer(monstre2))
    println(monstre2.pv)
    println("PV : ${monstre2.pv}  (attendu : 0)")
    println("")


    // --- Test 5 : vérifier que le changement de nom est effectif ---

    monstre1.renommer()
    println("=== Nouveau nom de votre monstre -> ${monstre1.nom} ===")

    // --- Test 6 : vérifier que la fonction afficherDetails() fonctionne correctement ---

    monstre3.afficherDetails()

    // --- Test 7 : vérifier que la fonction badge + item fonctionne correctement ---

    val badge = Badge(1, "Badge Roche", "Obtenu après avoir battu racaillou")
    println(badge.id)          // 1
    println(badge.nom)         // Badge Spécial roche
    println(badge.descriptions) // Obtenu après avoir battu racaillou
    println("")

    // --- Test 8 : fonction MonsterKube vérification  ---
    println("=== Test : capture Monstre ===")
    println("Avant : boîte de ${joueur.nom} = ${joueur.boiteMonstre.size} monstres")
    println("Taille équipe : équipe de ${joueur.nom} = ${joueur.equipeMonstre.size} monstres")

    val resultat = kube.utiliser(monstreSauvage,joueur)

    println("Résultat : $resultat")
    println("Après : boîte = ${joueur.boiteMonstre.size}")
    println("Taille : équipe = ${joueur.equipeMonstre.size}")
    println("Dresseur du monstre : ${monstreSauvage.entraineur?.nom}")

}









