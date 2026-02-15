# Projet_Go

Projet réalisé lors de ma deuxième année de BUT Informatique

Règles du jeu :
-
Le jeu de Go est un jeu de stratégie traditionnel qui se joue généralement à deux.  
Le plateau de jeu, appelé **goban**, est constitué dans sa version standard de **19 lignes par 19 colonnes**.

Les joueurs placent alternativement des pierres noires ou blanches sur les intersections du goban.
Le joueur noir commence la partie.  
L’objectif est de **contrôler le plus grand territoire possible** en capturant les pierres adverses et en délimitant des zones inaccessibles à l’ennemi.

Le Go est reconnu pour sa **profondeur stratégique**, offrant une grande richesse de jeu malgré des règles simples.

Concept :
-
Notre projet consistait à développer une application permettant de jouer une partie de Go, en s’inspirant de l’interface GNU Go et de son protocole de communication **GTP (Go Text Protocol)**.

Nous avons également suivi diverses contraintes de développement, en particulier les **principes SOLID**.  
En plus du jeu de base, l’un des objectifs était d’implémenter des joueurs non humains, permettant de jouer contre un robot.

Fonctionnalités :
-
Toutes les règles du jeu n’ont pas été implémentées, mais voici celles qui ont été gérées :
- Plateau personnalisable : taille modifiable
- Groupes/chaînes : des pierres adjacentes forment un groupe
- Captures : un groupe encerclé par l’adversaire est retiré du plateau
- Score/prisonniers : les pierres capturées sont comptabilisées
- Suicide interdit : il est interdit de jouer sur une intersection sans liberté (une liberté = une intersection vide adjacente)
- Règle du Ko (simplifiée) : interdiction de rejouer sur une position précédente
- Passer son tour : les joueurs peuvent passer
- Fin de partie : la partie se termine si les deux joueurs passent consécutivement

En plus du jeu de base :
- Possibilité de jouer avec différents types de joueurs : **Console ou Aléatoire**

Et celles non gérées :
- Hoshi : points de handicap non implémentés
- Calcul de score final : le score basé sur les territoires, les prisonniers, les handicaps et le **komi** n’est pas encore intégré

Ces dernières règles ont été exclues volontairement du projet, car elles sont dépendantes de la taille du plateau, ce que notre cahier des charges nous demandait de ne pas prendre en compte.

Exemple :
-
*boardsize 6*  
$\textcolor{gray}{\textsf{=}}$  
*showboard*  
$\textcolor{gray}{\textsf{=}}$  
&nbsp;&nbsp;&nbsp; A &nbsp; B &nbsp; C &nbsp; D &nbsp; E &nbsp; F  
6 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 6  
5 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 5  
4 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 4  
3 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 3  
2 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 2  
1 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 1  
&nbsp;&nbsp;&nbsp; A &nbsp; B &nbsp; C &nbsp; D &nbsp; E &nbsp; F  
Black (X) has 0 stones  
White (O) has 0 stones  

*player white random*  
$\textcolor{gray}{\textsf{=}}$  
*play black g3*  
$\textcolor{red}{\textsf{? invalid coordinate}}$  
*play black b1*  
$\textcolor{gray}{\textsf{=}}$  
WHITE : A1  

&nbsp;&nbsp;&nbsp; A &nbsp; B &nbsp; C &nbsp; D &nbsp; E &nbsp; F  
6 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 6  
5 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 5  
4 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 4  
3 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 3  
2 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 2  
1 &nbsp;O &nbsp; X &nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 1  
&nbsp;&nbsp;&nbsp; A &nbsp; B &nbsp; C &nbsp; D &nbsp; E &nbsp; F  
Black (X) has 0 stones  
White (O) has 0 stones  

*play black a1*  
$\textcolor{red}{\textsf{? illegal move}}$  
*play black a2*  
$\textcolor{gray}{\textsf{=}}$  
WHITE : D4  

&nbsp;&nbsp;&nbsp; A &nbsp; B &nbsp; C &nbsp; D &nbsp; E &nbsp; F  
6 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 6  
5 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 5  
4 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; O&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 4  
3 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 3  
2 &nbsp; X &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp;. &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 2  
1 &nbsp; . &nbsp; X &nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 1  
&nbsp;&nbsp;&nbsp; A &nbsp; B &nbsp; C &nbsp; D &nbsp; E &nbsp; F  
Black (X) has 1 stones  
White (O) has 0 stones  

En Pratique :
-
Le jeu est fonctionnel, mais jouer en ligne de commande reste fastidieux : il faut entrer chaque commande manuellement, ce qui ralentit fortement le déroulement d’une partie (qui dure normalement 30 minutes).

L’ajout de différents types de joueurs nous a poussés à écrire un code plus modulaire, pour faciliter l’ajout de futurs comportements.  
L’**intelligence du robot**, actuellement très limitée, se contente de jouer aléatoirement sur une case valide.

Cette absence de stratégie rend les parties peu intéressantes contre un humain, qui gagne facilement.  
Des types de joueurs plus avancés pourraient être envisagés, par exemple : IAeasy, IAnormal, IAhard.

Conclusion :
-
Ce projet Java de deuxième année a été réalisé en équipe, avec une organisation plutôt difficile.  
L’objectif principal était d’appliquer des principes de conception (SOLID) et de s’appuyer sur la documentation d’un outil existant (GNU Go).  
Ce fut aussi une première introduction aux tests unitaires et à l’écriture de code maintenable.
