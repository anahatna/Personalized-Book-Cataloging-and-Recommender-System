# *My Virtual Bookshelf*
##### Anahat Nagra
Java Application Using OOD

## Overview

The purpose of a virtual book journal is so the user is able to keep
track of the books they have read and be
recommended similar books to the ones they like.


#### *Further details*:

As mentioned in the overview, the main functionality of this app is to
allow the people using it to have an organized record of the books that they've read.
As they record their most recent read, the user will be prompted to give the book a personal rating out of 5 stars, 
select the genre(s) of that book, and give a short review. After adding the book to their record,
they will also have to option to be recommended books that they might like (based on what rating they have their most recent read)


#### *Who will use this app?*
- Book lovers! (so they can keep track of the books they've been reading)
- People who want to read more and be given recommendations based on they are interested in

The reason I chose to create this project is because I am a book lover myself.
I find myself keeping track of all the books I've read in an actual journal (which can be tedious) 
so I thought it would be interesting/useful to create an application that not only keeps a record of
everything that I've read, but also suggests other books to read.
This is an application that can be a very helpful tool used to arrange and organize a readers books!


## User Stories

- As a user, I want to be able to add books to the list of books I've read
- As a user, I want to be able to remove books from the list of books I've read
- As a user, I want to be able to see the list of books I've read already
- As a user, I want to be able to rate a book out of 5
- As a user, I want to be able to select the genre of a book from the options I'm given
- As a user, I want to be able to get book recommendations
- As a user, I want to be given to option whether I want to continue with the application or not
- As a user, I want to be able to save the list of books I've read to file
- As a user, I want to be able to be able to load the list of books I've read so far from file 

## Phase 4: Task 2

Wed Mar 30 16:41:21 PDT 2022 <br>
Book was added to the library <br>
Wed Mar 30 16:41:27 PDT 2022 <br>
Book was added to the library <br>
Wed Mar 30 16:41:29 PDT 2022 <br>
Book was removed from the library <br>

## Phase 4: Task 3

The design for my program is fairly good but there are still things I can do to increase cohesion.
For example, I think I could have added the list of book recommendations for each genre in the Genre class instead of 
the Book class. This would improve the SRP because the Genre class would be responsible for the book recommendations 
based off of the genre, rather than the Book class, which would make more sense. (I could refactor this by moving the 
recommendations methods into the genre enum class). I also have a lot of duplicate code in my main GUI and the 
ViewLibrary GUI, so what I could do to improve that is identify the duplicates and pull them into methods.
