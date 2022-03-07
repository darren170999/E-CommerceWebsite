/*source full-path*/
/* Delete all DataBases except info schema:
mysql -uroot -p<password> -e "show databases" | grep -v Database | grep -v mysql| grep -v information_schema| gawk '{print "drop database `" $1 "`;select sleep(0.1);"}' | mysql -uroot -p<password>
*/

/*DB with movies related*/
drop database if exists MainDB;
create database if not exists MainDB;
use MainDB;
drop table if exists Studios;
create table Studios (StudioId int, Name varchar(50), LogoURL varchar(255), TotalStars float); /*TotalStars float*/

insert into Studios values (1000, 'Paramount', 'https://en.wikipedia.org/wiki/Paramount_Global#/media/File:Paramount_Global.png', 0);
insert into Studios values (1001, 'WarnersBrothers', 'https://en.wikipedia.org/wiki/File:Warner_Bros._Consumer_Studios_logo.png', 0);
insert into Studios values (1002, 'WaltDisney', 'https://en.wikipedia.org/wiki/Walt_Disney_Animation_Studios#/media/File:Walt_Disney_Animation_Studios_logo.svg' , 0);
insert into Studios values (1003, 'UniversalPictures', 'https://en.wikipedia.org/wiki/Universal_Pictures#/media/File:Universal_Pictures_logo.svg' , 0);

/*one studio to many movies */
drop table if exists Paramount;
create table Paramount (MoviesId int, StudioId int, Name varchar(50), ScreenTime int, AgeRating varchar(50), YearReleased int, 
MoviePosterURL varchar(255), Price float, Language varchar(50), AvailableQuantity int, Stars float);

insert into Paramount values (2100, 1000, 'The Wolf of Wall Street', 180, 'R21', 2013, 'https://www.imdb.com/title/tt0993846/mediaviewer/rm2842940160/',
0.99, 'English', 100, 8.2);
insert into Paramount values (2101, 1000, 'Titanic', 194, 'PG13', 1997, 'https://www.imdb.com/title/tt0993846/mediaviewer/rm2842940160/',
0.99, 'English', 100, 7.9);
insert into Paramount values (2102, 1000, 'The God Father', 175, 'R21', 1972, 'https://www.imdb.com/title/tt0068646/mediaviewer/rm746868224/',
0.99, 'English', 100, 9.2);

drop table if exists WarnersBrothers;
create table WarnersBrothers (MoviesId int, StudioId int, Name varchar(50), ScreenTime int, AgeRating varchar(50), YearReleased int, 
MoviePosterURL varchar(255), Price float, Language varchar(50), AvailableQuantity int, Stars float);

insert into WarnersBrothers values (2200, 1001, 'The Hangover', 100, 'R21', 2009, 'https://www.imdb.com/title/tt1119646/mediaviewer/rm401570304/',
1.99, 'English', 100, 7.7);
insert into WarnersBrothers values (2201, 1001, 'Ready Player One', 140, 'PG13', 2018, 'https://www.imdb.com/title/tt1677720/mediaviewer/rm4286860032/',
1.99, 'English', 100, 7.4);
insert into WarnersBrothers values (2202, 1001, 'Inception', 162, 'PG13', 2010, 'https://www.imdb.com/title/tt1375666/mediaviewer/rm3426651392/',
1.99, 'English', 100, 8.8);

drop table if exists WaltDisney;
create table WaltDisney (MoviesId int, StudioId int, Name varchar(50), ScreenTime int, AgeRating varchar(50), YearReleased int, 
MoviePosterURL varchar(255), Price float, Language varchar(50), AvailableQuantity int, Stars float);

insert into WaltDisney values (2300, 1002, 'The Lion King', 88, 'G', 1994, 'https://www.imdb.com/title/tt0110357/mediaviewer/rm3272938240/',
2.99, 'English', 100, 8.5);
insert into WaltDisney values (2301, 1002, 'Lilo and Stitch', 85, 'PG13', 2002, 'https://www.imdb.com/title/tt0275847/mediaviewer/rm3243219200/',
2.99, 'English', 100, 7.3);
insert into WaltDisney values (2302, 1002, 'Mulan', 88, 'PG13', 1998, 'https://www.imdb.com/title/tt0120762/mediaviewer/rm2556312576/',
2.99, 'English', 100, 7.6);

