Feature: Validating Place API's

  @AddPlace @Regression
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add Place payload with "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name    | language | address            |
      | AAhouse | English  | World Cross Center |
#     | BBhouse | Spanish  | Sea Cross Center   |

  @DeletePlace @Regression
  Scenario: Verify if Delete Place functionality is working
    Given Delete Place payload
    When user calls "deletePlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"