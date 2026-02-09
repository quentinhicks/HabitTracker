<img width="1891" height="932" alt="image" src="https://github.com/user-attachments/assets/0d8e490f-78b3-4564-a352-2124af5d22a2" />

# System Tracker -- an idea for maintaining consistency

During my first two semesters at Princeton, I kept running into a problem. Due to the demanding nature of the STEM coursework, I found that I was unable to keep many of the healthy habits that I cared so much about keeping. When I tried to maintain healthy habits, I found that I was *overly ambitious* -- there were just too many activities that I would end up losing motivation for.

I developed this tool during the first week before coming back to Princeton to solve this problem. The idea is simple: the local implementation offered me an interface to be able to input habits (split between daily habits and additional weekly habits) and track my adherence to the habits that I set for myself. Underneath was an implementation which would evaluate my performance and assess whether I should increase my habits, decrease, or maintain at the current level.

When I created this, I was not interested in being a programmer, but now since I'm applying for software engineering roles I'm uploading it for proof of basic experience and skill.

## Implementation features

To complete this project, I played around with the following ideas:
* Building a lightweight Java project using the build tool Maven
* Utilizing SparkJava as a way to connect between the web interface and the backend
* Frontend design decisions and frameworks (the interface is intended to be minimal -- Times New Roman is one of my favorite fonts)
  * CSS grid library 
* Using HTML, CSS for designing the frontend
* Using Javascript to add scripting between the frontend and backend
  *  Implemented automatic table resizing and interactive color change
* JSON files to allow for the storage and transfer of information from the frontend to the backend
* Java as the backend language -- utilizing various data structures and functionalities to evaluate and run code
  * Including habit evaluation, saving and loading files, and a mistake journal (for days where my habit journey went awry)
