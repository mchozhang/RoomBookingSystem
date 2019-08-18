# Booker - Room Reservation System
Wenhao Zhang 970012  
Dulki Thalalle-Hewage 879076

## Overview
Booker is a system that provides room reservation (in a group of associated hotels) capabilities for the customers and reservation management capabilities to the hotel staff. This will be used for business purposes and the main users will be customers and hotel staff.

Through the Booker website, customers can search for rooms according to their requirements, view the information about the rooms and hotel and make a booking. The system also provides functions allowing customers to manage their bookings, such as modifying or canceling a booking within a given time.

Hotel staff can upload and modify the information of the hotel(specific to their assigned hotel), edit the room catalogue details and make confirmation of the bookings from customers.



## Properties of the System
* Persistent data - Data will be persistently stored in database after being created and modified
* Huge amount of data - hotel information, room catalogue, booking details etc.
* Concurrent access - Customers and staff have concurrent access to manage bookings
* Multiple user Interfaces- User interfaces for customers and staff will be different and based on their role they can perform different actions.
* Business logic - room reservation process, booking confirmation and cancellation policy



## Roles
**Customers**: customers who want to make a hotel room reservation  
**Staff**: hotel staff who manage the information and bookings of a hotel




## Feature A - Hotel Information Management

Hotel staff manages information of the hotel and the catalogue of rooms of their hotel, which will appear in the search for customers. Staff can create blocks in the catalogue to add new items(rooms), view their details, update details or delete items (rooms).Staff will be using their staff login to access the system and will have the capability to perform the actions mentioned in use cases. Staff will have concurrent access to the system , where many staff users can do catalogue changes at the same time as well as customers can search for rooms and make bookings.  

**Properties related to feature A**: The system and data should be persistent between all actions performed by users in the use cases given below. To support feature A, system should be able to hold high amounts of data. This feature will also use multiple user interfaces, namely customer interface and staff interface. Staff and customers have concurrent access into the system, where all staff have access to editing hotel and catalogue details and customers have access to view hotel and catalogue details.


### Use Cases:
* Staff edit hotel information  
Staff can upload and edit the information of the hotel, which includes the name, description, location and phone number.
* Staff create room catalogs of their hotel  
Staff can create new room catalogues that are provided by their hotel, he or she should provide the price, type and capability of the newly created catalogue item. Furthermore, staff can add rooms to a catalogue, he or she should also provide the ID of each of the room added. For example, a room catalogue item can be single, standard double or luxury, and the staff adds 10 rooms under this catalogue item.
* Staff modify room catalogue  
Staff can edit the name of type, prices and capability of a room catalogue item, and can also modify the number and IDs of the rooms under a catalogue item.  However, according to business logic the changes won’t apply for existing bookings.
* Staff delete item from catalogue  
Staff can remove a room catalogue items of their hotel and the rooms under this catalogue item. Again this won’t affect the existing bookings.
* User can search and view the information of hotels  
User with or without logging in can search hotels by name, price or location, and he or she is able to view the information, room catalogue and room availability of a hotel.



## Feature B - Room Booking Management
Booker provides advanced search function which allows customers to find hotels that meet their requirements, they can view the detailed information of the hotel and create bookings to reserve a room of the chosen hotel.Customers can search through the website without login in, however to make a booking they will have to login. Customer login will have the capabilities to make, view, update or delete a booking. Once the booking is made by customer, staff can login using staff interface to confirm the booking. Customer can delete a booking before the start date (business logic).  

**Properties related to feature B**: Data should be persistent in the system before and after user performs actions mentioned below in use cases. The system should be able to hold many bookings. Customers and staff have different user interfaces to access the system and perform these actions.Customers and staff also have concurrent access into bookings, where customers can perform all CRUD operations in bookings while staff can view and update the status of the booking.

### Use Cases
* Customer create a booking  
This gives the customer the capability to make a room reservation, he or she should choose the room under a room catalogue of a hotel, and select the dates. This will generate a booking item in the system.
* Customer view booking details  
Customers can check the details of bookings that they have created, which includes hotel name, room catalogue and ID, price and status. The status of a book will be “Waiting for confirmation” after being newly created, “Confirmed” after confirmed by hotel staff and “Finished” after the date passed.
* Customer can modify the booking  
Customers are allowed to change some information of a new booking before its start date, which includes the date of booking period, the room selection. After customer update a booking, the booking status will become “Waiting for confirmation”.
* Customer can cancel the booking  
Customers are allowed to cancel a booking before its start date, if they do so, the item of booking will be removed completely.
* Staff can update the status of a customer booking  
Staff can confirm a booking created by customers, then the status of the booking will become “Confirmed”, if the customer updates the booking after this point, the booking will need to be reconfirmed by staff. Staff can also change the booking status to “Finish” after the customer checks out.



## CRUD operations related with data entities and roles

|          | Hotel | Catalogue | Room | Booking |
| -------- | ----- | --------- | ---- | ------- |
| Customer | R     | R         | R    | CRUD    |
| Staff    | RU    | CRUD      | CRUD | RU      |
