# Description

Le sujet du projet se trouve dans le fichier sujet.pdf. 

L'application est un système de messagerie classique (Souscripteur, publieur, courtier) en programmation par composants. 

# Utilisation 

Pour lancer le projet dans un cadre multi jvm, ouvrir quatre terminaux. 
Placer tout les terminaux à la racine du projet. 

Voici les commandes à entrer. Une par terminal. Vous devez posseder java >= 8. 

```
./start-cyclicbarrier
```

```
./start-gregistry
```

```
./start-dcvm jvm1
```

```
./start-dcvm jvm2
```

Six composants sont alors créer, trois sur la jvm1 et trois sur la jvm2. Chaque jvm possède un publieur, un souscripteur et un coutier. Les courtiers communiquent etre eux pour transférer les messages entre jvms. 