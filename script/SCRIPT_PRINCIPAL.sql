create or replace TYPE adresse_ty AS OBJECT (
  NumRue CHAR(5),
  Rue VARCHAR(50),
  CodePostal CHAR(5),
  Ville VARCHAR(30)); 
/  

CREATE OR REPLACE TYPE etudiant_ty AS OBJECT(
    numEtu      NUMBER(10),
    nom         VARCHAR2(50),
    prenom      VARCHAR2(50),
    mail        VARCHAR2(60),
    Adresse     adresse_ty);
/    
    
CREATE TABLE etudiant OF etudiant_ty 
(numEtu PRIMARY KEY);
/

CREATE OR REPLACE TYPE entreprise_ty AS OBJECT(
    numEnt      NUMBER(10),
    nomEnt      VARCHAR2(50),
    mail        varchar2(60),
    Adresse     adresse_ty);  
/

CREATE TABLE entreprise OF entreprise_ty 
(numEnt PRIMARY KEY);
/

CREATE OR REPLACE TYPE stage_ty AS OBJECT(
      numEnt      NUMBER(10),
      numEtu      NUMBER(10),
      dateDeb     DATE,
      dateFin     DATE,
      Adresse     adresse_ty);  
/      
      
CREATE TABLE stage OF stage_ty 
(PRIMARY KEY (numEnt, numEtu));
    
    
