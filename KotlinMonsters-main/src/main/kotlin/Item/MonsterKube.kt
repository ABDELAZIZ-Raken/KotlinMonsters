package org.example.Item

import org.example.dresseur.Entraineur
import org.example.joueur
import org.example.monstre.IndividuMonstre




class MonsterKube(
    id: Int,
    nom: String,
    description: String,
    var chanceCapture: Double,
    ) : Item(id, nom, description), Utilisable {


        override fun utiliser(cible: IndividuMonstre, joueur: Entraineur): Boolean {

            println("Vous lancez le Monster Kube !")

            // Cas 1 : déjà capturé
            if (cible.entraineur != null) {
                println("Le monstre ne peut pas être capturé !")
                return false
            }

            // Cas 2 : tirage
            val nbAleatoire = (0..100).random()

            if (nbAleatoire >= chanceCapture) {
                println("Dommage... Le Kube n'a pas pu capturer le monstre !")
                return false
            }

            // Cas 3 : succès
            println("Parfait ! Le monstre est capturé !")
            println("Maintenant, donnez un nom à votre monstre : ")
            val nouveauNom = readln()

            if (nouveauNom.isNotEmpty()) {
                cible.nom = nouveauNom
            }

            if (joueur.equipeMonstre.size >= 6) {
                joueur.boiteMonstre.add(cible)
                println("=== ${cible.nom} a bien été ajouté à votre boîte Monstre !")
            } else {
                joueur.equipeMonstre.add(cible)
                println("=== Veuillez faire place à ${cible.nom}, il intègre votre équipe ! 💫")
            }

            cible.entraineur = joueur
            return true
        }
    }