create procedure dbo.retiredSwimmer @SwimmerID as varchar(50)
as
begin
update swimmer
	set teamID = null
	where swimmerID = @SwimmerID
end; 