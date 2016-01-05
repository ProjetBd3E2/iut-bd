CREATE OR REPLACE FUNCTION NBSTAGEZONEUT (zone Stage.Adresse.CodePostal%type,villeStage Stage.Adresse.Ville%type) RETURN NUMBER IS 
  nbStagesZone NUMBER(10):=0;
BEGIN
  SELECT COUNT(*) into nbStagesZone /*retourne le nb de stage réalissé dans la zone saisie par l'utilisateur*/
  FROM Stage s
   WHERE UPPER(s.Adresse.CodePostal)=UPPER(zone)
  AND UPPER(s.Adresse.Ville)=UPPER(villeStage);
  RETURN nbStagesZone;
END NBSTAGEZONEUT;