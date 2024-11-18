from selenium import webdriver
from selenium.webdriver.common.by import By
import time

driver = webdriver.Chrome()
driver.get("https://stratege.ru/forums/profile.php")

username_field = driver.find_element(By.ID, "vb_login_username")
password_field = driver.find_element(By.ID, "vb_login_password")

username_field.send_keys("CCowell")
password_field.send_keys("67823456")

submit_button = driver.find_element(By.CLASS_NAME, "button")
submit_button.click()

time.sleep(3)

if driver.find_element(By.CLASS_NAME, "blockhead"):
    print("Тест авторизации: Успешно, пользователь вошел.")
else:
    print("Тест авторизации: Неудача, пользователь не вошел.")

driver.quit()
