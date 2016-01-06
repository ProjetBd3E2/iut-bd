create or replace FUNCTION CONTACTENTREPRISE(nbannee in Number) RETURN types.ref_cursor IS 
  curs types.ref_cursor;
BEGIN

  OPEN curs FOR
  SELECT DISTINCT En.numEnt,En.nomEnt,et.NOM,et.PRENOM
  FROM entreprise en,stage s,etudiant et 
  WHERE s.NUMENT = en.NUMENT
  AND et.NUMETU=s.NUMETU
  AND to_char(s.dateObtention,'YYYY') >= to_char(sysdate,'YYYY')-nbannee
  ORDER BY En.numEnt;
  RETURN curs;
END CONTACTENTREPRISE;