create or replace FUNCTION NONSTAGIAIREANNEEUTILISATEUR(obtention Stage.dateObtention%type) RETURN NUMBER IS 
  nbStagiairesDateN NUMBER(10):=0;
  nbEtu NUMBER(10):=0;
  nbNonStagiairesDateN NUMBER(10):=0;
  ex Exception;
  anneeDeb Date;
  x NUMBER(4):=0;
  prov1 VARCHAR2(10):='03/09/';
  
  
BEGIN
  IF(to_number(to_char(anneeDeb,'YYYY')) <= 0 or to_number(to_char(anneeDeb,'YYYY')) >= to_number(to_char(sysdate,'YYYY')))then RAISE ex;/*Verification ann�e valide*/
  END IF;
  IF(to_number(to_char(obtention,'MM'))<9)then /*test pour voir si le stage est trouv� avant ou apres septembre*/
    x:=to_number(to_char(obtention,'YYYY'))-1;/*si trouv� avant septembre on considere que l'annee scolaire est celle d'avant*/
    prov1:=prov1||x;
    anneeDeb:=to_date(prov1);/*ducoup on assigne le debut d'ann�e le 3 septembre de l'ann�e d'avant*/
  ELSE 
    prov1:=prov1||to_number(to_char(obtention,'YYYY'));/*si trouv� apres septembre on prends l'annee donn�e par l'utilisateur*/
    anneeDeb:=to_date(prov1);/*ducoup on assigne le debut d'ann�e le 3 septembre de l'ann�e donn�e par l'utilisateur*/
  END IF; 
  
  SELECT COUNT(*) into nbStagiairesDateN /*etu avec stage*/
  FROM Etudiant e,Stage s
  WHERE e.numEtu =s.numEtu
  AND s.dateObtention>=anneeDeb
  AND s.dateObtention<=obtention;
  
  SELECT COUNT(*) into nbEtu/*tout les etudiants de cette ann�e*/
  FROM ETUDIANT e
  WHERE e.debutAnn�e<=to_number(to_char(anneeDeb,'YYYY'))
  AND e.finAnn�e>to_number(to_char(anneeDeb,'YYYY'));
  
  nbNonStagiairesDateN:=nbEtu-nbStagiairesDateN;/*nombre d'etudiants-nombre de stagiaires pour trouver nbNonStagiares*/
  RETURN nbNonStagiairesDateN;
  
EXCEPTION WHEN ex THEN RETURN -1; 
END NONSTAGIAIREANNEEUTILISATEUR;