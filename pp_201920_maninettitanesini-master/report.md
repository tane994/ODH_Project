

#  **Report**  
### *Introduction*

The aim of this report is to highlight the structure of the project, explain how the workload was balanced and point out any difficulty that we might have encountered

### *Our mood and behavior*
The group was composed by Lucia Maninetti and Andres Tanesini. Although preserving synchronization and coordination during a team project isn’t an easy task, we held a very good communication through the development of the project, which allowed us to smoothly carry out the job. We always made the other aware of any problems we encountered or of any changes we decided to pursue.

### *Division of tasks*
It felt natural to divide the load of the project into two main tasks, each assigned to one of us. While Lucia was responsible for the fetching, deserializing and filtering of the data with consequent serialization of the activities, Andres was in charge of the analysis and testing part. The logger and the Javadoc were completed by both of the members equally.

### *Structure of the project*
The project was structured in the following way: a main class is responsible for starting all the operations. It does so by calling instances of different classes, each with a specific purpose, such as reading the number written in the “input.txt” file, connecting with the hub to download and deserialize the data, and so on. An important step at this point is the filtering of such information, which is then stored in json files, one for each detected activity.

The analysis is performed on the reduced data, which was previously stored in a list to make it easier to retrieve and operate with it. A document is then created in which the result of the analysis is written.

The Validator class is in charge of checking whether all the json files created during these operations are valid.

We decided to create two main packages, one containing the entities needed to retrieve, deserialize and filter the json code, and another containing the analysis.

### *Techniques used*
As it was a Java project, since the beginning we focused a lot on keeping a non-static stance. Instead to create long classes with lots of poorly related stuff and static methods in them, we decided to create more classes, each containing a small portion of code and a constructor. This way we could easily access the methods by creating instances of the classes.

The retrieved data was treated as an InputStream, which was then passed to a proper method to be deserialized thanks to an ObjectMapper. During the deserializing process we made use of json setters for identifying the properties, and the @JsonIgnoreProperties tag to skip over superfluous ones.

The analysis was completed using streams, which made it easier to perform the needed operation on the available amount of data.

For handling files, both the input and the json ones, we relied on a useful tool, the ClassLoader.

Logging was planned according to the possible messages a user could receive whenever it was just an information about the current action of the program, we logged them as an info, in case of malfunctions we logged them as errors.

### *Difficulties and considerations*

We didn’t encounter lots of difficulties and challenges, however we decided to list here the main things that gave us some thoughts.

While Andres thought that the streams (mainly employed in the Analyzer class) revealed themselves to be very helpful, but their use wasn’t very intuitive at first. The testing also required some more extra planning and researches before we headed into that part.

Lucia was already more familiar with the project, as she had already worked on it in sight of the previous session, so she already knew how to deal with most parts of it.

Overall, this project revealed itself to be a good opportunity to practice what we learnt during the course in a more concrete setting. It also gave us a good insight of what it means to work together as a team on an application program, both on the technical and human side.