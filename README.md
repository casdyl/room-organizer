# My Personal Project

## Organize Me!

What my project will do:
- Take given dimensions and display all possible arrangements of furniture in a room
- Give top arrangements based on most variations of consecutive square feet (free space)
- Allow you to visualize the different ways you can reorganize a space without having to actually *move* anything and 
realizing it won't work

This application will allow the user to enter the dimensions of their room and what furniture they have, returning all 
possible combinations of room design variations. User's can either **enter** the exact measurements, or pick from a 
**list** of common sizes.

This can be used for any space, be it a bedroom, office, living room, etc.

This project is of interest to me because I love rearranging spaces, and often rearrange my room 4 or 5 times a year to
refresh the space. Oftentimes, I will move everything around to find that there is not enough space, and then be forced
to move things *again*. If I were able to enter in my furniture and room size, and see the possible combinations of 
space, it would save me lots of time and energy and I would be able to commit more time to making the space look nice
rather than trying to make everything fit.

## User Stories 

### Phase 1
- As a user, I want to be able to create a new room
- As a user, I want to be able to select a room and add a piece of furniture to it
- As a user, I want to be able to view a current list of furniture in that room
- As a user, I want to be able to remove furniture from a room

### Phase 2
- As a user, I want to be able to save my room to file
- As a user, when I start the application, I want to load my room from file

### Phase 4: Task 2
- Room throws FurnitureDoesNotFit Exception
- Room throws FurnitureNotFound Exception
- Furniture in Room is a HashMap

### Phase 4: Task 3
**UML Class Design Diagram**
- I would refactor the GUI class to make it shorter and more easily readable
- break it up further into UI classes so that it the code is more broken up between classes and not all in one method
- this would make it easier to read and make small changes with less chance of breaking the code
- make the Room class more cohesive for adding and removing furniture that fits
