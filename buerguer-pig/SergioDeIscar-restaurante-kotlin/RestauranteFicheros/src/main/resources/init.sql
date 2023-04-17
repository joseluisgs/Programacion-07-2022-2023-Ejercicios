CREATE TABLE IF NOT EXISTS tProducto (
    nIdProducto INTEGER PRIMARY KEY AUTOINCREMENT,
    cNombre TEXT NOT NULL,
    nPrecio REAL NOT NULL
);

CREATE TABLE IF NOT EXISTS tBebida (
    nIdBebida INTEGER PRIMARY KEY,
    nCapacidad INTEGER NOT NULL,
    FOREIGN KEY (nIdBebida) REFERENCES tProducto(nIdProducto)
);

CREATE TABLE IF NOT EXISTS tIngrediente (
    nIdIngrediente INTEGER PRIMARY KEY AUTOINCREMENT,
    cNombre TEXT NOT NULL,
    nPrecio REAL NOT NULL
);

CREATE TABLE IF NOT EXISTS tHamburguesa (
    nIdHamburguesa INTEGER PRIMARY KEY,
    FOREIGN KEY (nIdHamburguesa) REFERENCES tProducto(nIdProducto)
);

CREATE TABLE IF NOT EXISTS tHamburguesa_Ingrediente (
    id_hamburguesa INTEGER NOT NULL,
    id_ingrediente INTEGER NOT NULL,
    PRIMARY KEY (id_hamburguesa, id_ingrediente),
    FOREIGN KEY (id_hamburguesa) REFERENCES tHamburguesa(nIdHamburguesa),
    FOREIGN KEY (id_ingrediente) REFERENCES tIngrediente(nIdIngrediente)
);