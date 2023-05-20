alter procedure dbo.meetRecords @MeetName varchar(100), @Month varchar(25), @Year int
as
begin
with M(meetname, month, year) as 
	(select meetname, month, year from meet where meet.meetname = @MeetName and meet.month = @Month and meet.year = @Year)
	select R.meetname, R.month, R.year, R.gender, R.swimmerID, S.firstname, S.lastname, R.eventname, R.eventtime 
	from records as R inner join M on R.meetname = M.meetname and R.month = M.month and R.year = M.year 
	inner join swimmer as S on R.swimmerID = S.swimmerID;
end; 

exec meetRecords 'UAAs', 'February', 2022