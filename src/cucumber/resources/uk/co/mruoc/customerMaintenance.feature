Feature: Customer Maintenance

  Scenario: Get single customer
    Given the following customers exist
      | accountNumber | firstName | surname | balance |
      | 000007        | Micky     | Mouse   | 99999   |
    When a get request is made for customer "000007"
    Then the service returns a response code 200
    And the following customer is returned
      | accountNumber | firstName | surname | balance |
      | 000007        | Micky     | Mouse   | 99999   |

  Scenario: Create customer
    Given we have a new customer to create with the following data
      | accountNumber | firstName | surname | balance |
      | 000008        | Chris     | Stone   | 22222   |
    When the customer data is posted
    Then the service returns a response code 201
    And the following customer is returned
      | accountNumber | firstName | surname | balance |
      | 000008        | Chris     | Stone   | 22222   |
    And the response header contains "Location" with value "http://localhost:8090/web-template/ws/v1/customers/000008"

  Scenario: Get all customers
    Given the following customers exist
      | accountNumber | firstName | surname | balance |
      | 000000        | Michael   | Ruocco  | 10000   |
      | 000001        | Kiel      | Boatman | 5000    |
      | 000002        | Craig     | Betts   | 7500    |
    When a get request is made for all customers
    Then the service returns a response code 200
    And the following customers are returned
      | accountNumber | firstName | surname | balance |
      | 000000        | Michael   | Ruocco  | 10000   |
      | 000001        | Kiel      | Boatman | 5000    |
      | 000002        | Craig     | Betts   | 7500    |
    And the response header contains "X-Total-Count" with value "3"

  Scenario: Update customer
    Given the following customers exist
      | id     | firstName | surname | balance |
      | 000009 | Dean      | Heatlie | 33333   |
    And the customer needs to be updated to
      | id     | firstName | surname  | balance |
      | 000009 | Updated   | Customer | 77777   |
    When the customer data is updated
    Then the service returns a response code 200
    And the following customer is returned
      | id     | firstName | surname  | balance |
      | 000009 | Updated   | Customer | 77777   |

  Scenario: Delete customer
    Given the following customers exist
      | id     | firstName | surname | balance |
      | 000011 | Laura     | Noble   | 88888   |
    When the customer "000011" is deleted
    Then the service returns a response code 204

  Scenario: Attempt to create customer that already exists
    Given the following customers exist
      | id     | firstName | surname | balance |
      | 000009 | Dean      | Heatlie | 33333   |
    And we have a new customer to create with the following data
      | id     | firstName | surname | balance |
      | 000009 | Dean      | Heatlie | 33333   |
    When the customer data is posted
    Then the service returns a response code 409
    And the service returns error message "customer 000009 already exists"

  Scenario: Update customer that does not exist
    Given customer "000010" does not exist
    And the customer needs to be updated to
      | id     | firstName | surname  | balance |
      | 000010 | Updated   | Customer | 77777   |
    When the customer data is updated
    Then the service returns a response code 404
    And the service returns error message "customer 000010 not found"

  Scenario: Get single customer that does not exist
    Given customer "999999" does not exist
    When a get request is made for customer "999999"
    Then the service returns a response code 404
    And the service returns error message "customer 999999 not found"