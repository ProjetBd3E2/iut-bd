create or replace FUNCTION NONSTAGIAIREANNEEUTILISATEUR(obtention Stage.dateObtention%type) RETURN NUMBER IS 
  nbStagiairesDateN NUMBER(10):=0;
  nbEtu NUMBER(10):=0;
  nbNonStagiairesDateN NUMBER(10):=0;
  ex Exception;
  anneeDeb Date;
  x NUMBER(4):=0;
  prov1 VARCHAR2(10):='03/09/';
  
  
BEGIN
  IF(to_number(to_char(anneeDeb,'YYYY')) <= 0 or to_number(to_char(anneeDeb,'YYYY')) >= to_number(to_char(sysdate,'YYYY')))then RAISE ex;/*Verification année valide*/
  END IF;
  IF(to_number(to_char(obtention,'MM'))<9)then /*test pour voir si le stage est trouvé avant ou apres septembre*/
    x:=to_number(to_char(obtention,'YYYY'))-1;/*si trouvé avant septembre on considere que l'annee scolaire est celle d'avant*/
    prov1:=prov1||x;
    anneeDeb:=to_date(prov1);/*ducoup on assigne le debut d'année le 3 septembre de l'année d'avant*/
  ELSE 
    prov1:=prov1||to_number(to_char(obtention,'YYYY'));/*si trouvé apres septembre on prends l'annee donnée par l'utilisateur*/
    anneeDeb:=to_date(prov1);/*ducoup on assigne le debut d'année le 3 septembre de l'année donnée par l'utilisateur*/
  END IF; 
  
  SELECT COUNT(*) into nbStagiairesDateN /*etu avec stage*/
  FROM Etudiant e,Stage s
  WHERE e.numEtu =s.numEtu
  AND s.dateObtention>=anneeDeb
  AND s.dateObtention<=obtention;
  
  SELECT COUNT(*) into nbEtu/*tout les etudiants de cette année*/
  FROM ETUDIANT e
  WHERE e.debutAnnée<=to_number(to_char(anneeDeb,'YYYY'))
  AND e.finAnnée>to_number(to_char(anneeDeb,'YYYY'));
  
  nbNonStagiairesDateN:=nbEtu-nbStagiairesDateN;/*nombre d'etudiants-nombre de stagiaires pour trouver nbNonStagiares*/
  RETURN nbNonStagiairesDateN;
  
EXCEPTION WHEN ex THEN RETURN -1; 
END NONSTAGIAIREANNEEUTILISATEUR;