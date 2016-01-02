CREATE OR REPLACE FUNCTION STAGIAIREANNEECOURANTE RETURN NUMBER AS 
  nbStagiaires NUMBER(5):=0;
BEGIN
  SELECT COUNT(*) into nbStagiaires
  FROM Etudiant e,Stage s
  WHERE e.numEtu=s.numEtu
  AND e.debutAnn�e <=to_number(to_char( sysdate , 'YYYY' ))
  AND e.finAnn�e >=to_number(to_char( sysdate , 'YYYY' ));
  RETURN nbStagiaires ;
END STAGIAIREANNEECOURANTE;