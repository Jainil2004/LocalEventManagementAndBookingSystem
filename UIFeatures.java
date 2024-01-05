// this class provides lower level functionality to the Main program.
// the purpose of this class is to help in error handling and to avoid writing the same code multiple times
public class UIFeatures {

    //    static method which returns the event object based on the unique event ID
    public static Event getEventById(int eventID, Event events[]) {
        Event ac_event = null;
        for (Event event : events) {
            if (event == null) {
                System.out.println("Event with specified eventID not found");
                break;
            }
            if (event.getEventId() == eventID) {
                ac_event = event;
                break;
            }
        }
        return ac_event;
    }


    public static Booking getBookingById(int bookingID, Booking[] bookings) {
        Booking booking_ac = null;
        for (Booking booking: bookings) {
            if (booking == null) {
                System.out.println("booking with specified Special Booking ID not found");
                break;
            }
            if (booking.getSpecialID() == bookingID) {
                booking_ac = booking;
                break;
            }
        }
        return booking_ac;
    }

    //    static method which returns a event that needs to be removed from the system based on users request
    public static int eventRemover(int eventID, Event events[]) {
        int ac_event_id = -1;  // Initialize to a special value, -1, to indicate "not found."
        for (int i = 0; i < events.length; i++) {
            if (events[i].getEventId() == eventID) {
                ac_event_id = i;
                break;
            }
        }
        return ac_event_id;
    }

    //    static method to findout weather the user is a admin or not
    public static boolean isAdmin(User user) {
        return user.isAdmin();
    }

    //    static method to print all the available operations in the system
    public static void operationsMenu() {
        System.out.println("Available Operations (Normal Users)");
        System.out.println("register: For new Registration");
        System.out.println("login: For logging in the system");
        System.out.println("listEvents: To list all the available Events");
        System.out.println("searchEvent: to search for a specific event using EventID");
        System.out.println("bookEvent: to book an event");
        System.out.println("bookingDetails: to get specific bookingDetails");
        System.out.println("allBookedEvents: to check all the events booked in");
        System.out.println("OPSPanel: to re-print this menu");
        System.out.println("Logout: to logout");
        System.out.println("exit: to exit the System\n");
        System.out.println("Admin Specific Operations");
        System.out.println("users: to list all the registered users in the system");
        System.out.println("addEvent: to add an event in the system");
        System.out.println("removeEvent: to remove an event from the system");
        System.out.println("modifyEventDetails: to modify specific event details");
        System.out.println("bookings: to list all the bookings made in the system\n");
    }
}