# OOP_MySQL_workshop
:boom: Workshop based on **Java OOP** and **MySQL**

# Author and License
**OLGA KRYUKOVA**   
MIT license (It lets people do almost anything they want with the project, like making and distributing closed source versions)

# This is how it works
 The workshop's task is implementation of DAO.
 
**DAO (Data Access Object)** - is a pattern that provides an abstract interface to some type of database.
By mapping application calls to the persistence layer, 
the DAO provides some specific data operations without exposing details of the database.

The UserDAO class should offer the ability to:

* :star2: **create** - saves the object to the table as a new row;
* :star2: **read** - reads one row from the table and returns the object that represents this row;
* :star2: **update** - saves the object to the table by modifying an existing table row;
* :star2: **delete** - deletes an object from the table, that means removes a row with the same id as saved in the object
* :star2: **findAll** - reads all rows from the table, based on each a row creates a new object,
then returns a list with all created objects


 Very satisfying for me at the beginning of my Java learning :blush:
