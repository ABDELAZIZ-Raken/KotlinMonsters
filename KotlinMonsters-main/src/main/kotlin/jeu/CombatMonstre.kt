package org.example.jeu

import org.example.joueur
import org.example.monstre.IndividuMonstre

class CombatMonstre(
    var monstreJoueur: IndividuMonstre,
    var monstreSauvage: IndividuMonstre
) {
    var round: Int = 1

    /**
     * Vérifie si le joueur a perdu le combat.
     *
     * Condition de défaite :
     * - Aucun monstre de l'équipe du joueur n'a de PV > 0.
     *
     * @return `true` si le joueur a perdu, sinon `false`.
     */
    fun gameOver(): Boolean {

        var perdujoueur = true

        while (perdujoueur == false) {
            for (monstre in joueur.equipeMonstre) {
                if (monstre.pv < 0) {
                    println("GameOver... Tu feras mieux la prochaine fois.")
                    perdujoueur = false
                    return perdujoueur
                }

            }

        }

        return perdujoueur
    }

    /**
     * Indique si le joueur a gagné le combat.
     *
     * Il y a 2 façon de gagner le combat :
     *
     *     Capturer le monstre sauvage
     *
     *     Ou amener les pv du monstre sauvage à 0
     *
     * Le monstre du joueur gagne de l'expérience seulement dans le deuxième cas.
     *
     * La méthode retourne un booléen : vrai si le joueur a gagné, faux sinon.
     */
    fun joueurgagne(): Boolean{

        if(monstreSauvage.pv <= 0){
            println("${joueur.nom}")
            var gainExp = monstreSauvage.exp * 0.20
            gainExp += monstreSauvage.exp
            println("${monstreJoueur.nom} gagne ${gainExp} exp")
            return true
        }
        else{
            if(monstreSauvage.entraineur == joueur){

            }

        }




    }

}