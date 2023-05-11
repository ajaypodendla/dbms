CREATE TABLE Product (
    prodid TEXT PRIMARY KEY,
    pname TEXT,
    price NUMERIC
);

CREATE TABLE Depot (
    depid TEXT PRIMARY KEY,
    addr TEXT,
    volume NUMERIC
);

CREATE TABLE Stock (
    prodid TEXT REFERENCES Product(prodid) ON UPDATE CASCADE ON DELETE CASCADE,
    depid TEXT REFERENCES Depot(depid) ON UPDATE CASCADE ON DELETE CASCADE,
    quantity NUMERIC,
    PRIMARY KEY(prodid, depid)
);

INSERT INTO Product (prodid, pname, price) VALUES ('p1', 'tape', 2.5);
INSERT INTO Product (prodid, pname, price) VALUES ('p2', 'tv', 250);
INSERT INTO Product (prodid, pname, price) VALUES ('p3', 'vcr', 80);

INSERT INTO Depot (depid, addr, volume) VALUES ('d1', 'New York', 9000);
INSERT INTO Depot (depid, addr, volume) VALUES ('d2', 'Syracuse', 6000);
INSERT INTO Depot (depid, addr, volume) VALUES ('d4', 'New York', 2000);

INSERT INTO Stock (prodid, depid, quantity) VALUES ('p1', 'd1', 1000);
INSERT INTO Stock (prodid, depid, quantity) VALUES ('p1', 'd2', -100);
INSERT INTO Stock (prodid, depid, quantity) VALUES ('p1', 'd4', 1200);
INSERT INTO Stock (prodid, depid, quantity) VALUES ('p3', 'd1', 3000);
INSERT INTO Stock (prodid, depid, quantity) VALUES ('p3', 'd4', 2000);
INSERT INTO Stock (prodid, depid, quantity) VALUES ('p2', 'd4', 1500);
INSERT INTO Stock (prodid, depid, quantity) VALUES ('p2', 'd1', -400);
INSERT INTO Stock (prodid, depid, quantity) VALUES ('p2', 'd2', 2000);