import java.util.ArrayList;
import java.util.List;

// the purpose of this class is to initialize event objects and associate users with event and booking
public class Event {
    private int eventId;
    private String eventName;
    private String eventOrganizer;
    private String eventDate;
    private String eventTime;
    private String eventVenue;
    private String eventType;
    private int avaliableSeats;
    private int currentBookingNum;
    private List<Booking> bookings = new ArrayList<>();
    private static int no_event = 0;

    //    constructor
    public Event(String eventName, String eventDate, String eventTime, String eventVenue, int avaliableSeats, String eventOrganizer) {
        no_event++;
        eventId = no_event;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventOrganizer = eventOrganizer;
        this.eventTime = eventTime;
        this.eventVenue = eventVenue;
        this.avaliableSeats = avaliableSeats;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventOrganizer() {
        return eventOrganizer;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getEventVenue() {
        return eventVenue;
    }

    public int getAvaliableSeats() {
        return avaliableSeats;
    }

    //    method to modify the avaliable seats in an event
    public boolean modifyAvaliableSeats(int avaliableSeats) {
        if (avaliableSeats > 0){
            this.avaliableSeats = avaliableSeats;
            return true;
        }
        return false;
    }

    //    method to modify the event venue of an event
    public boolean changeEventVenue(String Venue) {
        this.eventVenue = Venue;
        return true;
    }

    //    method to modify the event timings
    public boolean changeEventTime(String newTime) {
        this.eventTime = newTime;
        return true;
    }

    //    method to modify the event Date of an event
    public boolean changeEventDate(String newDate) {
        this.eventDate = newDate;
        return true;
    }

    //    method to display all the essential information related to an event
    public void displayEventDetails() {
        System.out.println("Event Registered ID: " + eventId);
        System.out.println("Event Name: " + eventName);
        System.out.println("Event Organizer: " + eventOrganizer);
        System.out.println("Event Date: " + eventDate + ", event timings: " + eventTime);
        System.out.println("Venue: " + eventVenue);
        System.out.println("available Seats: " + avaliableSeats);
        System.out.println("current bookings: " + currentBookingNum + "\n");
    }

    //    method for associating a user with an event and make a booking.
//    the method also checks if the booking can be made or not.
    public boolean bookEvent(User user, int seatsToBook, Booking[] DataInitializerBooking) {
        if (seatsToBook <= avaliableSeats) {
            for (Booking booking : bookings) {
                if (booking.getUser() == user) {
                    System.out.println("User has already booked for this event.");
                    return false;
                }
            }
            Booking newbooking = new Booking(user, this, seatsToBook);
            bookings.add(newbooking);
            avaliableSeats = avaliableSeats - seatsToBook;
            currentBookingNum = currentBookingNum + seatsToBook;

            NormalUser testuser = (NormalUser) user;
            testuser.addBookedEvent(this);
            testuser.addBooking(newbooking);

            for (int i = 0; i < DataInitializerBooking.length ; i++) {
                if (DataInitializerBooking[i] == null) {
                    DataInitializerBooking[i] = newbooking;

                    break;
                } else {
                    continue;
                }
            }

            System.out.println("Event Name: " + eventName);
            System.out.println("EventID: " + eventId);
            System.out.println("Event Organizer: " + eventOrganizer);
            System.out.println("Registration successful, See you at " + eventVenue + " on " + eventDate);
            return true;
        }
        System.out.println("booking failed due to lack of seats");
        return false;
    }
}