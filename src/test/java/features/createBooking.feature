Feature: Create Booking

  #Pozitive scenarios
  Scenario Outline: Verify that the user can successfully create a booking with valid credentials
    Given User prepares a valid booking payload with "<firstname>", "<lastname>", "<totalprice>", "<depositpaid>", "<checkin>", "<checkout>", "<additionalneeds>"
    When the user sends a "POST" request to the "booking" endpoint
    Then the user should receive status code 200 in the response
    And the response body should has "bookingid" item and it should a "integer"
    And "booking.totalprice" in response body equals "<totalprice>"
    When the user sends a "GET" request to the "booking/{bookingId}" endpoint
    Then "firstname" in response body equals "<firstname>"
    And "lastname" in response body equals "<lastname>"

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Jim       | Brown    | 111        | true        | 2018-01-01 | 2019-01-01 | Breakfast       |
     # | Anna      | Smith    | 222        | false       | 2020-05-01 | 2020-05-15 | Lunch           |
      #| John      | Doe      | 333        | true        | 2022-10-01 | 2022-10-10 | Dinner          |
