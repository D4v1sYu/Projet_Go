# Projet_Go

Règles du jeu :
-
Le jeu de go est un jeu de stratégie classique, se pratiquant généralement à deux joueurs.
Le goban, plateau de jeu, comporte 19 lignes sur 19 colonnes dans les règles de base.  
Les participants utilisent des pierres noires et blanches pour marquer leur territoire en plaçant alternativement leurs pierres sur les intersections du goban.
Le joueur noir commence la partie en posant une pierre.  
L'objectif est de contrôler le plus grand territoire possible à la fin du jeu, en capturant les pierres adverses et en établissant des zones incontestées sur le plateau. 
Le go est réputé pour sa profondeur stratégique et sa complexité, offrant des parties vraiment captivantes.

Concept :
-
Notre projet consiste à développer et tester un programme permettant de jouer une partie de go.
En suivant l'exemple de l'interface GNU Go et de son protocole GTP (Go Text Protocol).  
Mais aussi en respectant au mieux divers contraintes de programmation comme les principes SOLID.
En plus du développement du jeu, les joueurs ne sont pas forcément humain et l'idée est de pouvoir jouer contre un robot.

Fonctionalités :
-
Toutes les contraintes/règles du jeu n'ont pas forcément été implémenté, voici la liste des contraintes gérées :
- Taille du plateau modifiable
- Territoire/Chaine/Groupe : quand plusieurs pierres sont posées l'une à côté de l'autre, elles forment un groupe
- Capture : lorsque qu'une pierre ou un groupe est entouré par les pierres énnemis, ils récupèrent le territoire
- Score/Prisonniers : après une capture, le nombre de pierre capturé est enregistré dans les scores des joueurs
- Suicide : il n'est pas possible de poser une pierre dans un territoire énnemi
- Ko (simplifié) : pas possible de rejouer à la même position
- Skip : pouvoir passer son tour
- Fin de partie : lorsque les deux joueurs ne peuvent plus jouer c'est fini

En plus du jeu de base :
- Joueurs : Console ou Aléatoire

Et celles non gérées :
- Hoshi : points particuliers où sont posées des pierres de "handicap" si le niveau entre les deux joueurs est trop différent
- Calcul des scores : en fin de partie, il faudrait attribuer des points selon le nombre de prisonniers, les points de handicap et appliquer la règle du Komi

Ces contraintes là sont proportionnelles à la taille du plateau et notre sujet nous indique de ne pas les considérer.

Exemple :
-
*boardsize 6*  
$\textcolor{gray}{\textsf{=}}$  
*showboard*  
$\textcolor{gray}{\textsf{=}}$  
&nbsp;&nbsp;&nbsp; A &nbsp; B &nbsp; C &nbsp; D &nbsp; E &nbsp; F  
6 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 6  
5 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 5  
4 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 4  &nbsp;&nbsp;&nbsp;&nbsp; Black (X) has 0 stones  
3 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 3  &nbsp;&nbsp;&nbsp;&nbsp; White (O) has 0 stones  
2 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 2  
1 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 1  
&nbsp;&nbsp;&nbsp; A &nbsp; B &nbsp; C &nbsp; D &nbsp; E &nbsp; F  

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
4 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 4  &nbsp;&nbsp;&nbsp;&nbsp; Black (X) has 0 stones  
3 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 3  &nbsp;&nbsp;&nbsp;&nbsp; White (O) has 0 stones  
2 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 2  
1 &nbsp;O &nbsp; X &nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 1  
&nbsp;&nbsp;&nbsp; A &nbsp; B &nbsp; C &nbsp; D &nbsp; E &nbsp; F  

*play black a1*  
$\textcolor{red}{\textsf{? illegal move}}$  
*play black a2*  
$\textcolor{gray}{\textsf{=}}$  
WHITE : D4  

&nbsp;&nbsp;&nbsp; A &nbsp; B &nbsp; C &nbsp; D &nbsp; E &nbsp; F  
6 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 6  
5 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 5  
4 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; O&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 4  &nbsp;&nbsp;&nbsp;&nbsp; Black (X) has 1 stones  
3 &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 3  &nbsp;&nbsp;&nbsp;&nbsp; White (O) has 0 stones  
2 &nbsp; X &nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp;&nbsp;. &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 2  
1 &nbsp; . &nbsp; X &nbsp; . &nbsp;&nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp;&nbsp; . &nbsp; 1  
&nbsp;&nbsp;&nbsp; A &nbsp; B &nbsp; C &nbsp; D &nbsp; E &nbsp; F  

En Pratique :
-
Le jeu est fonctionnel, mais y jouer en ligne de commande est très fastidieux, on se retrouve à taper chaque commande une par une pour pouvoir faire une vraie partie (qui à la base dure 30min).
L'ajout de différent type de joueurs nous poussent à devoir programmer de manière plus optimal pour qu'à l'avenir on ajoute des types en plus.

Conclusion :
-
C'est un projet Java que j'ai pu réalisé en équipe (difficilement au vu de la mauvaise organisation).
Mais l'objectif principal est d'apprendre à suivre plusieurs principes de programmation (SOLID) et en essayant de comprendre la documentation d'un jeu fonctionnel (GNUGo).
Nous avons aussi introduit la réalisation de tests lors de ce projet, il faut que je continue d'apprendre et suivre une bonne méthodologie de programmation.
