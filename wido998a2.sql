/*wido998*/

/*1*/ SELECT "William" AS "First Name", "Idoine" AS "Last Name", "549327849" AS ID, "Computer science student at UoA" AS Description;

/*2*/ SELECT * FROM Artist WHERE Name LIKE 'f%' OR Name LIKE 'F%'  COLLATE NOCASE ORDER BY ArtistID;

/*3*/  SELECT (FirstName || " " || Lastname) AS Name FROM Employee ORDER BY length(Name) DESC;

/*4*/ UPDATE Employee SET Fax = ("+" || Fax) WHERE Fax NOT LIKE "+%";

	drop table if exists Country ;
	
/*5*/ create table Country (CountryID Int PRIMARY KEY, Name Text NOT NULL, Desc Text DEFAULT "A Happy Place", Code text CHECK( length(Code) <= 7));

/*6*/ SELECT Name FROM Track WHERE Name LIKE "% right %"  OR Name LIKE "right %" OR Name LIKE "% right" OR Name LIKE "% right %" COLLATE NOCASE;

/*7*/ SELECT A.Title AS Album, count(T.name) AS Tracks, sum(T.UnitPrice) AS Price FROM Album A, Track T WHERE A.AlbumID = T.AlbumID GROUP BY A.AlbumID ORDER BY Price DESC;

/*8*/ SELECT FirstName,  min((strftime('%Y', 'now') - strftime('%Y', BirthDate)) - (strftime('%m-%d', 'now') < strftime('%m-%d', BirthDate))) as age FROM Employee WHERE Title LIKE "%manager%";

/*9*/ INSERT INTO PlayList (Name) VALUES("Quick Music");
/*9*/ INSERT OR REPLACE INTO PlayListTrack (PlaylistID, TrackID) Select * from ((select PlayListID FROM PlayList where Name LIKE "Quick%"), (select * From (SELECT TrackID FROM Track ORDER BY Millisecond ASC LIMIT 0,10)));

/*10*/ SELECT * from(select "AAC" as Media, sum(tracks) as Tracks from (SELECT count(t.trackid) as tracks from track t, MediaType mt where mt.Name LIKE "%AAC%" AND t.mediatypeID = mt.mediatypeId group by t.mediatypeID))
Union
SELECT * from(select "Non AAC" as Media, sum(tracks) as Tracks from (SELECT count(t.trackid) as tracks from track t, MediaType mt where mt.Name NOT LIKE "%AAC%" AND t.mediatypeID = mt.mediatypeId group by t.mediatypeID));


/*11 not finished */ SELECT DISTINCT a.Title as "Album" , t.albumid,  group_concat(g.Name) FROM Album a, Genre g, Track t WHERE g.GenreID = t.GenreID AND t.AlbumID =  a.AlbumID GROUP BY t.GenreID, t.trackid, t.albumid;

/*12*/ select substr(Email, charindex('@',Email,1)+1, charindex('.',Email,charindex('@',Email,1)+1)-charindex('@',Email,1)-1), 
		round(Count(substr(Email, charindex('@',Email,1)+1,charindex('.',Email,charindex('@',Email,1)+1)-charindex('@',Email,1)-1))*100 / round((SELECT Count(email) FROM customer),2),2) as MyPercentage from customer
		Group by substr(Email, charindex('@',Email,1)+1, charindex('.',Email,charindex('@',Email,1)+1)-charindex('@',Email,1)-1)
		order by count(*) DESC, substr(Email, charindex('@',Email,1)+1, charindex('.',Email,charindex('@',Email,1)+1)-charindex('@',Email,1)-1) ASC;

