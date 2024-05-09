Project Progress Update: Doctor Finder App
Team:
Boulahia mohamed abderrahmen

Note: Additionally, it's important to highlight that the project's focus shifted from an initially planned adoptify app to the Doctor Finder App. This transition prompted the integration of the sign language recognition feature, leveraging Python and CNNs, to enhance accessibility within the app.
Challenges:

Unearthed Project Challenges:
Transitioning from the original project idea of an adoptify app to reviving an old project, the Doctor Finder App, posed unexpected challenges. While the concept remained familiar, integrating new features and technologies required extensive reevaluation.
Adapting to the shift in project scope demanded a comprehensive reassessment of timelines, resources, and technical requirements.
Adaptation to Unforeseen Challenges:
The plan had to be adjusted significantly to accommodate the shift in project focus. Priorities were rearranged, and resource allocation was revised to align with the new objectives.
Incorporating the sign language recognition feature, developed using Python and CNN, presented both technical and logistical challenges. Integrating this feature seamlessly into the existing Java-based app architecture required careful planning and execution.
Unexpected Non-Technical Challenges:
Revisiting an old project brought to light organizational and documentation challenges. Reorienting ourselves with the existing codebase and project structure demanded additional time and effort.
Balancing the demands of revisiting an old project with the time constraints of the end term added pressure to the development process.
Collaboration:

Team Collaboration:
As a solo developer, collaboration primarily involved seeking feedback from mentors, peers, and industry professionals. Regular check-ins with mentors helped in refining the project roadmap and addressing technical challenges effectively.
Leveraging online communities and forums facilitated knowledge sharing and troubleshooting, particularly in areas such as integrating the sign language recognition feature.
Project Updates:

Portfolio Project Deliverables Changes:
The primary change to the portfolio project's deliverables involved transitioning from an adoptify app to a doctor finder app. This shift necessitated updates to project documentation, including the problem statement, technical choices, and feature set.
Additional emphasis was placed on integrating the sign language recognition feature as a unique selling point of the app, enhancing its accessibility and usability.
Progress:

Progress Rating (1-10): 8
Progress this week has been substantial, with significant strides made in integrating new features and refining existing functionalities.
Key milestones, including the implementation of the sign language recognition feature and enhancements to the doctor filtering algorithm, have been achieved ahead of schedule.
Certainly! Let's delve into detailing the key features mentioned for the Doctor Finder App and how to implement them:
1. Doctor Filtering by City and Speciality:
Implementation: Develop a search functionality where users can input their desired city and select a specific medical specialty from a predefined list or through a search bar.
Utilize backend logic to query the database or API for doctors matching the specified criteria.
Display the search results in a user-friendly format, possibly with options for sorting or filtering further.
2. Location and Contact Information Display:
Implementation: Integrate mapping APIs (e.g., Google Maps) to display the location of each listed doctor based on their address.
Fetch and display contact information such as phone numbers, email addresses, and clinic hours for each doctor.
Implement functionality for users to directly contact or schedule appointments with doctors through the app.
3. Medicine Alarm Feature:
Implementation: Develop a reminder system where users can set alarms for taking medication at specified times.
Utilize device notifications or push notifications to alert users when it's time to take their medication.
Implement a user-friendly interface for setting up alarms, including options for frequency, dosage, and medication names.
4. Sign Language Recognition Feature:
Implementation: Integrate a Python-based backend, leveraging Convolutional Neural Networks (CNNs) for sign language recognition.
Develop an interface within the app where users can activate the sign language recognition feature.
Utilize device camera functionality to capture and analyze users' sign language gestures in real-time.
Implement feedback mechanisms to provide users with real-time feedback on the recognized sign language gestures.

Progress Measurement:
Progress is measured based on the completion of planned tasks, adherence to project timelines, and the overall functionality and user experience of the app.
Regular code reviews and testing have helped in identifying and addressing issues promptly, ensuring steady progress towards project goals.
GitHub Link: Doctor Finder App Repository

