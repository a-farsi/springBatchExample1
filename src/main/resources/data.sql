/*
declare
   c int;
begin
   select count(*) into c from user_tables where table_name = upper('table_parametrage_cnil');
   if c = 1 then
      execute immediate 'drop table table_parametrage_cnil';
   end if;
end;
*/
INSERT INTO table_parametrage_cnil  VALUES (5,'police','description','POLICES','DESCR'); 
INSERT INTO table_parametrage_cnil  VALUES (6,'police','notes','POLICES','NOTES'); 
INSERT INTO table_parametrage_cnil  VALUES (7,'police','avis_locataire','POLICHAB','AVIS_LOC'); 
INSERT INTO table_parametrage_cnil  VALUES (8,'police','DDO_contrat','POLICE_DDO','Champs_25'); 