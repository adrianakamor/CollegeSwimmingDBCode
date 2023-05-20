create procedure dbo.updateBestTimes @SwimmerID int, @EventName varchar(25), @EventTime float, @MeetName varchar(100), @Month varchar(25), @Year int
as
begin
update besttimes
	set eventname = @EventName
	where besttimes.swimmerID = @SwimmerID
update besttimes
	set eventtime = @EventTime
	where besttimes.swimmerID = @SwimmerID
update besttimes
	set meetname = @MeetName
	where besttimes.swimmerID = @SwimmerID
update besttimes
	set month = @Month
	where besttimes.swimmerID = @SwimmerID
update besttimes
	set year = @Year
	where besttimes.swimmerID = @SwimmerID
end; 
