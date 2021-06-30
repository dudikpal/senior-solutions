# Feladat

A mai feladatban bicikli sharing alkalmazást készítünk.

Adott a bikes.csv állomány, melyben egy-egy bicikli adatai találhatók:
* A bicikli azonosítója
* Az utolsó felhasználó egyedi azonosítója
* Az utolsó leadás pontos ideje
* Az utolsó úton megtett távolság kilometerben

Legyen egy BikeService nevű osztályod ami beolvassa a fájlt és eltárolja egy listában.
A beolvasás, ne a program indulásakor, hanem az első kérés alkalmával valósuljon meg.Azaz a listát visszaadó hívás esetén ellenőrizzük, hogyha a lista üres akkor beolvasunk, ha nem akkor visszaadjuk a listát.

A BikeController osztály a `/history` végponton kersztül érje el a lista minden elemét minden adattal együtt.

A `/users` végponton keresztül kapjuk meg a userek azonosítóit

Ne felejts el teszteket írni, unit és integrációs tesztet egyaránt!


bikes.csv  
FH675;US3434;2021-06-24 17:12:50;0.8
FH676;US3a34;2021-06-25 11:20:42;1.2
FH676;US3334;2021-06-25 12:40:37;0.7
FH636;US336;2021-06-23 09:36:12;1.9
FH631;US346;2021-06-24 08:53:21;2.9