Ennek a feladatnak az elvégzése opcionális. Akkor csináld meg, ha érdekel, hogy hozz létre egy Spring Frameworkre épülő projektet Spring Boot nélkül. Ezt ma már csak legacy alkalmazásoknál használjuk, új projektet Spring Boot használatával indítunk.

Hozz létre egy locations-spring projektet, benne egy LocationsControllerosztályt, mely visszaadja a kedvenc helyeket, egyelőre Stringként!

Egy kedvenc helyet a Location osztály reprezentál. Rendelkezik egy azonosítóval, névvel és két koordinátával (rendre Long id, String name, double lat, double lon).

Ezek egy listája legyen a controllerben. A getLocations() metódus ezeket adja vissza. Implementáld a Location osztály toString() metódusát!