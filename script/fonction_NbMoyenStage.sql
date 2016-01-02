create or replace function NBMOYENSTAGE(annee Etudiant.debutAnnée%type) return real is
notAvailable exception;
moyenne real(10);
nb number(5);

begin
  if(annee<=0 or annee>to_number(to_char(sysdate,'YYYY'))) then
    raise notAvailable;
  end if;
  nb := to_number(to_char(sysdate,'YYYY'))-annee+1;
  moyenne := NBETUSTAGIAIREDATEN(annee)/nb;
  return moyenne;
exception
  when notAvailable then
    return  null;
end NBMOYENSTAGE;