drop table if exists UniversalPictures;
create table UniversalPictures (MoviesId int, StudioId int, Name varchar(50), ScreenTime int, AgeRating varchar(50), YearReleased int, 
MoviePosterURL varchar(255), Price float, Language varchar(50), AvailableQuantity int, Stars float);

insert into UniversalPictures values (2400, 1003, 'Jurassic Park', 127, 'PG13', 1993, 'https://www.imdb.com/title/tt0107290/mediaviewer/rm3913805824/',
3.99, 'English', 100, 8.2);
insert into UniversalPictures values (2401, 1003, 'Jaws', 124, 'PG13', 1975, 'https://www.imdb.com/title/tt0073195/mediaviewer/rm1449540864/',
3.99, 'English', 100, 8.1);
insert into UniversalPictures values (2402, 1003, 'Minions', 91, 'PG13', 2015, 'https://www.imdb.com/title/tt2293640/mediaviewer/rm3451909376/',
3.99, 'English', 100, 6.4);

/* many to many table */
drop table if exists Movies_MoviesStars;
create table Movies_MoviesStars (MoviesId int ,CastID int);
insert into Movies_MoviesStars values (2100, 3000);
insert into Movies_MoviesStars values (2100, 3001);
insert into Movies_MoviesStars values (2100, 3002);
insert into Movies_MoviesStars values (2101, 3000);
insert into Movies_MoviesStars values (2101, 3003);
insert into Movies_MoviesStars values (2102, 3004);
insert into Movies_MoviesStars values (2102, 3005);
insert into Movies_MoviesStars values (2200, 3006);
insert into Movies_MoviesStars values (2200, 3007);
insert into Movies_MoviesStars values (2200, 3008);
insert into Movies_MoviesStars values (2201, 3009);
insert into Movies_MoviesStars values (2201, 3010);
insert into Movies_MoviesStars values (2202, 3000);
insert into Movies_MoviesStars values (2202, 3011);
insert into Movies_MoviesStars values (2202, 3012);
insert into Movies_MoviesStars values (2300, 3013);
insert into Movies_MoviesStars values (2300, 3014);
insert into Movies_MoviesStars values (2300, 3015);
insert into Movies_MoviesStars values (2301, 3016);
insert into Movies_MoviesStars values (2301, 3017);
insert into Movies_MoviesStars values (2302, 3018);
insert into Movies_MoviesStars values (2302, 3019);
insert into Movies_MoviesStars values (2302, 3020);
insert into Movies_MoviesStars values (2400, 3021);
insert into Movies_MoviesStars values (2400, 3022);
insert into Movies_MoviesStars values (2401, 3023);
insert into Movies_MoviesStars values (2401, 3024);
insert into Movies_MoviesStars values (2402, 3025);
insert into Movies_MoviesStars values (2402, 3026);

