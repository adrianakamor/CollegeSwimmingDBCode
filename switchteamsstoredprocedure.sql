create procedure dbo.swimmerTransfer @swimmerID int, @teamID int
as 
begin 
	update swimmer
		set teamID = @teamID
		where swimmerID = @swimmerID
end