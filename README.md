# signal-free-corridor
 Signal Free Corridor for Ambulances


![Alt text](images/architecture.png?raw=true "Architecture")

The various parts in the architecture shown in the image above are explained below.

1. Android Application
The system is controlled at the end-user level using an android application. The driver of the ambulance enters the source and destination of the journey in the application. Google Maps Directions API will be used to determine the closest and least time consuming path. Using GPS, location of the ambulance is sent to the server at regular intervals of time. The Google Maps Places API is used to determine the names and addresses of the coordinates that are derived from the application.

2. Server
The server interacts with the app and controls the traffic signals. It is decentralized system with a main server/root server and several smaller servers/child servers (similar to proxy servers). Each child server is responsible for controlling the traffic signals of a certain area of the city. The decentralized system leads to high degree of reliability.

When the shortest path is received from the app, the input is mapped to the child server responsible for the area in the city in which the path lies. The appropriate child server finds all the traffic signals in the path of the ambulance. Since the server is monitoring the location of the ambulance at all times, it turns the traffic signal green as the ambulance approaches the traffic signal, thus ensuring smooth passage of the vehicle. After the passage of the ambulance, the server reduces congestion on the sides that had to face the red signal.

3. Firebase Real-time Database
When the ambulance driver enters the location of the destination where he has to either pick up a patient or drop him off to the hospital, the co-ordinates are stored in the Firebase Real-time database. Also, the location of the ambulance is also constantly updated in the database. This data is accessed by the server to make its decisions.

4.Traffic Signal
The traffic signals are connected to a common network which is controlled by the server. As the ambulance approaches, the server directs the signal to turn green ensuring the smooth passage of the vehicle. After the ambulance has passed, the normal operation of the signals is resumed. NodeMCU is used along with the traffic signal in order to get data from the server and take the required action.
