# **hybrid_automation_framework**
This repository provides a **hybrid framework** designed for both API testing (using Rest Assured) and UI testing (using Selenium). It uses **Cucumber** for BDD, **TestNG** for test management, and integrates seamlessly with CI/CD pipelines for scalable, maintainable, and robust test automation.

---

## **Features**

### **1. Testing Support**
- **API Testing**: With services and request/response models.
- **UI Testing**: Driven by Selenium with Page Object Model (POM).
- **BDD Support**: Cucumber for behavior-driven testing.
- **Test Management**: TestNG ensures structured and organized execution.

### **2. Reporting**
- Extent Reports and Cucumber Reports for detailed test insights.
- Logs available in `logs/automation.log` for debugging.

### **3. Data Handling**
- Supports **Data-Driven Testing** using:
    - JSON files (`testData/logindata.json`,`testData/api_login_data`).
    - Excel files (`testData/LoginData.xlsx`).

### **4. Configurations**
- Environment-specific properties files (`config/QA.properties`).
- Dynamic test configurations through `config/config.json`.

### **5. CI/CD Ready**
- Integrated with **GitHub Actions** for continuous testing (`.github/workflows/automation.yml`).

### **6. Utilities**
- **LoggingFilter**: Captures API request and response logs.
- **FakerUtility**: Generates dynamic test data.
- **ExtentReportUtility**: Generates rich HTML test reports.

---

## **Framework Structure**

### **1. API Testing**
- **Base**: Common functionality for API services (`api/base`).
- **Services**: Specific API logic, e.g., `AuthService`, `UserProfileManagementService`.
- **Models**: Request and response models for structured payloads (`models/requests`, `models/response`).

### **2. UI Testing**
- **Pages**: Implements POM design (`ui/pages`).
    - Pages include `LoginPage`, `MyAccountPage`, etc.
- **Step Definitions**: Cucumber step implementations (`ui/stepDefinitions`).
- **Runners**: Test execution using TestNG (`ui/runner`).

### **3. Utilities**
- **BrowserUtility**: Handles browser interactions.
- **ExcelReaderUtility**: Reads data from Excel files.
- **LoggerUtility**: Configures and manages logging.

---

## **File and Folder Details**

| **Folder/File**         | **Description**                                                                |
|-------------------------|------------------------------------------------------------------------------|
| `.github/workflows`     | CI/CD workflows for GitHub Actions.                                          |
| `config`                | Configuration files for environments (DEV, QA, UAT).                         |
| `logs`                  | Logs generated during test execution.                                         |
| `Reports`               | Extent Reports and Cucumber HTML reports.                                     |
| `resources/features`    | Gherkin feature files for BDD tests.                                          |
| `src/test/java`         | Source code for API and UI automation.                                        |
| `testData`              | Test data files in JSON and Excel formats.                                    |
| `pom.xml`               | Maven configuration file for dependencies and project setup.                  |
| `testng.xml`            | TestNG configuration file execution and suites.                   |
| `parallelExecution.xml` | TestNG configuration file for parallel execution and suites.                  |
---

## **Execution**

### **1. Prerequisites**
- **Java** (version 8 or above).
- **Maven** (for dependency management).

### **2. Setup**
1. Clone the repository:
   ```bash
   git clone https://github.com/QAEngineerBot/hybrid_automation_framework.git

### **Setup**

1. **Configure**
    - Update the `config.json` file and environment property files (`QA.properties`) as per your test requirements.
    - Add your test data files in the `testData` folder (e.g., `api_login_data.json`, `LoginData.xlsx`).

2. **Run Tests**

    - **UI Tests**:
      ```bash
      mvn clean test -Dtest=RunUiTest
      mvn clean test -DBrowser=chrome -DisLambdaTest=true -DisHeadless=true
      ```

    - **API Tests**:
      ```bash
      mvn clean test -Dtest=RunApiTest
      mvn clean test -Dsuite=testng
      ```

   - **Parallel Execution**:
     ```bash
     mvn clean test -Dsuite=parallelExecution

---

### **Dependencies**

The project relies on the following major dependencies:
- **Selenium**: For browser automation.
- **Cucumber**: For behavior-driven testing.
- **TestNG**: For test management and parallel execution.
- **Rest Assured**: For API testing.
- **ExtentReports**: For generating HTML test reports.
- **Faker**: For generating random test data.
- **Log4j**: For logging framework and debugging.

---

### **Contributing**

Contributions are welcome! Here's how you can contribute:
1. Fork the repository.
2. Create a feature branch for your changes.
3. Submit a pull request with detailed explainations of your updates.

---

### **Contact**

For any questions or issues, feel free to reach out:  
📧 **sachinpujari5579@gmail.com**  
