ProJet 7 Bibliotheque 

bibliotheque Projet 7 OC Gestion d'une bibliothèque d'une grande ville



Description :



bibliotheque est composé de 3 modules :

                Un Module : microservice-clientui
                Un Module : microservice-livre
                Un Module : microservice-Utilisateur
                
                
Api Gateway :

                Zuul : zuul-server
                
                
                
Edge services utilisés :

                Spring-Cloud-Config : config-server
                Eureka : Eureka-server
                Zipkin
                
                
Lancer l'application :

                mvn spring-boot:run
                

                
                
Etapes :

 Ordre de déploiement
 
 créer les bases de données(PostgreSQL):
 
          -book: pour les emprunts, les emails, les livres
          -utilisateur: pour les utilisateur_livre et les utilisateur_livre_user_role_list
          
          
  Import de base Postgre avec pgAdmin
          
        1. Se connecter au serveur PGSQL avec pgAdmin

        2. Créer une base de données vierge (clic droit, Create, Database) sauf si elle existe déjà.

        3. Faire un clic droit sur la base de données, Restore.

        4. A l’onglet General, dans le champ Filename, charger le fichier :
        
        la base pour le microservice le nom du fichier est "dump_utilisateur.sql"
        
        la base pour le microservice le nom du fichier est "dump_book.sql"

        Si le fichier n’apparait pas dans la liste, modifier le Format de fichiers recherché.

        5. Cliquer sur Restore pour importer la base. Un message « Successfully completed » indique que l’opération s’est bien déroulée.



Etape 1 :

    Zipkin-server
   
   
Etape 2 :

    config-server
    eureka-server
   
   
Etape 3 :
   
    zuul-server
   
   
Etape 4 :

   1 microservice-utilisateur
   
       Import de la base de donnée (opération non nécessaire):   
        psql -U postgres utilisateur < /schema_utilisateur.sql
        psql -U postgres utilisateur < /data_utilisateur.sql
        
        
   2  microservice-livre
   
       import de la base de donnée (opération non nécessaire):
        psql -U postgres book < /schema_book.sql
        psql -U postgres book < /data_book.sql

   3  microservice-clientui
   
comment générer la javadoc :

       - Dans le panneau de contrôle de maven en lancant la commande: javadoc:javadoc
       - Dans Intellij via le menu Tools puis Generate Javadoc
