pact13
======

Repository pour le PACT 1.3

Chaque module possède un package, e.g. GUI, et peut créer des sous-package (ex : GUI.Accueil). De même, dans le dossier data, chaque module  possède un
sous-dossier dans lequel on place les ressources du module (ex : data/GUI/user.png).

Documentation module Graphic User Interface :
  Le module contient un objet de type Window, dans lequel sont contenus un certain nombre d'objets de type Fenetres. Ces fenetres sont agencées par le Cardlayout de la sur-classe Window. Le Cardlayout échange les fenetres au premier plan en lien avec la navigation de l'utilisateur dans l'interface.
