CREATE OR REPLACE FUNCTION NONSTAGIAIREANNEECOURANTE RETURN NUMBER AS 
 nbNonStagiaires NUMBER(5):=0;
BEGIN
  SELECT COUNT(DISTINCT e.numEtu) into nbNonStagiaires
  FROM Etudiant e,Stage s
  WHERE e.debutAnnée <=to_number(to_char( sysdate , 'YYYY' ))
  AND e.finAnnée >=to_number(to_char( sysdate , 'YYYY' ))
  AND e.numEtu NOT IN( select s1.numEtu
                      from stage s1
                      where S1.numEtu IS NOT NULL );
  RETURN nbNonStagiaires ;
END NONSTAGIAIREANNEECOURANTE;