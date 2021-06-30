# Csapatmunka feladat
​
## Feladat
A mai feladatban az EB meccsek eredményeit kell egy alkalmazásban  
tárolnod, és különböző feladatokat elvégezned.
​
### Game
Legyen egy `Game` nevű osztályod a következő attribútumokkal
+ `firstCountry (String)`
+ `secondCountry (String)`
+ `firstCountryScore (int)`
+ `secondCountryScore (int)`
​
Legyen benne egy metódus ami visszaadja a győztes ország nevét!
​
​
### GameRepository 
Legyen egy `GameRepository` nevű osztályod, melynek van egy meccseket 
memóriában tároló listája van.
​
A listához elemet két féle képpen lehet hozzáadni. Vagy egy `addGame(Game game)` metódussal,
vagy fájlból beolvasva, ahol a fájl egy csv állomány.
​
### GameService
Legyen egy `GameService` nevű osztályod, ami különböző statisztikai adatokat jelenít meg.
Legyen egy `GameRepository` attribútuma amin keresztül eléri a benne lévő listát.
​
Megvalósítandó metódusok:
​
+ Határozd meg a legnagyobb gólkülönbséggel véget ért mérkőzést
+ Határozd meg hogy egy paraméterül kapott ország hány gólt rúgott eddig
+ Határozd meg az eddig legtöbb gólt rúgó országot
​
​
### Tesztelés
Mindegyik osztályhoz legyen külön teszt osztály. A nem generált metódusokhoz legyen teszt eset, lehetőleg több. 
A `GameService` osztály második metódusát paraméterezett teszttel végezd. Ez lehet akár dinamikus teszteset is.

results.csv

Turkey;Italy;0;3
Wales;Switzerland;1;1
Denmark;Finnland;0;1
Belgium;Russia;3;0
England;Croatia;1;0
Austria;North Macedonia;3;1
Netherlands;Ukrain;3;2
Scotland;Czech-Republic;0;2
Poland;Slovakia;1;2
Spain;Sweden;0;0
Hungary;Portugal;1;0
French;Germany;2;2
Finnland;Russia;0;1
Turkey;Wales;1;1
Italy;Switzerland;2;0