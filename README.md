1. Create a test plan (test suite) containing 5 manual test cases (tests) for testing a new account registration at http://automationpractice.com (a brand-new account creation should be covered)
   Send a prioritized list of these test cases with their description and explain why you chose these 5 test cases in this priority order;

2. Automate 2 test cases from your test plan and explain why exactly those tests were chosen for automation. In automation use: Java, TestNG\JUnit and Selenium Webdriver. Please use Maven for project assembling. All automated tests should be stable (can be executed multiple times without false failures\issues.)
   Would be a plus if the implementation will include usage of
   
    •	Java/OOP best practices

    •	PageObject pattern
   
    •	Tests parameterization

3. English Task: Communication (Необходимо написать запрос заказчику на английском языке): Вы нашли, как Вам кажется, дефект в приложении, которое Вы тестируете. Вы пишите об этом заказчику, описывая суть данной "проблемы", и говорите, как по вашему должно работать корректно приложение. Отмечая, что в документации, которая имеется у Вас, нет точной информации на этот счет и просите выслать обновленную документацию, чтобы понять как все таки правильно должна работать данная "фича" согласно последним требованиям.
-----
Solution:
1. https://docs.google.com/spreadsheets/d/1RmZ1R09Bvcq8WjNr1274iMq1ipmDESEG9osXuwnMyBg/edit?usp=sharing
2. src->test->java->AccountCreationTests.createAccount
   src->test->java->FieldsValidationTests.checkSignUpWithEmptyEmail
   src->test->java->FieldsValidationTests.checkSignUpWithEmptyPersonalInfo
3. https://docs.google.com/document/d/1TmD1vzt__bAlSHUx0t9RCphrzmDhPXEYs_n9dEh36ck/edit?usp=sharing