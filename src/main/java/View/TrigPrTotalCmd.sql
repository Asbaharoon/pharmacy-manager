Create trigger PrtotalCmd on Lignevente for update,insert
as
begin
declare c cursor for select p.prachat,i.idcmd  from Produit p,inserted i,commande c where i.idprod=p.idprod and c.idcmd=i.idcmd
declare @pr float,@cmd int
open c 
fetch c into @pr,@cmd 
while(@@FETCH_STATUS+0)
begin 
update Commande set prixtotal=prixtotal+@pr   where idcmd=@cmd
end
fetch c into @pr,@cmd 
end 
close c
deallocate c
