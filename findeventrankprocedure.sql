create procedure dbo.findEventRank (@SwimmerID as int, @EventName as varchar(25))
as
begin
select count(eventtime) + 1 as teamrank
from (select swimmerID from swimmer where teamID = (select teamID from swimmer where swimmerID = @SwimmerID)
		 and gender = (select gender from swimmer where swimmerID = @SwimmerID)) as roster INNER JOIN besttimes ON roster.swimmerID = besttimes.swimmerID
		 where eventname = @EventName and 
		 eventtime < (select eventtime from besttimes where swimmerID = @SwimmerID and eventname = @EventName);
end;




