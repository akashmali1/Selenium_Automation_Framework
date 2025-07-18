Feature: Error validation of login page

  @ErrorValidation
  Scenario Outline: Error validations
    Given person is on the landing page
    When the person logs in with <username> and <password>
    Then the output is <output>

    Examples:
      | username             | password     | output                       |
      | 12345akash@gmail.com | Akash@123    | Incorrect email or password. |
      | akash12345@gmail.com | incorrectPwd | Incorrect email or password. |