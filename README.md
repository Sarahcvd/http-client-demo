![Java CI with Maven](https://github.com/Sarahcvd/http-client-demo/workflows/Java%20CI%20with%20Maven/badge.svg)
Prosjektbeskrivelse:

Dette prosjektet består av en HTTP-server og klient. Vi har også lagd tester for å se at planlagd funksjonalitet kjøres korrekt (tester mot: server, klient og querystring).
Hovedklassen i prosjektet er server-klassen, denne kjøres for å starte servern (Forbindelse åpnes på port 8080 - via localhost).
Klient-klassen brukes for å "snakke" med servern, data kan lastes opp og ned til serveren (html/txt objekter og css funksjonalitet).
Det går ann å kommunisere med server i nettleseren.

Vi har lagd threads i en uendelig "while-loop" for å holde serveren "åpen" sånn at den alltid er tilgjengelig for nye forbindelser. Kode for å stenge forbindensen er også lagd inn, "connection-close" for at nettsiden ikke står stille når brukeren prøver å kalle på serveren.

For å kommunisere med serveren så må brukeren taste inn følgende: localhost:8080/newWorker.html

Når brukeren har opprettet forbindelse mot serveren så har denne mulighet til å lagre informasjon til arbeidere som lagres i en HTML fil.
For å få tilgang til lagret informasjon må brukeren taste inn følgende: localhost:8080/showWorker.html


Prosjektet er bygget av Sarah Christine Van Dyke og Wali Gustav Björk
