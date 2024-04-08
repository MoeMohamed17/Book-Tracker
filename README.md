# My Personal Project

## Book Tracker

The program aims to manage the reading list of book enthusiasts or soon to be book enthusiasts by keeping track of books they have read or wish to read in the future.
It will offer sorting capabilities, allowing users to organize their reading list based on *genre*, *audience*, *author*, and *literary form*. This will allow users to easily navigate their libraries and discover new reading material tailored to their preferences.

The target audience for this application includes avid readers, individuals looking to develop a reading habit, and anyone in between. By providing a program with a variety of sorting options, it will make it easier to manage reading lists and achieve reading goals. The project is of interest to me as it aligns with my goal of reading more. By developing an application that encourages reading and organization, I hope myself and others are able to explore and expand the literature around us and make reading a more integral part of our lives.

![Reading list!](https://images.theconversation.com/files/45159/original/rptgtpxd-1396254731.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=754&fit=clip)

## User Stories

- As a user, I want to be able to add books to my reading list so that I can keep track of what I plan to read

- As a user, I want to be able to add books to a completed list to track my reading progress

- As a user, I want to be able to view the list of books to read and books that I have read

- As a user, I want to be able to sort books by genre so that I can find books that match my current mood

- As a user, when I start the application, I want to be given the option to load my book tracker from file

- As a user, I want to be able to save my read and unread book lists to a file 

## Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Books to a BookList" by pressing on the Add Book button. 
- You can generate the second required action related to the user story by marking a book as read. Click on a book and click on the Mark as Read Button. 
- You can view the books you have read and have not read by clicking on View Read Books button and View Unread Books button respectively. 
- You can locate my visual component when the application launches 
- You can save the state of my application by clicking the save button 
- You can reload the state of my application by clicking on the load button. 

##  Phase 4: Task 2
- Here are the logged events:
- Mon Apr 01 01:23:36 PDT 2024
- Added a new book to unread books
- Mon Apr 01 01:25:29 PDT 2024
- Added a new book to unread books
- Mon Apr 01 01:25:44 PDT 2024
- Marked unread book as read

##  Phase 4: Task 3
BookList should have implemented using the Singleton design pattern. Since the application’s functionality revolves around managing a collection of books, this pattern would ensure that all parts of the application use the same instance of the book collection. To actually do this, I would implement the getinstance method to create one object of the BookList class. This approach would eliminate inconsistencies that could be a problem when having multiple instances of your book list.

The graphical user interface (GUI) of the application should have been created with multiple classes. I would have created a class for each panel and then extended appropriately. This will allow me to keep all the code for a specific part organized in its own class, improving cohesion.

