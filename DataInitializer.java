// the purpose of this class is to act as a in program database.
// this class stores all the objects of different classes
public class DataInitializer {
    public static Event[] initializeEvents() {
        // Event initialization method providing an array of event objects
        Event[] events = new Event[15]; // allowed max limit of the number of events
        events[0] = new Event("test", "20 oct 2023", "20:00hrs","City Palace", 100, "balvendra Bahubali");

        return events;
    }

    public static User[] initializeUsers() {
        // User initialization method providing an array of user objects
        User[] users = new User[50]; // current max limit of the number of users

        // Initialize some admin users
        users[0] = new Admin("Jainil Jain", "OOPS", "Slayer.respwan@gmail.com");
        users[1] = new NormalUser("ballu", "123", "ballu26172@gmail.com");

        return users;
    }

    public static Booking[] initializeBookings() {
//        booking initialization method providing an array of booking objects
        Booking[] bookings = new Booking[100];

        return bookings;
    }
}