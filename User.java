import java.util.ArrayList;
import java.util.List;

// Basic user interface which defines the basic methods required in the system
public interface User {
    String getuserName();
    String getPassword();
    String getContact();
    List<Event> getBookedEvents();
    List<Booking> getBookings();
    boolean isAdmin();
    void displayInfo();
}

// implemented Admin class with admin specific entries and methods.
class Admin implements User {
    private String userName;
    private String password;
    private String contactInfo;
    private boolean isAdmin = true;

    //    constructor
    public Admin(String userName, String password, String contactInfo) {
        this.userName = userName;
        this.password = password;
        this.contactInfo = contactInfo;
    }

    public String getuserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getContact() {
        return contactInfo;
    }

    @Override
    public List<Booking> getBookings() {
        return null;
    }

    @Override
    public List<Event> getBookedEvents() {
        return null;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    //    boolean method that returns whether the new event is successfully added into the system or not.
    public boolean addNewEvent(String eventName, String eventDate, String eventTime, String eventVenue, int avaliableSeats, String eventOrganizer, Event Events[]) {
        Event event = new Event(eventName, eventDate, eventTime, eventVenue, avaliableSeats, eventOrganizer);

        boolean creation = false;

        for (int i = 0; i < Events.length; i++) {
            if (Events[i] == null) {
                Events[i] = event;
                creation = true;
                int eveid = Events[i].getEventId();
                System.out.println("Event Registered with ID = " + eveid);
                break;
            } else {
                creation = false;
            }
        }
        return creation;
    }

    //    display method which prints all the essential user information
    @Override
    public void displayInfo() {
        System.out.println("User Name: " + userName);
        System.out.println("User Password: " + password);
        System.out.println("Contact information: " + contactInfo);
        System.out.println("is user admin: " + isAdmin + "\n");
    }
}

// implemented NormalUser class with specific entries and methods.
class NormalUser implements User {
    private String userName;
    private String password;
    private String contactInfo;
    private boolean isAdmin = false;
    private List<Event> bookedEvents;
    private List<Booking> bookings;

    //    constructor
    public NormalUser(String userName, String password, String contactInfo) {
        this.userName = userName;
        this.password = password;
        this.contactInfo = contactInfo;
        bookedEvents = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    @Override
    public String getuserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getContact() {
        return contactInfo;
    }

    @Override
    public boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public List<Booking> getBookings() {
        return bookings;
    }

    @Override
    public List<Event> getBookedEvents() {
        return bookedEvents;
    }

    //    method which prints all the booking details about the bookings made by the user.
    public void getAllBookings() {
        for (Booking booking : bookings) {
            booking.displayBookingDetails();
        }
    }

    //    method which prints all the events the user booked in for.
    public void getAllBookedEvent() {
        for (Event event : bookedEvents) {
            event.displayEventDetails();
        }
    }

    //    method to add a specific event object which the user booked for.
    public void addBookedEvent(Event event) {
        bookedEvents.add(event);
    }

    //    method to add the specific booking object associated with a booking
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    //    display method to print all the essential information about the user.
    @Override
    public void displayInfo() {
        System.out.println("User Name: " + userName);
        System.out.println("User Password: " + password);
        System.out.println("Contact information: " + contactInfo);
        System.out.println("is user admin: " + isAdmin + "\n");
    }
}