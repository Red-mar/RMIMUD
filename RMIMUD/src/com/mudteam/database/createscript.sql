DROP TABLE IF EXISTS character;
DROP TABLE IF EXISTS inventory;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS characterlocation;
DROP TABLE IF EXISTS accountcharacter;
DROP TABLE IF EXISTS account;

CREATE TABLE item(
  ID integer PRIMARY KEY AUTOINCREMENT ,
  Name TEXT NOT NULL
);

CREATE TABLE location(
  ID integer PRIMARY KEY AUTOINCREMENT ,
  Name TEXT NOT NULL,
  X integer NOT NULL,
  Y integer NOT NULL
);

CREATE TABLE characterlocation(
  LocationID integer NOT NULL,
  CharacterID integer NOT NULL,
  FOREIGN KEY(LocationID) REFERENCES location(ID),
  FOREIGN KEY(CharacterID) REFERENCES character(ID)
);

CREATE TABLE character (
  ID integer PRIMARY KEY AUTOINCREMENT ,
  Name TEXT NOT NULL,
  Lifepoints integer NOT NULL
);

CREATE TABLE inventory(
  InventoryID integer NOT NULL,
  ItemID integer NOT NULL,
  FOREIGN KEY(ItemID) REFERENCES item(ID),
  FOREIGN KEY(InventoryID) REFERENCES character(ID)
);

CREATE TABLE accountcharacter(
  AccountID integer NOT NULL,
  CharacterID integer NOT NULL,
  FOREIGN KEY(AccountID) REFERENCES account(ID),
  FOREIGN KEY(CharacterID) REFERENCES character(ID)
);

CREATE TABLE account(
  ID integer PRIMARY KEY AUTOINCREMENT,
  Username TEXT NOT NULL,
  Password TEXT NOT NULL
);