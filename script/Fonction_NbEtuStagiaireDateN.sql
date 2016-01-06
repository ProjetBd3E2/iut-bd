create or replace FUNCTION NBETUSTAGIAIREDATEN (nbannee in number)RETURN types.ref_cursor IS
  curs types.ref_cursor;
 
BEGIN
 
  OPEN curs FOR
  SELECT En.numEnt,En.nomEnt,COUNT(DISTINCT s.numEtu)
  FROM Entreprise En,Stage s
  WHERE En.numEnt=s.numEnt
  AND to_char(s.dateObtention,'YYYY')>=to_char(sysdate,'YYYY')-nbannee /*on prends tout les stages obtenu apres la date fixée*/
  GROUP BY En.numEnt,En.nomEnt; /*on groupe par entreprise*/
 
  RETURN curs;
END NBETUSTAGIAIREDATEN;