drop table if exists MovieStars;
create table MovieStars (CastID int, Name varchar(50), CastURL varchar(255));
insert into MovieStars values (3000, 'Leonardo Di Carprio', 'https://www.imdb.com/name/nm0000138/mediaviewer/rm487490304/');
insert into MovieStars values (3001, 'Margot Robbie', 'https://www.imdb.com/name/nm3053338/mediaviewer/rm1008443648/');
insert into MovieStars values (3002, 'Jonah Hill', 'https://www.imdb.com/name/nm1706767/mediaviewer/rm4139364608/');
insert into MovieStars values (3003, 'Kate Winslet', 'https://www.imdb.com/name/nm0000701/mediaviewer/rm537960448/');
insert into MovieStars values (3004, 'Al Pacino', 'https://www.imdb.com/name/nm0000199/mediaviewer/rm3894385408/');
insert into MovieStars values (3005, 'Marlon Brando', 'https://www.imdb.com/name/nm0000008/mediaviewer/rm1238402304/');
insert into MovieStars values (3006, 'Ed Helms', 'https://www.imdb.com/name/nm1159180/mediaviewer/rm1018280448/');
insert into MovieStars values (3007, 'Bradley Cooper', 'https://www.imdb.com/name/nm0177896/mediaviewer/rm4030128128/');
insert into MovieStars values (3008, 'Zach Galifianakis', 'https://www.imdb.com/name/nm0302108/mediaviewer/rm1030458880/');
insert into MovieStars values (3009, 'Olivia Cooke', 'https://www.imdb.com/name/nm4972453/mediaviewer/rm3001396225/');
insert into MovieStars values (3010, 'Tye Sheridan', 'https://www.imdb.com/name/nm4446467/mediaviewer/rm2945862400/');
insert into MovieStars values (3011, 'Elliot Page', 'https://www.imdb.com/name/nm0680983/mediaviewer/rm1517935617/');
insert into MovieStars values (3012, 'Joseph Gordon-Levitt', 'https://www.imdb.com/name/nm0330687/mediaviewer/rm2827403520/');
insert into MovieStars values (3013, 'Matthew Broderick', 'https://www.imdb.com/name/nm0000111/mediaviewer/rm1743425280/');
insert into MovieStars values (3014, 'James Earl Jones', 'https://www.imdb.com/name/nm0000469/mediaviewer/rm589597184/');
insert into MovieStars values (3015, 'Jeremy Irons', 'https://www.imdb.com/name/nm0000460/mediaviewer/rm3382217472/');
insert into MovieStars values (3016, 'Daveigh Chase', 'https://www.imdb.com/name/nm0153738/mediaviewer/rm3935144704/');
insert into MovieStars values (3017, 'Chris Sanders', 'https://www.imdb.com/name/nm0761498/mediaviewer/rm2981536512/');
insert into MovieStars values (3018, 'Ming-Na Wen', 'https://www.imdb.com/name/nm0001840/mediaviewer/rm985960960/');
insert into MovieStars values (3019, 'Eddie Murphy', 'https://www.imdb.com/name/nm0000552/mediaviewer/rm803183360/');
insert into MovieStars values (3020, 'BD Wong', 'https://www.imdb.com/name/nm0000703/mediaviewer/rm2582873856/');
insert into MovieStars values (3021, 'Laura Dern', 'https://www.imdb.com/name/nm0000368/mediaviewer/rm4228886528/');
insert into MovieStars values (3022, 'Sam Neill', 'https://www.imdb.com/name/nm0000554/mediaviewer/rm3722343936/');
insert into MovieStars values (3023, 'Roy Scheider', 'https://www.imdb.com/name/nm0001702/mediaviewer/rm1028495616/');
insert into MovieStars values (3024, 'Robert Shaw', 'https://www.imdb.com/name/nm0001727/mediaviewer/rm1583851520/');
insert into MovieStars values (3025, 'Sandra Bullock', 'https://www.imdb.com/name/nm0000113/mediaviewer/rm185241344/');
insert into MovieStars values (3026, 'Jon Hamm', 'https://www.imdb.com/name/nm0358316/mediaviewer/rm2388763136/');


drop table if exists CountryLocations;
create table CountryLocations (ShopId int, Name varchar(50), ShopURL varchar(255));

insert into CountryLocations values (8000, 'Singapore', 'https://en.wikipedia.org/wiki/Flag_of_Singapore#/media/File:Flag_of_Singapore.svg');
insert into CountryLocations values (8001, 'China', 'https://upload.wikimedia.org/wikipedia/commons/f/fa/Flag_of_the_People%27s_Republic_of_China.svg');
insert into CountryLocations values (8002, 'Indonesia', 'https://en.wikipedia.org/wiki/Flag_of_Indonesia#/media/File:Flag_of_Indonesia.svg');
insert into CountryLocations values (8002, 'India', 'https://en.wikipedia.org/wiki/Flag_of_India#/media/File:Flag_of_India.svg');
insert into CountryLocations values (8002, 'Malaysia', 'https://en.wikipedia.org/wiki/Flag_of_Malaysia#/media/File:Flag_of_Malaysia.svg');
insert into CountryLocations values (8002, 'Thailand', 'https://en.wikipedia.org/wiki/Flag_of_Thailand#/media/File:Flag_of_Thailand.svg');


