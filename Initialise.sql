drop table ambulance_staff;
drop table ambulances;
create table AMBULANCE_STAFF (Surname VARCHAR(30), FirstName VARCHAR(60), OfficerID INT NOT NULL PRIMARY KEY, SkillLevel VARCHAR(15), Ambulance VARCHAR(4));
create table AMBULANCES (AmbulanceID VARCHAR(4) NOT NULL PRIMARY KEY , Station VARCHAR(30));

insert into AMBULANCE_STAFF values("Doe", "John", 135790, "Basic", null);
insert into AMBULANCE_STAFF values("Smith","Peter" , 135970, "Basic", null);
insert into AMBULANCE_STAFF values("Doe", "Jane", 131234, "Intermediate", null);
insert into AMBULANCE_STAFF values("Bobbins", "Bill", 133535, "Intermediate", null);
insert into AMBULANCE_STAFF values("Green", "Carol", 130001, "Advanced", null);
insert into AMBULANCE_STAFF values("Shield","Jill" , 132244, "Basic", null);

insert into AMBULANCES values("A7", "Greenfields");
insert into AMBULANCES values("A42", "Bluelane");
insert into AMBULANCES values("A110", "Bluelane");
