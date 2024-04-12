Voor het gemak is zowel de backend als frontend applicatie hier te vinden.

Er word vanuit gegaan dat dependencies zoals maven, java (21), .net etc al geinstalleerd zijn.

Voor het runnen van de backend : 

-Open een console en ga naar ./backend

-run : `mvn install`

-run : `mvn package`

-Ga naar ./target, De jar is nu te vinden onder ./backend/target/Trivia-0.0.1-SNAPSHOT.jar

-Run : `java -jar Trivia-0.0.1-SNAPSHOT.jar`

Voor het runnen van de frontend :

-Open een console en ga naar ./frontend

-Run `npm install`

-Run `npm run dev`

Ga naar de website http://localhost:5173/ (of andere port indien aangegeven) voor de applicatie