drop table if exists Movies;
create table Movies (MoviesId int, StudioId int, Name varchar(50), ScreenTime int, AgeRating varchar(50), YearReleased int, 
MoviePosterURL varchar(255), Price float, Language varchar(50), AvailableQuantity int, Stars float);

insert into Movies values (2100, 1000, 'The Wolf of Wall Street', 180, 'R21', 2013, 'https://www.imdb.com/title/tt0993846/mediaviewer/rm2842940160/',
0.99, 'English', 100, 8.2);
insert into Movies values (2101, 1000, 'Titanic', 194, 'PG13', 1997, 'https://www.imdb.com/title/tt0993846/mediaviewer/rm2842940160/',
0.99, 'English', 100, 7.9);
insert into Movies values (2102, 1000, 'The God Father', 175, 'R21', 1972, 'https://www.imdb.com/title/tt0068646/mediaviewer/rm746868224/',
0.99, 'English', 100, 9.2);
insert into Movies values (2200, 1001, 'The Hangover', 100, 'R21', 2009, 'https://www.imdb.com/title/tt1119646/mediaviewer/rm401570304/',
1.99, 'English', 100, 7.7);
insert into Movies values (2201, 1001, 'Ready Player One', 140, 'PG13', 2018, 'https://www.imdb.com/title/tt1677720/mediaviewer/rm4286860032/',
1.99, 'English', 100, 7.4);
insert into Movies values (2202, 1001, 'Inception', 162, 'PG13', 2010, 'https://www.imdb.com/title/tt1375666/mediaviewer/rm3426651392/',
1.99, 'English', 100, 8.8);
insert into Movies values (2300, 1002, 'The Lion King', 88, 'G', 1994, 'https://www.imdb.com/title/tt0110357/mediaviewer/rm3272938240/',
2.99, 'English', 100, 8.5);
insert into Movies values (2301, 1002, 'Lilo and Stitch', 85, 'PG13', 2002, 'https://www.imdb.com/title/tt0275847/mediaviewer/rm3243219200/',
2.99, 'English', 100, 7.3);
insert into Movies values (2302, 1002, 'Mulan', 88, 'PG13', 1998, 'https://www.imdb.com/title/tt0120762/mediaviewer/rm2556312576/',
2.99, 'English', 100, 7.6);
insert into Movies values (2400, 1003, 'Jurassic Park', 127, 'PG13', 1993, 'https://www.imdb.com/title/tt0107290/mediaviewer/rm3913805824/',
3.99, 'English', 100, 8.2);
insert into Movies values (2401, 1003, 'Jaws', 124, 'PG13', 1975, 'https://www.imdb.com/title/tt0073195/mediaviewer/rm1449540864/',
3.99, 'English', 100, 8.1);
insert into Movies values (2402, 1003, 'Minions', 91, 'PG13', 2015, 'https://www.imdb.com/title/tt2293640/mediaviewer/rm3451909376/',
3.99, 'English', 100, 6.4);

/*DB with User details*/
drop database if exists MembershipDB;
create database if not exists MembershipDB;
use MembershipDB;
drop table if exists UsersDetails;
create table UsersDetails (Username varchar(50), Password varchar(50));
insert into UsersDetails values ('abc', 'abcabc');

drop table if exists UserFeedback;
create table UserFeedback (Name varchar(50), Email varchar(255), Subject varchar(50), Feedback varchar(255));

drop table if exists UserSalesRecords;
create table UserSalesRecords (MoviesId int, AvailableQuantity int, TotalSales float);


drop database if exists FinanceDB;/* DB with Everything Money related.*/
create database if not exists FinanceDB;
use FinanceDB;
drop table if exists SalesRecord;
create table SalesRecord (StudioId int, TotalSales float);

drop table if exists TopGrossings;
create table TopGrossings (MoviesId int, TotalSales float);

show databases;
