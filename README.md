# Basic-Doctor-Management-PRO192

Write a program to manage the doctor information below:
Display the menu:
1. Add Doctor
2. Update Doctor.
3. Delete Doctor
4. Search Doctor.
5. Exit.
- If the user chooses 1 then add one record to data including Code(String), Name(String), 
Specialization(String), Availability(int).
- If user chooses 2: then request enter the code. If it does not exist Code, the notification 
"Doctor code does not exist". Otherwiseuser can edit of the remaining information. If 
Information is blank then not change old information.
- If user chooses 3: the Code requires the user to enter Code and delete information if Code 
exists, if Code does not exist,, the notification "code does not exist Doctor".
- If user chooses 4: require user to enter search strings, search and returns the list for users.
- If user chooses 5: exit the program.

  
Function details:

Function 1: Display GUIand Input Data.
• User runs the program. The program prompts users input Data.
• Auto next Function2.
Function 2: Perform function based on the selected option.
• Option 1: Add Doctor
o Require to enter task information including“code, name, specialization, 
availability”.
o Check the valid data following conditions:
▪ Code is not null or duplicate in the DB.
o Add Doctor to the program.
o Return to the main screen.
• Option 2:Update Doctor
o Require to enter Code (id) and the data to be modified.
o Check the valid data with the conditions below:
▪ Code (id) must exist in the DB.
o Update Doctor in the program.
o Return to the main screen.
• Option 3:Delete Doctor
o The requirement to enter Code (id).
o Check valid data with the conditions below:
▪ Code(id) must exist in the DB.
o Delete the doctor information.
o Return to the main screen.
• Option 4: Search Doctor.
o Require to enter strings to search.
o Search data and displayon the screen
o Return to the main screen.
• Option 5:Exit the program.
