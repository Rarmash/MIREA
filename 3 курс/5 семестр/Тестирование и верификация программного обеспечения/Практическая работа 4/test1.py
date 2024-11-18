from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import time

driver = webdriver.Chrome()
driver.get("https://www.stratege.ru")

search_box = driver.find_element(By.ID, "gb_ids_site_search_input")
search_box.send_keys("Forza Horizon 3")
search_box.send_keys(Keys.RETURN)

time.sleep(3)

results = driver.find_element(By.CLASS_NAME, "ss_search_bx_list_title")
if results:
    print("Тест поиска: Успешно, результаты найдены.")
else:
    print("Тест поиска: Неудача, результаты не найдены.")

driver.quit()
