Vaadin 8.1 Grid Bug
==============

Vaadin 8.1 Grid example to reproduce bug of row selection for big row numbers.

Rows with numbers 441506 (zero based) and more is not selectable.

How to reproduce
========

* run "mvn install"
* run "mvn jetty:run"
* open http://localhost:8080
* go to the row number 441505, make sure that it is selectable
* go to next rows and make sure that it is **not** selectable  

Workflow
========

To compile the entire project, run "mvn install".

To run the application, run "mvn jetty:run" and open http://localhost:8080/ .

To produce a deployable production mode WAR:
- change productionMode to true in the servlet class configuration (nested in the UI class)
- run "mvn clean package"
- test the war file with "mvn jetty:run-war"
