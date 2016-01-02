CREATE OR REPLACE FUNCTION STAGIAIREANNEECOURANTE RETURN NUMBER AS 
  nbStagiaires NUMBER(5):=0;
BEGIN
  SELECT COUNT(*) into nbStagiaires
  FROM Etudiant e,Stage s
  WHERE e.numEtu=s.numEtu
  AND e.debutAnnée <=to_number(to_char( sysdate , 'YYYY' ))
  AND e.finAnnée >=to_number(to_char( sysdate , 'YYYY' ));
  RETURN nbStagiaires ;
END STAGIAIREANNEECOURANTE;