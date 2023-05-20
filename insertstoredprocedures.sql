create procedure dbo.insertConference @ConferenceName as varchar(50), @YearFounded as int
as
begin
insert into conference(conferencename, yearfounded)
values(@ConferenceName, @YearFounded)
end; 

create procedure dbo.insertTeam @TeamName varchar(100), @City varchar(100), @State varchar(2), @ConferenceName varchar(50)
as
begin
insert into team(teamname, city, state, conferencename)
values(@TeamName, @City, @State, @ConferenceName)
end; 

create procedure dbo.insertSwimmer @FirstName varchar(50), @LastName varchar(50), @Gender varchar(6), @TeamID int, @Year int, @Major varchar(50), @Speciality varchar(50), @Hometown varchar(50)
as
begin
insert into swimmer(firstname, lastname, gender, teamID, year, major, speciality, hometown)
values(@FirstName, @LastName, @Gender, @TeamID, @Year, @Major, @Speciality, @Hometown)
end; 

create procedure dbo.insertCoach @FirstName varchar(50), @LastName varchar(50), @Role varchar(25), @TeamID int
as
begin
insert into coach(firstname, lastname, role, teamID)
values(@FirstName, @LastName, @Role, @TeamID)
end; 

create procedure dbo.insertMeet @MeetName varchar(100), @Month varchar(25), @Year int, @Type varchar(25)
as
begin
insert into meet(meetname, month, year, type)
values(@MeetName, @Month, @Year, @Type)
end; 

create procedure dbo.insertBestTimes @SwimmerID int, @EventName varchar(25), @EventTime float, @MeetName varchar(100), @Month varchar(25), @Year int
as
begin
insert into besttimes(swimmerID, eventname, eventtime, meetname, month, year)
values(@SwimmerID, @EventName, @EventTime, @MeetName, @Month, @Year)
end; 
