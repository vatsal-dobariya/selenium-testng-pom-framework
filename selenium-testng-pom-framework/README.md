# Selenium TestNG POM Framework

![CI](https://github.com/vatsal-dobariya/selenium-testng-pom-framework/actions/workflows/ci.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-11-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.18-green)
![TestNG](https://img.shields.io/badge/TestNG-7.9-blue)

A production-grade **Page Object Model (POM)** test automation framework built with Selenium WebDriver, TestNG, and Java. Designed to demonstrate scalable, maintainable test architecture for enterprise QA teams.

---

## 🏗️ Framework Architecture

```
selenium-testng-pom-framework/
├── src/
│   ├── main/java/com/vatsal/
│   │   ├── pages/
│   │   │   ├── BasePage.java         # Shared WebDriver utilities & explicit waits
│   │   │   ├── LoginPage.java        # Login page POM
│   │   │   └── DashboardPage.java    # Dashboard/Inventory page POM
│   │   └── utils/
│   │       ├── DriverManager.java    # Thread-safe WebDriver factory
│   │       └── ConfigReader.java     # Properties-based config loader
│   └── test/
│       ├── java/com/vatsal/tests/
│       │   ├── BaseTest.java         # Test lifecycle (setup/teardown)
│       │   ├── LoginTest.java        # Login test scenarios
│       │   └── DashboardTest.java    # Dashboard test scenarios
│       └── resources/
│           ├── testng.xml            # TestNG suite configuration
│           └── config.properties     # Test environment config
└── .github/workflows/ci.yml          # GitHub Actions CI pipeline
```

---

## ✅ Test Coverage

| Test Class      | Scenarios Covered                                              |
|-----------------|----------------------------------------------------------------|
| LoginTest       | Valid login, Invalid credentials, Locked-out user, Empty fields, Logout |
| DashboardTest   | Page title, Product count, Product names, Cart initial state   |

---

## 🚀 Getting Started

### Prerequisites
- Java 11+
- Maven 3.8+
- Google Chrome (latest)

### Run All Tests
```bash
mvn clean test
```

### Run in Headless Mode (CI)
```bash
mvn clean test -Dheadless=true
```

### Run Specific Browser
```bash
mvn clean test -Dbrowser=firefox
```

---

## ⚙️ Configuration

Edit `src/test/resources/config.properties`:

```properties
browser=chrome
headless=false
base.url=https://www.saucedemo.com/
valid.username=standard_user
valid.password=secret_sauce
```

---

## 🔄 CI/CD

This project uses **GitHub Actions** for continuous integration. On every push or pull request to `main`, the pipeline:
1. Sets up JDK 11
2. Installs Chrome
3. Executes tests in headless mode
4. Uploads TestNG reports as build artifacts

---

## 🧰 Tech Stack

| Tool              | Purpose                          |
|-------------------|----------------------------------|
| Selenium WebDriver 4 | Browser automation            |
| TestNG 7.9        | Test runner & assertions         |
| WebDriverManager  | Automatic browser driver setup   |
| Maven             | Build & dependency management    |
| Log4j2            | Test execution logging           |
| GitHub Actions    | CI/CD pipeline                   |

---

## 👤 Author

**Vatsal Dobariya** — QA Lead | 7+ years in Software Quality Engineering  
🔗 [LinkedIn](https://www.linkedin.com/in/vatsal-dobariya/) | 🌐 [Portfolio](https://vatsal-dobariya.github.io/vatsal-portfolio/)
