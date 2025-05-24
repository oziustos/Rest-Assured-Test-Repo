package resources;

import pojo.AuthBody;
import pojo.createBooking.BookingDates;
import pojo.createBooking.CreateBookingBody;

public class TestDataBuild {
    public AuthBody auth(String username, String password) {
        AuthBody authBody = new AuthBody();
        authBody.setPassword(password);
        authBody.setUsername(username);

        return authBody;
    }

    public CreateBookingBody createBooking(String firstname, String lastname, int totalprice,
                                           boolean depositpaid, String checkin, String checkout,
                                           String additionalneeds) {
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(checkin);
        bookingDates.setCheckout(checkout);

        CreateBookingBody createBooking = new CreateBookingBody();
        createBooking.setBookingdates(bookingDates);
        createBooking.setFirstname(firstname);
        createBooking.setLastname(lastname);
        createBooking.setDepositpaid(depositpaid);
        createBooking.setTotalprice(totalprice);
        createBooking.setAdditionalneeds(additionalneeds);

        return createBooking;
    }
}
