# Programmation des cartes à puce – JavaCard
## TP1 - Environnement de développement JavaCard (V2.0)

### I.1 Outils logiciels nécessaires :
Avant de commencer à télécharger les différentes ressources nécessaires, il est impératif de
vérifier le type de votre système d’exploitation : click droit sur le poste de travail puis
sélectionner ‘Propriétés’.
#### Q1. Identifiez votre système d’exploitation ainsi que son type.
![image](https://github.com/user-attachments/assets/4e4801c5-90ab-4172-9a21-28c67acc1ca3)

#### Q2. Le Java Card Development Kit 2.2.2 : 
archive java_card_kit-2_2_2-windows.zip à partir du site d’Oracle : http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-javame-419430.html
![image](https://github.com/user-attachments/assets/6c4b1a34-b2f4-4550-af4d-8483f2a1c34d)

#### Q3. Télécharger un environnement standard édition de Java (Java SE) qui contient non
seulement un Java Runtime Environnement (JRE) pour pouvoir tourner des applications java
mais également un Java Developpement Kit pour pouvoir compiler vos codes sources. Nous
proposons de télécharger Java SE 8. link : http://www.oracle.com/
![image](https://github.com/user-attachments/assets/2b3f602d-da7a-46ea-bdd2-c6e4deb1c5e5)
![image](https://github.com/user-attachments/assets/7336ed4b-8550-4d19-9da4-8231e3404805)
![image](https://github.com/user-attachments/assets/60d6358d-15c3-49e7-b859-9f2dedb583d6)
![image](https://github.com/user-attachments/assets/4d5d4996-92bd-4664-b7aa-5c2eb089962b)
![image](https://github.com/user-attachments/assets/88131987-b3f9-431b-b51e-e304a52a505a)

#### Q4. Suivant le type de votre système, télécharger un environnement de développement java
pour pouvoir éditer votre code source. Nous proposons de télécharger ECLIPSE IDE. link :
www.eclipse.org
![image](https://github.com/user-attachments/assets/7e9fe404-af28-4995-a4a0-0aa814d6d50c)

#### Q5. Le plugin d'intégration Eclipse-JCDE version 0.2 : archive eclipse-jcde-0.2.zip à partir
du site de téléchargement sourceforge :
http://sourceforge.net/projects/eclipse-jcde/
Pour procéder à l'installation de l'environnement de développement, les outils logiciels
suivant sont nécessaires :
![image](https://github.com/user-attachments/assets/abc97684-2f6b-4550-8a12-03d1579a7770)

### I.2 Instructions d'installation :
####I.2.1 Installation d’Eclipse sous windows :
Pour centraliser nos fichiers, nous allons créer un seul et unique répertoire de travail
directement sous la racine qu’on nommera C:\Eclipse .
![image](https://github.com/user-attachments/assets/afb234bb-8fbd-4222-bafb-5aa32b6bdcef)
##### a. Installation du JDK :
Eclipse ne contient pas par défaut un compilateur Java. Ainsi, pour développer des
programmes en Java, il faut installer au préalable un kit de développement Java sur votre
machine (on utilisera JDK 1.7).
Pour cela télécharger et installer l’exécutable jdk-7u40-windows-i586.exe à partir du lien
suivant :
http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html
![image](https://github.com/user-attachments/assets/80f324df-425f-4407-86df-fab19d6e69e1)
![image](https://github.com/user-attachments/assets/054b5bb9-977d-42fd-b0db-070c16f460e2)
![image](https://github.com/user-attachments/assets/4c4cafff-0de1-473d-b0d8-0e8059d585dd)
![image](https://github.com/user-attachments/assets/95b32011-8678-4d37-bbdf-d9656769d0eb)
Remarque :
Il ne faut pas confondre le JDK (Java Development Kit) avec le JRE (Java Runtime
Environment) :
✓ la JRE ne contient que les outils nécessaires pour exécuter des applications Java ;
✓ le JDK permet également d’exécuter des applications Java mais il permet en plus de
compiler du code source java pour en faire des applications compréhensible par la JRE, le
JRE est inclut dans le JDK.
Une fois l'installation terminée, il faut ajouter la variable d’environnement
JAVA_HOME, contenant le chemin d'accès au JDK, dans la définition de la variable Path.
Pour cela :
1. Créer un nouveau répertoire C:\Java , puis recopier tout le contenu du répertoire
C:\Program Files \Java\ sous ce répertoire C:\Java .
![image](https://github.com/user-attachments/assets/01a1d6ed-d9c3-4857-915b-6ce5edb6dd45)
2. Cliquer avec le bouton droit sur l'icône du Poste de travail, puis Propriétés >
Paramètres système Avancés > Variables d'environnement
![image](https://github.com/user-attachments/assets/63a3ed96-a25e-43e7-9ea6-bc233fd083c1)
3. Créer une nouvelle variable d’environnement (coté utilisateur) nommée JAVA_HOME contenant le chemin C:\ Java\jdk1.8
![image](https://github.com/user-attachments/assets/cf238970-ea89-4017-b2c1-d197e9dd0109)
4. Modifier la variable Path (coté système) en ajoutant vers sa fin :
%JAVA_HOME%\bin ;
![image](https://github.com/user-attachments/assets/742311b2-f538-4ac1-b27a-440cb145e7b3)
![image](https://github.com/user-attachments/assets/78d9706e-462e-45e2-acb7-d1a0ddfdaa41)
5. Pour vérifier le bon fonctionnement de votre JDK, il suffit de taper la commande « java –version » sous la console DOS :
![image](https://github.com/user-attachments/assets/d6cb5928-9a8b-46c3-b910-0df337110cb2)
   b. Installation de l’IDE - Eclipse :
L'installation consiste simplement à décompresser l’archive eclipse-SDK-4.3-win32.zip téléchargée dans le répertoire C:\Eclipse .Cela crée un sous-dossier "eclipse", pour le lancer, double cliquez sur "eclipse.exe".
![image](https://github.com/user-attachments/assets/9f5bf40a-46ae-405d-a30d-291282914563)
![image](https://github.com/user-attachments/assets/17e1c480-37fb-4371-9537-aa8ca01e155f)
Remarque :
Eclipse vous demande de choisir un Workspace (Espace de travail). Notre Workspace sera par
défaut le répertoire C:\Eclipse\workspace :
![image](https://github.com/user-attachments/assets/3f1648e3-03ac-4546-b272-2b4cba2afeaf)
c. L’indispensable « Hello Word »
Comme initiation, nous allons créer une petite application Java avec l’IDE Eclipse qui va
afficher à l'écran "Hello, World!":
1. Création d’un nouveau projet sous Eclipse:
▪ Lancer Eclipse, dans le menu File, faire New > Java Project, puis indiquer le nom du
projet, par exemple hello.
![image](https://github.com/user-attachments/assets/468a7f52-5402-40dd-92fe-181d9ced5269)
▪ Cliquer alors sur le bouton Finish, Le projet ainsi créé apparaît dans la fenêtre principale :
![image](https://github.com/user-attachments/assets/062f7d18-8e7f-4acb-acd2-c6a6a2289826)
2. Création d’une nouvelle classe :
▪ Cliquer avec le bouton droit sur le projet hello, on sélectionne New -> Class :
![image](https://github.com/user-attachments/assets/195a93a6-5f72-40a3-ac77-c5184af31d94)
▪ Dans la fenêtre qui s'ouvre, on indique le nom de la classe, Hello, et on coche la case
indiquant que l'on souhaite qu'elle contienne une méthode main :
![image](https://github.com/user-attachments/assets/73b605b6-83c9-4535-a22b-b49280c479b8)
▪ Cliquer alors sur le bouton Finish, Eclipse crée le squelette de la classe Hello :
![image](https://github.com/user-attachments/assets/762b37cd-b33b-48eb-9edb-8828fb15022c)
▪ Il ne reste qu'à compléter la méthode main, en ajoutant System.out.println ("Hello World!");
![image](https://github.com/user-attachments/assets/ec123cde-98d4-4869-a3eb-2b097f2f29cc)
▪ Pour tester notre programme, cliquez droit sur la classe Hello.java, puis Run As -> Java
Application :
![image](https://github.com/user-attachments/assets/79bf0ffb-f132-40c8-8eb7-e35e8c0a16c2)
![image](https://github.com/user-attachments/assets/90526f70-1df6-484e-b590-7fa100ccb445)
![image](https://github.com/user-attachments/assets/c7a6b55e-bc3a-42c9-9d09-52b56f4c1823)








 










