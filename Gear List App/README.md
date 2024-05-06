# My Personal Project

## **What will the application do?**
I would like to create an app to manage and organize climbing gear, such as creating a packing list for a trip and calculate the weight of all the gear.

## **Who will use it?**
Gear intensive climbers such as trad, ice, big wall climbers.

## **Why is this project of interest to you?**
I am a trad and ice climber myself; such gear intensive climbing can require hundreds of individual pieces of gear (a climbing rack). Buying and maintaining a large climbing rack is very expensive and hard to manage. You can easily lose track of how much money you have spent or how many pieces you have in your storage.

Before each climbing trip, a climber needs to pack a specific combination of gear to suit the climbing objectives. Without a checklist it is very easy to miss a few pieces of critical gear. It would be very helpful to have an app to help organize the gear and create a packing checklist.

The weight of a climbing rack is a very important interest for most climbers, because a lighter rack can reduce muscle fatigue. I would also like to be able to calculate the weight of a rack for climbers to compare gear from different brands.

This is an app that I want to make for a long time, currently I am using Excel for gear management, and I don’t like just having a spreadsheet that doesn’t have any features.



## **User Stories:**

- As a user, I want to be able to add a piece of gear to my inventory with specs such as name, weight.
- As a user, I want to be able to view the list of gear in my inventory.
- As a user, I want to be able to mark a piece of gear as needed for the next trip.
- As a user, I want to be able to see the list of gear needed for the next trip (a packing list) and the weight of the list.
- phase 2:
- As a user, I want to be able to save my gear list to file (if I so choose)
- As a user, I want to be able to be able to load my gear list from file (if I so choose)

## **Instructions for Grader**
- phase 3:
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking the button labelled "add a piece of gear".
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking the button labelled "view list for next trip". 
The app will display a subset of the Xs, that have needed for next trip = true. It is recommended to load the existing sample gear list from file first, so you don't have to add many items by hand to see this feature.
-  Visual components are the icon in the top left corner and the main menu image.
- Save the state of the application to file by clicking the button labelled "save gear list to file".
- Load the state of the application from file by clicking the button labelled "load gear list from file".

## **Phase 4: Task 2 Instructions for Grader**
- regarding the requirement: "you must not print the messages 
to console as the events occur.", I have console messages for each 
action performed that I made for phase 3 debugging, they are not logs, the 
logs with time stamp will only be printed when you close the app.
- ### representative sample of the printed log events:
- Sun Mar 31 21:53:41 PDT 2024
-  A piece of gear is added to the app (when you add an X to Y)
-  Sun Mar 31 21:53:43 PDT 2024
-  Full Gear List is generated (when you click the view full list button)
-  Sun Mar 31 21:53:46 PDT 2024 
-  Gear List For Next Trip is generated (when you click the view list for next trip button)

## **Phase 4: Task 3, potential refactoring**
For my getGearList() and getGearListForNextTrip() methods, they are very similar in structure, the both return a string
of the gear list, with the only difference is that the getGearListForNextTrip() will filter the list. I can make
a new method for printing the string of each piece of gear and put the method into getGearList() and getGearListForNextTrip() methods,
in this way, I can reduce the duplication of the code for printing the string, and improve readability.