/*etudiant*/
insert into etudiant(numEtu,nom,prenom,mail,adresse)
values (1,'teixeira','simon','simon.teixeira@u-psud.fr',2015,2016,adresse_ty(3,'avenue saint laurent','91400','Orsay'));
insert into etudiant(numEtu,nom,prenom,mail,adresse)
values (2,'guitton','charles','charles.guitton@u-psud.fr',2013,2015,adresse_ty(4,'rue de paris','91400','Orsay'));
insert into etudiant(numEtu,nom,prenom,mail,adresse)
values (3,'mare','benjamin','benjamin.mare@u-psud.fr',2011,2013,adresse_ty(12,'rue de verdun','91400','Orsay'));
insert into etudiant(numEtu,nom,prenom,mail,adresse)
values (4,'pavot','fabien','fabien.pavot@u-psud.fr',2014,2015,adresse_ty(1,'rue charles de gaulle','91400','Orsay'));
insert into etudiant(numEtu,nom,prenom,mail,adresse)
values (5,'doisneau','mathieu','mathieu.doisneau@u-psud.fr',2011,2014,adresse_ty(18,'rue de port royal','78470','Saint Rémy'));
insert into etudiant(numEtu,nom,prenom,mail,adresse)
values (6,'lapeyre','rémi','rémi.lapeyre@u-psud.fr',2015,2016,adresse_ty(4,'rue de paris','78470','Saint Rémy'));
insert into etudiant(numEtu,nom,prenom,mail,adresse)
values (7,'blot','charles','charles.blot@u-psud.fr',2012,2013,adresse_ty(53,'avenue des champs-élysées','75000','Paris'));
insert into etudiant(numEtu,nom,prenom,mail,adresse)
values (8,'commelin','vincent','vincent.commelin@u-psud.fr',2012,2014,adresse_ty(14,'avenue des champs-élysées','75000','Paris'));
insert into etudiant(numEtu,nom,prenom,mail,adresse)
values (9,'mariathas','alain','alain.mariathas@u-psud.fr',2012,2013,adresse_ty(36,'avenue des champs-élysées','75000','¨Paris'));
insert into etudiant(numEtu,nom,prenom,mail,adresse)
values (10,'mukthar','hassan','hassan.mukthar@u-psud.fr',2012,2013,adresse_ty(12,'rue charles de gaulle','75000','Paris'));
insert into etudiant(numEtu,nom,prenom,mail,adresse)
values (11,'vang foua','jérémie','jérémie.vangfoua@u-psud.fr',2013,2014,adresse_ty(18,'rue charles de gaulle','91400','Orsay'));
insert into etudiant(numEtu,nom,prenom,mail,adresse)
values (12,'martinez','guillaume','guillaume.martinez@u-psud.fr',2012,2014,adresse_ty(17,'rue du parc','91400','Orsay'));
insert into etudiant(numEtu,nom,prenom,mail,adresse)
values (13,'pardé','tom','tom.pardé@u-psud.fr',2013,2014,adresse_ty(1,'rue du moulin','91400','Orsay'));
insert into etudiant(numEtu,nom,prenom,mail,adresse)
values (14,'bhatta','rajsaurav','rajsaurav.bhatta@u-psud.fr',2015,2016,adresse_ty(12,'boulevard de la liberté','91440','Bures'));
insert into etudiant(numEtu,nom,prenom,mail,adresse)
values (15,'hakim','assous','hakim.assous@u-psud.fr',2013,2014,adresse_ty(15,'avenue victor hugo','91440','Bures'));
insert into etudiant(numEtu,nom,prenom,mail,adresse)
values (16,'pourouchottamane','srivatsa','srivatsa.pourouchottamane@u-psud.fr',2014,2015,adresse_ty(5,'avenue voltaire','91440','Bures'));
insert into etudiant(numEtu,nom,prenom,mail,adresse)
values (17,'robillard','jonathan','jonathan.robillard@u-psud.fr',2015,2016,adresse_ty(9,'rue gustave vatonne','91190','Gif'));
insert into etudiant(numEtu,nom,prenom,mail,adresse)
values (18,'mercadier','valentin','valentin.mercadier@u-psud.fr',2015,2016,adresse_ty(8,'rue de la chapelle','91190','Gif'));
/
/*entreprise*/
insert into entreprise (numEnt,nomEnt,mail,adresse)
values (1,'microsoft','microsoft.drh@microsoft.com',adresse_ty(1,'avenue des champs-élysées','75000','paris'));
insert into entreprise (numEnt,nomEnt,mail,adresse)
values (2,'thales','thales.drh@thales.com',adresse_ty(3,'rue charles de gaulle','91400','orsay'));
insert into entreprise (numEnt,nomEnt,mail,adresse)
values (3,'hitek','hitek.enteprise@hitek.fr',adresse_ty(1,'rue césar franck','91120','palaiseau'));
insert into entreprise (numEnt,nomEnt,mail,adresse)
values (4,'ugc','ugc.serviceclient@ugc.com',adresse_ty(1,'allée du stade','78190','saint quentin'));
insert into entreprise (numEnt,nomEnt,mail,adresse)
values (5,'jardinerie de chevreuse','jardinerieServiceClient@gmail.com',adresse_ty(1,'rue de versailles','78460','chevreuse'));
/
/*stage*/
insert into stage
values( 5,2,TO_DATE('11/04/2014'),TO_DATE('01/07/2014'),adresse_ty(1,'rue de versailles','787460','chevreuse'));
insert into stage
values( 1,3,TO_DATE('11/04/2012'),TO_DATE('01/07/2012'),adresse_ty(3,'rue de charles de gaulle','75000','paris'));
insert into stage
values( 3,5,TO_DATE('11/04/2012'),TO_DATE('01/07/2012'),adresse_ty(1,'allée du stade','78190','saint quentin'));
insert into stage
values( 2,7,TO_DATE('11/04/2013'),TO_DATE('01/07/2013'),adresse_ty(3,'rue charles de gaulle','91400','orsay'));
insert into stage
values( 2,8,TO_DATE('11/04/2013'),TO_DATE('01/07/2013'),adresse_ty(1,'rue de versailles','75000','paris'));
insert into stage
values( 1,9,TO_DATE('11/04/2013'),TO_DATE('01/07/2013'),adresse_ty(1,'rue de versailles','91120','palaiseau'));
insert into stage
values( 1,10,TO_DATE('11/04/2013'),TO_DATE('01/07/2013'),adresse_ty(1,'rue de versailles','91400','orsay'));
insert into stage
values( 3,11,TO_DATE('11/04/2014'),TO_DATE('01/07/2014'),adresse_ty(1,'rue de versailles','75000','paris'));
insert into stage
values( 4,12,TO_DATE('11/04/2014'),TO_DATE('01/07/2014'),adresse_ty(1,'allée du stade','78190','saint quentin'));
insert into stage
values( 3,13,TO_DATE('11/04/2014'),TO_DATE('01/07/2014'),adresse_ty(1,'rue de versailles','91120','palaiseau'));
insert into stage
values( 1,15,TO_DATE('11/04/2014'),TO_DATE('01/07/2014'),adresse_ty(3,'rue de charles de gaulle','75000','paris'));
insert into stage
values(2,16,TO_DATE('11/04/2015'),TO_DATE('01/07/2015'),adresse_ty(1,'rue de versailles','75000','paris'));
insert into stage
values( 4,12,TO_DATE('11/04/2013'),TO_DATE('01/07/2013'),adresse_ty(1,'allée du stade','78190','saint quentin'));
insert into stage
values( 4,5,TO_DATE('11/04/2014'),TO_DATE('01/07/2014'),adresse_ty(1,'allée du stade','78190','saint quentin'));
insert into stage
values( 3,8,TO_DATE('11/04/2014'),TO_DATE('01/07/2014'),adresse_ty(1,'rue de versailles','91400','orsay'));
insert into stage
values( 2,3,TO_DATE('11/04/2013'),TO_DATE('01/07/2013'),adresse_ty(1,'rue de versailles','75000','paris'));
insert into stage
values( 1,2,TO_DATE('11/04/2015'),TO_DATE('01/07/2015'),adresse_ty(3,'rue de charles de gaulle','75000','paris'));
/