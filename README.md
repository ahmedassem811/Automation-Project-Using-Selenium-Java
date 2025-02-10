# Project: Automation Testing 

---

## Prerequisites

1. **Java**: Ensure Java (JDK) is installed on your machine.
2. **Maven**: Use Maven for project dependency management.
3. **TestNG**: Ensure TestNG is installed and configured in your IDE.
4. **Selenium WebDriver**: Add Selenium dependencies to your Maven `pom.xml` file.
5. **Browser**: Use the latest version of a compatible browser (e.g., Chrome, Firefox).
7. **Development IDE**: Recommended: IntelliJ IDEA.

---

## Project Structure

```
|-- src
    |-- main
        |-- java
            |-- pages
                |-- AddCustomerPage.java
                |-- BankingPage.java
                |-- CustomersTablePage.java
                |-- LandingPage.java

            |-- utils
                |-- AlertHandler.java
                |-- RandommerApiClient.java
                |-- TableAssertion.java
                |-- WindowHandler.java

    |-- test
        |-- java
            |-- testPack
                |-- TestCases.java
```

### Key Components
- **`pages/`**: Contains Page Object Model classes for web page interactions.
- **`utilities/`**: Includes reusable utilities like WindowHandler,Assertions.
- **`testcases/`**: Contains TestNG test classes for test scenarios.
---

## Test Cases

### **1. Create User and Validate ID**

#### Test Steps:
1. Navigate to the "URL" Of the application.
2. Click on Banking APP.
3. Click on Log in as bank manager.
4. Click on Add customer button.
5. Fill out the required fields to create a new user.
6. Submit the form.
7. Capture the user ID displayed in the alert message upon successful creation.
8. Verify the captured ID matches the ID in the users table.

#### Assertions:
- Validate that the alert message is displayed after creating the user.
- Confirm the ID in the alert message matches the ID in the users table.

### **2. Delete Created User and Assert Deletion**

#### Test Steps:
1. Navigate to the users table.
2. Locate the user created in the previous test case.
3. Click the delete button for the corresponding user.
5. Verify the user no longer exists in the users table.

#### Assertions:
- Confirm the user is removed from the table after deletion.
