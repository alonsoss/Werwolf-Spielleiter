# Werwolf-Spielleiter

\[\[_TOC_\]\]

Ein detailierter Überblick über das Spiel ist im [Wiki](../../wikis/home) zu finden.

## Projekt ausführen
Das Projekt kann auf mehrere Arten ausgeführt werden.
* Durch ausführen der Main-Methode in der MainWrapper Klasse
* Durch den Befehl `mvn clean javafx:run` in der Mavenkonsole
* Durch Maven Package und dem ausfüren der größere .jar Datei

Weitere Informationen finden sich im [Wiki](../../wikis/projekt_ausfuehren).

## Commitformat

Commits bitte in folgendem Format durchführen:

#IssueNummer Header<br>
Beschreibender Text

## Issues
*  Immer sich selbst zu den Issues assignen, die man bearbeitet
*  /spend hinzufügen, im Umfang der gearbeitet wurde
*  /estimate mit geschätzter Zeit einfügen, ggf. anpassen (Angabe in h, das entspricht dann den Story Points)

## Bugs
* Die Bugs zum aktuellen Milestone hinzufügen, damit die abgeleistete Zeit mitgerechnet wird. 
* Nach close noch mal wieder Bug als Label hinzufügen, damit eine bessere Übersicht unter Closed erreicht wird. 

## Coding Guidelines
* Kommentare werden in Englisch verfasst

## Definition of Done
* Die Akzeptanzkriterien der User Story sind erfüllt
* Alle Unit Tests sind geschrieben und laufen grün
* Die manuellen GUI-Test sind erfolgreich
* Der Code ist im Repository erfolgreich eingecheckt
* Ein Code Review wurde durchgeführt
* Die Dokumentation wurde geschrieben
* Coding Guidelines und Standards wurden eingehalten