from selenium import webdriver
from selenium.webdriver.common.by import By
import time

driver = webdriver.Chrome()
driver.get("https://www.stratege.ru")

games_link = driver.find_element(By.LINK_TEXT, "Игры")
games_link.click()

time.sleep(2)
if "games" in driver.current_url:
    print("Тест навигации - Игры: Успешно")
else:
    print("Тест навигации - Игры: Неудача")

# Возвращаемся на главную страницу
driver.get("https://www.stratege.ru")

forums_link = driver.find_element(By.LINK_TEXT, "Форумы")
forums_link.click()

time.sleep(2)
if "forums" in driver.current_url:
    print("Тест навигации - Форумы: Успешно")
else:
    print("Тест навигации - Форумы: Неудача")

driver.quit()
