package org.example.monstre

import java.io.File

/**
 * Classe EspeceMonstre
 *
 * @property id : Identifiant unique de l'espèce.
 * @property nom : Nom de l'espèce du monstre.
 * @property type : Catégorie ou élément (ex: Feu, Eau) du monstre.
 * @property baseAttaque : Valeur de base définissant la puissance des attaques physiques.
 * @property baseDefense : Valeur de base définissant la résistance aux attaques physiques.
 * @property baseVitesse : Valeur de base déterminant l'ordre d'action en combat.
 * @property baseAttaqueSpe : Valeur de base définissant la puissance des attaques spéciales/magiques.
 * @property baseDefenseSpe : Valeur de base définissant la résistance aux attaques spéciales/magiques.
 * @property basePv : Valeur de base définissant les points de vie (santé) de l'espèce.
 * @property modAttaque : Multiplicateur ou bonus de croissance pour la statistique d'attaque.
 * @property modDefense : Multiplicateur ou bonus de croissance pour la statistique de défense.
 * @property modVitesse : Multiplicateur ou bonus de croissance pour la statistique de vitesse.
 * @property modAttaqueSpe : Multiplicateur ou bonus de croissance pour la statistique d'attaque spéciale.
 * @property modDefenseSpe : Multiplicateur ou bonus de croissance pour la statistique de défense spéciale.
 * @property modPv : Multiplicateur ou bonus de croissance pour les points de vie.
 * @property description : Texte informatif décrivant l'apparence ou l'histoire du monstre.
 * @property particularites : Traits uniques ou mécaniques spécifiques liés à cette espèce.
 * @property caractères : Nature, tempérament ou comportement général de l'espèce.
 */

class EspeceMonstre(
    var id : Int,
    var nom: String,
    var type: String,
    val baseAttaque: Int,
    val baseDefense: Int,
    val baseVitesse: Int,
    val baseAttaqueSpe: Int,
    val baseDefenseSpe: Int,
    val basePv: Int,
    val modAttaque: Double,
    val modDefense: Double,
    val modVitesse: Double,
    val modAttaqueSpe: Double,
    val modDefenseSpe: Double,
    val modPv: Double,
    val description: String = "",
    val particularites: String = "",
    val caractères: String = "",
    )

{
    /**
     * Affiche la représentation artistique ASCII du monstre.
     *
     * @param deFace Détermine si l'art affiché est de face (true) ou de dos (false).
     *               La valeur par défaut est true.
     * @return Une chaîne de caractères contenant l'art ASCII du monstre avec les codes couleur ANSI.
     *         L'art est lu à partir d'un fichier texte dans le dossier resources/art.
     */

    fun afficheArt(deFace: Boolean=true): String{
        val nomFichier = if(deFace) "front" else "back";
        val art= File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
        val safeArt = art.replace("/", "∕")
        return safeArt.replace("\\u001B", "\u001B")
    }


}

