import java.util.Scanner;

public class LocalEventManagementSystem {
    static {
        System.out.println("LocalEventManagementSystem Active");
        System.out.println("Version: v1.0.0-beta1\n");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        data Storage Units for storing all the relevant data
        Event[] events = DataInitializer.initializeEvents();
        User[] users = DataInitializer.initializeUsers();
        Booking[] bookings = DataInitializer.initializeBookings();

//        program variables for operating the system
        String operation = null, userName, password, email, eventName, eventDate, eventTime, eventVenue, eventOrganizer;
        boolean status;
        User currentUser = null;
        int avaliableSeats = 0, eventID, selectedSeats, bookingID;
        byte microoperation;
        String Strmicrooperation = null;

        System.out.println("welcome to The local Event Management and Reservation system");

        UIFeatures.operationsMenu();

        while (!"Exit".equals(operation)) {
            try {
                System.out.print("Please enter a operation: ");
                operation = scanner.nextLine();

                switch (operation) {
//                case so that the user can register in the system to use it
                    case "register":
                        System.out.println("please enter your credentials for new registration");
                        System.out.print("User Name: ");
                        userName = scanner.nextLine();
                        System.out.println("password: ");
                        password = scanner.nextLine();
                        System.out.println("Email: ");
                        email = scanner.nextLine();

                        status = UserAuthentication.registerUser(userName, password, email, users);

                        if (status) {
                            System.out.println("user registration successful");
                        } else {
                            System.out.println("user registration un-successful (user with username already exists) ");
                        }
                        break;

//                    case so that the user can login into the system and use other cases
                    case "login":
                        if (currentUser != null) {
                            System.out.println("A User is already logged in. Please first logout then login with another registered credentials");
                            break;
                        }
                        System.out.print("User Name: ");
                        userName = scanner.nextLine();
                        System.out.print("password: ");
                        password = scanner.nextLine();

                        for (int i = 0; i < users.length; i++) {
                            if (users[i] != null && users[i].getuserName().equals(userName) && users[i].getPassword().equals(password)) {
                                currentUser = users[i];
                                System.out.println("welcome " + currentUser.getuserName());
                                break;
                            } else {
                                continue;
                            }
                        }
                        if (currentUser == null) {
                            System.out.println("user with the provided credentials doesnt exist");
                        }
                        break;

//                    case so that the system admin can check all the registered users in the program
                    case "users":
                        if (currentUser != null && UIFeatures.isAdmin(currentUser)) {
                            for (User user : users) {
                                if (user != null) {
                                    user.displayInfo();
                                } else {
                                    break;
                                }
                            }
                        } else {
                            System.out.println("Admin confirmation Failed. Unauthorized access denied");
                        }
                        break;

//                    case so that a normal user can get the list of all the avaliable events in the system
                    case "listEvents":
                        if (currentUser != null) {
                            for (Event event : events) {
                                if (event != null) {
                                    event.displayEventDetails();
                                } else {
                                    break;
                                }
                            }
                        } else {
                            System.out.println("Please login before continuing ahead.");
                        }
                        break;

//                    case so that a admin can add a new event in the system.
                    case "addEvent":
                        if (currentUser != null && UIFeatures.isAdmin(currentUser)) {
                            System.out.println("Admin confirmation successful");
                            Admin admin = (Admin) currentUser;

                            System.out.print("please enter event specific details\n");
                            System.out.print("Event Name: ");
                            eventName = scanner.nextLine();
                            System.out.print("Event Date: ");
                            eventDate = scanner.nextLine();
                            System.out.print("Event timing: ");
                            eventTime = scanner.nextLine();
                            System.out.print("Event Venue: ");
                            eventVenue = scanner.nextLine();
                            System.out.print("Event Organizer: ");
                            eventOrganizer = scanner.nextLine();
                            System.out.print("Maximum Event Attendees allowed: ");
                            avaliableSeats = scanner.nextInt();
                            scanner.nextLine();

                            status = admin.addNewEvent(eventName, eventDate, eventTime, eventVenue, avaliableSeats, eventOrganizer, events);
                            if (status) {
                                System.out.println("Event successfully added in the system");
                            } else {
                                System.out.println("Event addition failed");
                            }
                        } else {
                            System.out.println("Admin confirmation unsuccessful. Unauthorized Access Denied");
                        }
                        break;

//                    case so that a admin can remove a event from the system
                    case "removeEvent":
                        if (currentUser != null && UIFeatures.isAdmin(currentUser)) {
                            System.out.println("Admin confirmation successful");
                            Admin admin = (Admin) currentUser;

                            System.out.println("please enter the eventID associated with the event you would like to remove");
                            eventID = scanner.nextInt();
                            scanner.nextLine();

                            Event removedEvent = UIFeatures.getEventById(eventID, events);
                            if (removedEvent != null) {
                                System.out.println("event with eventID " + eventID + " found");
                                System.out.print("are you sure you want to remove this event from the system (removed events cannot be restored once deleted) (yes/no):  ");
                                Strmicrooperation = scanner.nextLine();
                                if (Strmicrooperation.equals("yes")) {
                                    int removeEvent_index = UIFeatures.eventRemover(eventID, events);
                                    events[removeEvent_index] = null;
                                    System.out.println("Event Removal Successful");
                                } else {
                                    System.out.println("Event removal Aborted");
                                    break;
                                }
                            } else {
                                System.out.println("event with ID = " + eventID + " not found. please check and try again");
                            }
                        } else {
                            System.out.println("Admin Confirmation unsuccessful. UnAuthorized Access Denied");
                        }
                        break;

//                    case which allows a admin to modify event details like date, time etc
                    case "modifyEventDetails":
                        if (currentUser != null && UIFeatures.isAdmin(currentUser)) {
                            System.out.println("Admin confirmation successful");
                            Admin admin = (Admin) currentUser;

                            System.out.println("please enter the eventID associated with the event you would like to modify");
                            eventID = scanner.nextInt();
                            scanner.nextLine();
                            Event modifyingEvent = UIFeatures.getEventById(eventID, events);
                            if (modifyingEvent != null) {
                                System.out.println("please select one of the following options:");
                                System.out.println("1. Change Maximum avaliable Seats in the event");
                                System.out.println("2. Change Event Venue");
                                System.out.println("3. Change Event Date");
                                System.out.println("4. Change Event Timing");
                                System.out.print("please enter your choice: ");
                                microoperation = scanner.nextByte();
                                scanner.nextLine();

                                switch (microoperation) {
                                    case 1:
                                        System.out.println("please enter the avaliable seats for the event");
                                        avaliableSeats = scanner.nextInt();
                                        scanner.nextLine();
                                        if (modifyingEvent.modifyAvaliableSeats(avaliableSeats)) {
                                            System.out.println("modification successful. Maximum seats changed successfully");
                                        }
                                        break;
                                    case 2:
                                        System.out.println("please enter the new venue for the event");
                                        eventVenue = scanner.nextLine();
                                        if (modifyingEvent.changeEventVenue(eventVenue)) {
                                            System.out.println("modification successful. Venue changed successfully");
                                        }
                                        break;
                                    case 3:
                                        System.out.println("please enter the new Event Date");
                                        eventDate = scanner.nextLine();
                                        if (modifyingEvent.changeEventDate(eventDate)) {
                                            System.out.println("modification successful. event dates changed successfully");
                                        }
                                        break;
                                    case 4:
                                        System.out.println("please enter the new Event timing");
                                        eventTime = scanner.nextLine();
                                        if (modifyingEvent.changeEventTime(eventTime)) {
                                            System.out.println("modification successful. event timings changed successfully");
                                        }
                                        break;
                                    default:
                                        System.out.println("provided option is not avaliable. Please try again");
                                        break;
                                }
                            }
                        }
                        break;

//                    case which allows a user to search for an event in the system using the Unique autogenerated ID.
                    case "searchEvent":
                        if (currentUser != null) {
                            System.out.print("please enter the eventID for the event you would like to search for: ");
                            eventID = scanner.nextByte();
                            scanner.nextLine();

                            Event searchedEvent = UIFeatures.getEventById(eventID, events);
                            System.out.println("Event with ID = " + eventID + " found");
                            System.out.println("Event Details");
                            searchedEvent.displayEventDetails();
                        } else {
                            System.out.println("Please login before continuing ahead.");
                        }
                        break;

//                    case which allows a user to book seats in a Event
                    case "bookEvent":
                        if (currentUser != null && !UIFeatures.isAdmin(currentUser)) {
                            System.out.println("Please enter the EventID of the event you would like to book: ");
                            eventID = scanner.nextInt();
                            scanner.nextLine();

                            Event selectedEvent = UIFeatures.getEventById(eventID, events);
                            if (selectedEvent != null) {
                                System.out.println("Seats currently available for the selected event: " + selectedEvent.getAvaliableSeats());
                                System.out.println("Please enter the number of seats you would like to book:");
                                selectedSeats = scanner.nextInt();
                                scanner.nextLine();

                                selectedEvent.bookEvent(currentUser, selectedSeats, bookings);
                            }
                        } else {
                            System.out.println("Event booking unavailable. Please login first");
                        }
                        break;

//                    case which allows a user to look up all the booking details made by the user
                    case "bookingDetails":
                        if (currentUser != null) {
                            NormalUser temp = (NormalUser) currentUser;
                            temp.getAllBookings();
                        }
                        break;

//                    case which allows the user to view all the events he/she booked in.
                    case "allBookedEvents":
                        if (currentUser != null) {
                            NormalUser temp = (NormalUser) currentUser;
                            temp.getAllBookedEvent();
                        }
                        break;

//                    case for admins which allows a admin to see all the bookings made in the system
                    case "bookings":
                        if (currentUser != null && UIFeatures.isAdmin(currentUser)) {
                            for (Booking booking : bookings) {
                                if (booking != null) {
                                    booking.displayBookingDetails();
                                } else {
                                    break;
                                }
                            }
                        } else {
                            System.out.println("Admin confirmation Failed. Unauthorized access denied");
                        }
                        break;

//                    case to re-print the operations panel present in the UIFeatures class
                    case "OPSPanel":
                        UIFeatures.operationsMenu();
                        break;

//                    case which is used by a user to logout from the system. as at any given time there can only be a single user in the system
                    case "logout":
                        if (currentUser != null) {
                            System.out.println("Thank you for using the Local Event Management System");
                            currentUser = null;
                            System.out.println("User successfully logged out");
                        } else {
                            System.out.println("No User is logged in the system");
                        }
                        break;

//                    case to exit from the system
                    case "exit":
                        System.out.println("made by Jainil Jain");
                        System.exit(0);
                        break;

//                    default case when invalid operation is entered
                    default:
                        System.out.println("invalid operation entered. please check the OPS panel and try again");
                        break;
                }

            } catch (Exception e) {
                System.out.println("an error occured " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}