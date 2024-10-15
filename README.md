Link for the presentation on youtube
https://youtu.be/GDALtDKTC_M

GasTrackerX

Overview
GasTrackerX is a vehicle fuel consumption tracking application that allows users to record and monitor gas fillings for their vehicles. Users can input details such as fuel volume, current mileage, and then view both overall consumption and fuel efficiency after each filling. The app provides clear insights into fuel usage and offers a simple way to manage multiple vehicles.

Features

•	Add, edit, and delete vehicles.

•	Log gas fillings (fuel volume and current mileage).

•	Calculate and display overall fuel consumption.

•	Display fuel consumption since the last fill.

•	Responsive and user-friendly interface.


Setup and Installation

Prerequisites

To run this application, ensure you have the following installed:

•	Java

•	Spring Boot

•	MySQL/PostgreSQL


Steps

1.	Clone this repository:
git clone https://github.com/your-username/gastrackerx.git
2.	Navigate to the project directory:
cd gastrackerx
3.	Set up your database and configure the application.properties file with your database credentials.
4.	Build and run the application:
./mvnw spring-boot:run
5.	Navigate to http://localhost:8080 in your browser to access the app.

API Endpoints

Vehicles

•	GET /api/v1/vehicles/user/{userId} - Get all vehicles for a user.

•	POST /api/v1/vehicles/user/{userId} - Add a new vehicle.

•	DELETE /api/v1/vehicles/{vehicleId} - Delete a vehicle.

Gas Fillings

•	POST /api/v1/gasfillings/vehicle/{vehicleId} - Add a gas filling for a vehicle.

•	GET /api/v1/gasfillings/vehicle/{vehicleId} - Get all gas fillings for a vehicle.

•	DELETE /api/v1/gasfillings/vehicle/{vehicleId} - Delete gas fillings for a vehicle.

Future Enhancements

•	Add user authentication and authorization.

•	Provide visual data charts for gas consumption trends.
•	Enable filtering and exporting of vehicle data.
License
This project is licensed under the MIT License.