Screenshots: in the last section with explanation



About:

The Doctor Finder App was inspired by a personal experience of struggling to find specialized medical care in a new city. This project aims to simplify the process of locating and connecting with healthcare professionals, thereby improving access to quality healthcare services.

Team Members:

Boulahia mohamed abderrahmen
GitHub Repository: Doctor Finder App Repository

Optional:
video_of_the_app_forAlxProject.mp4


Description of the application:
Home interface
In this interface you can see the list of doctors and their specialty, and images.
Also you can see in the bottom the navbar that will let you switch between the fragments.
As well you can add doctor to favorite list by clicking in the star cheackbox and the star will be colored ,also you can remove it by clicking in the same cheack box and the star will be empty
When you click on the floating hand it will send you to our additional feature sign language recognition .


Figure: Home fragment
Filter action
Also there is filter button in the top left corner when you click on it you can see a list of speciality you can choose one of them an the list of doctor will be updated and show the list of doctors of that speciality.


Figure: filter button















Search box
In the home fragment you can also click on search  button and write the name of the doctor or the phone number or the area and the list of doctor will be updated and show the result of search


Figure: Search button


Profile of the doctor
In the home fragment you can also click on the doctor and dialog box will appear and show the information of that doctor(name,speciality,phone number,adress)
In the bottom of this dialog there is three button
   when you click this button it will open (create new appointment activity) and fill the information of the doctor so you only have to fill inforamtion of the appointment
                                  
  when you click this button it will open dialer in your phone and call the doctor

   when you click this open it will open google map app and show you the direction to the doctor



Figure: Profile of the doctor

Favorite Fragment
In this fragment you can see your favorite list .




Figure: Favorite fragment




Appointment Reminder Fragment
In this fragment you can see you appointment reminders so it shows the names of the doctors and the times of appointments .
also you can see edit button and delete button so the delete button delete the appointment and cancel the alarm of appointment and edit button it will send you to edit activity so you can edit the appointment as you like.
In the right bottom corner you can see the alarm floating button this button it will send you to the create new appointment activity so you can create new custom appointment.
Note: for the known doctor(when you create new appointment from the profile of doctor) it will show you the picture of this doctor.
And for unkown doctor it will show you vector image of doctor.

Figure: Appointment Reminder Fragment
Medicine Reminder Fragment
In this fragment you can see you Medicine reminders so it shows the name of Medicine and the quantity.
also you can see edit button and delete button so the delete button delete the Medicine reminder and cancel the alarms of Medicine and edit button it will send you to edit activity so you can edit the Medicine as you like.
In the right bottom corner you can see the alarm floating button this button it will send you to the create new Medicine reminder activity so you can create new Medicine reminder.

Figure: Medicine Reminder Fragment
Create new appointment reminder Activity
In this Activity you can create new appointment Reminder so you fill the information of the doctor and date and time of appointment .
Also you shoul fill the time of alarm and the time of alarm should be befor the time of appointment to notify you.

Figure: Create new appointment reminder Activity















Create new Medicine Reminder Activity
In this Activity you can create new Medicine Reminder so you fill the information of the Medicine and the quantity you can also write note to yourself.
The difference between the create medicine reminder and the create appointment reminder that in the medicine reminder you can set more than one alarm and delete them by clicking on the delete button and the medicine alarms will alarm you daily.


Figure: Create new Medicine Reminder Activity

Set Time DialogBox
In this dialog box you can set the time of the alarm.


Figure: Set Time DialogBox




Set Date DialogBox
In this dialog box you can set the Date of appointment and the alarm.


Figure: Set Date DialogBox














Notification
When the time of appointment or medicine cames the app will send this notification.



Figure: Notification







(Additional Feature) Sign language recognition
In this Activity you can make sign of sign language with your hand and our classifier will try to predict wich sign is this.



Figure: Sign language Recognition



(Additional Feature) Results of training the model



Figure: Results of training the model

