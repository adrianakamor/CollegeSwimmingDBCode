create table meet (
	meetname	varchar(100), 
	month	varchar(25), 
	year	int, 
	type	varchar(25) check (type in ('dual', 'invite', 'conference', 'nationals')), 
	primary key (meetname, month, year)
)
create table conference (
	conferencename	varchar(50), 
	yearfounded	int, 
	primary key (conferencename)
)
create table team (
	teamID	int identity(0,1), 
	teamname	varchar(100), 
	city	varchar(100), 
	state	varchar(2), 
	conferencename	varchar(50), 
	rostersize	int default 0, 
	primary key (teamID), 
	foreign key (conferencename) references conference
)
create table swimmer (
	swimmerID	int identity(0,1), 
	firstname	varchar(50), 
	lastname	varchar(50), 
	gender	varchar(6) check (gender in ('male', 'female')), 
	teamID	int, 
	year	int, 
	major	varchar(50), 
	speciality	varchar(50), 
	hometown	varchar(50), 
	primary key (swimmerID), 
	foreign key (teamID) references team
)
create table coach (
	coachID	int identity(0,1), 
	firstname	varchar(50), 
	lastname	varchar(50), 
	role	varchar(25) check (role in('head', 'assistant')), 
	teamID	int, 
	primary key (coachID), 
	foreign key (teamID) references team
)
create table besttimes (
	swimmerID	int, 
	eventname	varchar(25), 
	eventtime	float, 
	meetname	varchar(100), 
	month	varchar(25), 
	year	int, 
	primary key (swimmerID, eventname), 
	foreign key (meetname, month, year) references meet,
	foreign key (swimmerID) references swimmer	
)
create table records (
	conferencename	varchar(50), 
	gender	varchar(6), 
	eventname	varchar(25), 
	swimmerID	int, 
	eventtime	float, 
	meetname	varchar(100), 
	month	varchar(25), 
	year	int, 
	primary key (conferencename, gender, eventname),
	foreign key (swimmerID) references swimmer, 
	foreign key (meetname, month, year) references meet
) 

