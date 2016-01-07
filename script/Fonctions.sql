create or replace FUNCTION STAGIAIREANNEECOURANTE RETURN NUMBER AS 
  nbStagiaires NUMBER(5):=0;
BEGIN
  SELECT COUNT(*) into nbStagiaires
  FROM Etudiant e,Stage s
  WHERE e.numEtu=s.numEtu
  AND e.debutAnnée <=to_number(to_char( sysdate , 'YYYY' ))
  AND e.finAnnée >=to_number(to_char( sysdate , 'YYYY' ));
  RETURN nbStagiaires ;
END STAGIAIREANNEECOURANTE;
/

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
/

create or replace FUNCTION NONSTAGIAIREANNEECOURANTE RETURN NUMBER AS 
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

/

create or replace FUNCTION NBSTAGEZONEUT (zone Stage.Adresse.CodePostal%type,villeStage Stage.Adresse.Ville%type) RETURN NUMBER IS 
  nbStagesZone NUMBER(10):=0;
BEGIN
  SELECT COUNT(*) into nbStagesZone /*retourne le nb de stage réalissé dans la zone saisie par l'utilisateur*/
  FROM Stage s
   WHERE UPPER(s.Adresse.CodePostal)=UPPER(zone)
  AND UPPER(s.Adresse.Ville)=UPPER(villeStage);
  RETURN nbStagesZone;
END NBSTAGEZONEUT;

/

create or replace FUNCTION NBSTAGETOUTEZONE RETURN types.ref_cursor IS
  curs types.ref_cursor;
BEGIN
  OPEN curs FOR
  SELECT s.ADRESSE.CodePostal,COUNT(*) /*retourne le nb de stage par region*/
  FROM Stage s
  GROUP BY s.Adresse.CodePostal;
  RETURN curs;
END NBSTAGETOUTEZONE;

/

create or replace FUNCTION NBMOYENSTAGIAIRESENTREP (nbannee in Number)RETURN types.ref_cursor IS 
  curs types.ref_cursor;

BEGIN

  OPEN curs FOR
  SELECT En.numEnt,En.nomEnt,COUNT(DISTINCT s.numEtu)/nbannee
  FROM entreprise en,stage s 
  WHERE s.NUMENT = en.NUMENT
  AND to_char(s.dateObtention,'YYYY') >= to_char(sysdate,'YYYY')-nbannee
  GROUP BY En.numEnt,En.nomEnt;
  RETURN curs;
END;

/

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

/

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

/

/*Probleme dans la Procedure
CREATE OR REPLACE PROCEDURE NBSTAGETOUTEZONEMAJ IS
BEGIN
/*met a jour la table imbrique de la table statistique*/
 /* DELETE FROM ImbriStatTa;
  INSERT INTO ImbriStatTa
  SELECT ImbriStatOb(s.ADRESSE.CodePostal,COUNT(*)) 
  FROM Stage s
  GROUP BY s.Adresse.CodePostal;
END NBSTAGETOUTEZONEMAJ;
/
*/

