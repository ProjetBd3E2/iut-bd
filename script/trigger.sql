create trigger trgApresModifStage
after insert or update or delete on stage
begin
  update resultat
  set nbEtudiantStageMaintenant = STAGIAIREANNEECOURANTE,
  nbEtudiantSansStageMaintenant = NONSTAGIAIREANNEECOURANTE,
  nbStageTouteZone = NBSTAGIAIREREGION;
end trgApresModifStage;