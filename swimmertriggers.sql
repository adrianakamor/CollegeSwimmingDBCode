--Changing the roster size when adding or deleting swimmers 
create trigger increaserostersize
	on swimmer
	after insert
	as
	begin
	 update team
   		set rostersize = rostersize + 1
   		where team.teamID = (select teamID from inserted);
end;

create trigger decreaserostersize
	on swimmer
	after delete
	as
	begin
		update team
   			set rostersize = rostersize - 1
   			where team.teamID = (select teamID from deleted);
end;

--Changing roster size when a swimmer switches teams
create trigger switchteams
	on swimmer
	after update
	as
	begin
		update team
			set rostersize = rostersize + 1
   			where team.teamID = (select teamID from inserted)
		update team
   			set rostersize = rostersize - 1
   			where team.teamID = (select teamID from deleted)
end;

--Checking for a record break when besttime is updated
alter trigger recordbreak
	on besttimes
	after insert, update
	as
	begin
			if ((select eventtime from inserted) < 
			(select eventtime
			from records
			where records.eventname = (select eventname from inserted) and records.gender = (select gender from swimmer where swimmer.swimmerID = (select swimmerID from inserted)) and records.conferencename = (select conferencename from team where teamID = (select teamID from swimmer where swimmerID = (select swimmerID from inserted)))))
				begin
				update records
					set records.swimmerID = (select swimmerID from inserted);
				update records
					set records.eventtime = (select eventtime from inserted);
				update records
					set records.meetname = (select meetname from inserted);
				update records
					set records.month = (select month from inserted);
				update records
					set records.year = (select year from inserted);
				end
		if ((select eventtime from records where records.eventname = (select eventname from inserted) and records.gender = (select gender from swimmer where swimmer.swimmerID = (select swimmerID from inserted)) and records.conferencename = (select conferencename from team where teamID = (select teamID from swimmer where swimmerID = (select swimmerID from inserted))))is null)
    	begin
		insert into records(conferencename, gender, eventname, swimmerID, eventtime, meetname, month, year)
   		values((select conferencename from team where teamID = (select teamID from swimmer where swimmerID = (select swimmerID from inserted))), (select gender from swimmer where swimmer.swimmerID = (select swimmerID from inserted)), (select eventname from inserted), (select swimmerID from inserted), (select eventtime from inserted), (select meetname from inserted), (select month from inserted), (select year from inserted))
		end
end