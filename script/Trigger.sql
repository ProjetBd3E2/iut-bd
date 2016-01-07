CREATE or replace TRIGGER trgApresModifStage
AFTER INSERT OR UPDATE OR DELETE ON stage
BEGIN
  UPDATE resultat
  SET nbEtudiantStageMaintenant = STAGIAIREANNEECOURANTE,
  nbEtudiantSansStageMaintenant = NONSTAGIAIREANNEECOURANTE;
  /*NBSTAGETOUTEZONE;*/
END trgApresModifStage;

/