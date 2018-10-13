
SELECT DISTINCT * FROM Reserves AS R, Boats AS B WHERE R.G = B.F;
SELECT DISTINCT * FROM Sailors AS S, Reserves AS R, Boats AS B WHERE S.A = R.G AND R.G = B.F;
SELECT DISTINCT * FROM Sailors AS S, Reserves AS R, Boats AS B WHERE S.A = R.G AND R.G = B.F ORDER BY S.B;
SELECT * FROM Sailors S, Reserves R WHERE S.A = R.G;
SELECT * FROM Sailors S, Reserves R WHERE R.G = S.A;
SELECT * FROM Sailors, Reserves WHERE Sailors.A = Reserves.G;
SELECT * FROM Reserves AS R, Sailors AS S WHERE S.A = R.G;
SELECT * FROM Reserves AS R, Sailors AS S WHERE R.G = S.A;
SELECT R.G, S.A, R.H FROM Reserves AS R, Sailors AS S WHERE R.G = S.A;
SELECT DISTINCT R.G, S.A, R.H FROM Reserves AS R, Sailors AS S WHERE R.G = S.A;
SELECT DISTINCT R.G, S.A, R.H FROM Reserves AS R, Sailors AS S WHERE R.G = S.A ORDER BY R.H;
SELECT * FROM Sailors S1, Sailors S2 WHERE S1.A < S2.A AND S1.A<3;  
SELECT DISTINCT R.G FROM Reserves R;
SELECT * FROM Sailors ORDER BY Sailors.B;
SELECT * FROM Sailors;
SELECT Sailors.A FROM Sailors;
SELECT S.A FROM Sailors S;
SELECT * FROM Sailors S WHERE S.A < 3;
