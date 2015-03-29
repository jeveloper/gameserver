### NOTES ON THIS PROJECT Originally Designed and build by Serge Bornow in 2007

### 2 Main Components 
 - Logic - several lottery implementations
 - Using a Queue (message driven) processing millions of game can be achieved 


## Other notes

 - This project was built with Java 1.5 or 1.6 in mind and to run on Jboss or other EJB capable platform
 - NetBeans IDE was used


GameLogic - logic

Terminology

Pick - a single character a-z , e.g. A, e.g. user will select a series of picks e.g. ABCD

Play - a single series of Picks e.g. Play: A B C D E F G

Note: a user can select multiple plays within one game
 Important notice: 
   Each play is compared against server pick and each one is tracked
   Each play is compared and best is chosen for the Draw


Some Important notes:

> General

- class BaseGame contains a property Id which is a InstanceID
- It is advised to remove 3 Test classes as well as NewMain from the enterprise module


> Elimination

If ALL are eliminated at once (1st round) 
the game is a DRAW 
All players win

If in Elimination only one player is left, he/she is the winner


> Enterprise module - JMS, Database

NewMain.java class contains a sample of how to run a game , please remove for production

Very important:
Commons-primitives.jar are needed to deploy enterprise module as well as the same version of gameServer.jar


2 files are configurable for JNDI context
both reside in a package com.jeveloper.gameserver.enterprise.mdb  hence it would be the same folder

jndi-sender-props.properties file contains information for a Web Tier server where the message will be sent to upon
completion of a game , instead of a localhost, an ip can be used

jndi-db-props.properties file contains information that used by the persistence piece 
Also in configuration i've placed a mysql-ds.xml file which is a setup file for a Datasource
it is ready for use, a mysql driver is required to be placed in a shared lib folder for Jboss 

To change the location of these files to a better place: alter Utility class

StartMDB is deployable EJB 3.0 Message Driven Bean that is invoked upon receiving a message via
a queue .
To alter the name of a queue, the following has to be changed and redeployed.
propertyName = "destination", propertyValue = "queue/GameStarter")

Side NOTE: a permanent Queue with the same name should be setup using Jboss's service description 
See XML files located in the jboss with same queue already deployed
A queue can also be restricted with username and password as well as JNDI itself.

> Persistence

A single transaction would attempt to store all draws at once in a batch to be effective
Upon failure a rollback happens, otherwise implicit commit.


Upon completion of a game, then persistence , a message is sent back to Web Tier 
from RespondUponCompletion class 
Note: a different style of coding JMS messages is used in this class simply for demonstration , whichever is preferred.





    