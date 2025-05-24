Feature: Ping

  Scenario: API should be up and running
    Given the user has the restful-booker URL
    When the user sends a "GET" request to the "ping" endpoint
    Then the user should receive status code 201 in the response