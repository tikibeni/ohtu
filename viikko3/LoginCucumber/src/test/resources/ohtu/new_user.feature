Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new is selected
        When username "beni" and password "beNjAmin12a3" are entered
        Then system will respond with "new user registered"

    Scenario: creation fails with already taken username and valid password
        Given command new is selected
        When username "pekka" and password "notpekka" are entered
        Then system will respond with "new user not registered"

    Scenario: creation fails with too short username and valid password
        Given command new is selected
        When username "pk" and password "validword" are entered
        Then system will respond with "new user not registered"

    Scenario: creation fails with valid username and too short password
        Given command new is selected
        When username "moccamaster" and password "no" are entered
        Then system will respond with "new user not registered"

    Scenario: creation fails with valid username and password long enough but consisting of only letters
        Given command new is selected
        When username "tikibeni" and password "rootrootroot" are entered
        Then system will respond with "new user not registered"

    Scenario: can login with successfully generated account
        Given user "eero" with password "salainen1" is created
        And command login is selected
        When username "eero" and password "salainen1" are entered
        Then system will respond with "logged in"