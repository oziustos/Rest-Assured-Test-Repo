Feature: Auth

  #Positive scenarios
  Scenario: Verify user successfully auth with valid credentials
    Given the user has the valid auth payload
    When the user sends a "POST" request to the "auth" endpoint
    Then the user should receive status code 200 in the response
    And the response body should has "token" item and it should a "string"

 #Negative Scenarios
  Scenario: Verify that authentication fails when no request body is sent
    Given the user has the restful-booker URL
    When the user sends a "POST" request to the "auth" endpoint
    Then  the user should receive a 200 OK response
    And "reason" in response body equals "Bad credentials"

  Scenario: Verify that authentication fails when the username field is empty in the request payload
    Given the user has auth request with the "username" field set to "" in the request body
    When the user sends a "POST" request to the "auth" endpoint
    Then  the user should receive a 200 OK response
    And "reason" in response body equals "Bad credentials"

  Scenario: Verify that authentication fails when the username field is equals space (" ") character in the request payload
    Given the user has auth request with the "username" field set to " " in the request body
    When the user sends a "POST" request to the "auth" endpoint
    Then  the user should receive a 200 OK response
    And "reason" in response body equals "Bad credentials"

  Scenario: Verify that authentication fails when the username field is wrong in the request payload
    Given the user has auth request with the "username" field set to "asd" in the request body
    When the user sends a "POST" request to the "auth" endpoint
    Then  the user should receive a 200 OK response
    And "reason" in response body equals "Bad credentials"

  Scenario: Verify that authentication fails when the password field is empty in the request payload
    Given the user has auth request with the "password" field set to "" in the request body
    When the user sends a "POST" request to the "auth" endpoint
    Then  the user should receive a 200 OK response
    And "reason" in response body equals "Bad credentials"

  Scenario: Verify that authentication fails when the password field is equals space (" ") character in the request payload
    Given the user has auth request with the "password" field set to " " in the request body
    When the user sends a "POST" request to the "auth" endpoint
    Then  the user should receive a 200 OK response
    And "reason" in response body equals "Bad credentials"

  Scenario: Verify that authentication fails when the password field is wrong in the request payload
    Given the user has auth request with the "password" field set to "asd" in the request body
    When the user sends a "POST" request to the "auth" endpoint
    Then  the user should receive a 200 OK response
    And "reason" in response body equals "Bad credentials"