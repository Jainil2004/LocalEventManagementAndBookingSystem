// the purpose of this class is to keep track of all the booking details
public class Booking {

    private static int bookingID = 0;
    private int specialID;
    private User user;
    private Event event;
    private int seatsBooked;

    //    constructor
    public Booking(User user, Event event, int seatsBooked) {
        this.user = user;
        this.event = event;
        this.seatsBooked = seatsBooked;
        bookingID++;
        this.specialID = bookingID;
    }

    public User getUser() {
        return user;
    }

    public int getSpecialID() {
        return specialID;
    }
    public Event getEvent() {
        return event;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    //    display method which prints all the essential details about a booking.
    public void displayBookingDetails() {
        System.out.println("Booking Details");
        System.out.println("Special Booking ID = " + specialID);
        System.out.println("Event: " + event.getEventName() + "\nEvent Organizer: " + event.getEventOrganizer());
        System.out.println("Booked By: " + user.getuserName());
        System.out.println("Seats booked: " + seatsBooked + "\n");
    }
}