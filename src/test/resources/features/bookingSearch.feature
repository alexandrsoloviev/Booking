

Feature: Booking.com поиск
  Scenario: Найти самый популярный отель на booking
    Given ввести название города "Lviv"
    When нажать кнопку search
    Then "Jam Hotel Hnatyuka" певрый отель в списке
    And рейтинг должен составлять "8.7"