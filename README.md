Mastermind
==========

![](mastermind.jpg)

L'objectif de ce projet est de concevoir une application permettant à un joueur de jouer au mastermind contre l'ordinateur.

# Mastermind Game in Java

Bienvenue dans notre projet de jeu Mastermind réalisé en Java. Ce jeu classique de réflexion vous met au défi de deviner une combinaison secrète de couleurs. Le projet a été développé avec Ryan Gourdon dans le cadre de notre cursus universitaire en BUT Informatique.

## Comment jouer

Pour installer le jeu et commencer à jouer, suivez ce [lien](./INSTALL.md) pour consulter les étapes d'installation.

## Structure du Projet (Modèle-Vue-Contrôleur - MVC)

Ce projet suit le modèle architectural Modèle-Vue-Contrôleur (MVC), séparant les composants de la manière suivante :

- **Modèle (package `model`):** Contient les classes qui représentent les données du jeu.
  - `Partie`: Gère les aspects globaux du jeu, tels que le score, le nombre de manches, etc.
  - `Manche`: Représente une manche individuelle du jeu, gérant la combinaison secrète et les interactions spécifiques à la manche.

- **Contrôleur (package `controller`):** Gère la logique du jeu.
  - `PartieController`: Contrôleur principal qui gère les actions globales du jeu.
  - `MancheController`: Contrôleur spécifique à une manche qui gère les actions liées à une manche individuelle.

- **Vue (package `view`):** Gère l'interface utilisateur.
  - `GameWindow`: Fenêtre principale affichant le plateau de jeu.
  - `StartWindow`: Fenêtre de démarrage pour configurer le jeu.
  - `EndWindow`: Fenêtre de fin de jeu.

- **Autres (package `utils`):** Contient des utilitaires pour le projet.
  - `ColorSwap`: Classe utilitaire pour gérer les couleurs.

## Comment Contribuer

Si vous souhaitez contribuer à l'amélioration du jeu, vous pouvez suivre ces étapes :

1. Fork ce projet.
2. Créez une branche pour votre fonctionnalité (`git checkout -b amelioration-jeu`).
3. Effectuez vos modifications et ajoutez-les (`git add .`).
4. Committez vos modifications (`git commit -m 'Ajout d'une fonctionnalité'`).
5. Poussez la branche (`git push origin amelioration-jeu`).
6. Ouvrez une Pull Request.

Nous sommes ouverts à toutes les suggestions et contributions pour rendre ce jeu encore meilleur !

## Remerciements

Un grand merci à Ryan Gourdon qui a été mon partenaire de projet exceptionnel tout au long du développement du Mastermind en Java. Cette collaboration a été enrichissante et a contribué de manière significative à la réussite de ce projet. Nous sommes reconnaissants de l'effort et de l'engagement de chacun, et nous espérons que vous apprécierez jouer à notre version du Mastermind en Java !

Joyeux codage à tous ! 🚀
