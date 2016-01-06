create or replace FUNCTION NBSTAGETOUTEZONE RETURN types.ref_cursor IS
  curs types.ref_cursor;
BEGIN
  OPEN curs FOR
  SELECT s.ADRESSE.CodePostal,COUNT(*) /*retourne le nb de stage par region*/
  FROM Stage s
  GROUP BY s.Adresse.CodePostal;
  RETURN curs;
END NBSTAGETOUTEZONE;