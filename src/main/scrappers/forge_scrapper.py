import sys
import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from descargador import descargador

version = sys.argv[1]
url = f"https://files.minecraftforge.net/net/minecraftforge/forge/index_{version}.html"

options = Options()
options.add_argument("--no-sandbox")

driver = webdriver.Chrome(options=options)
driver.get(url)

time.sleep(5)

downloads = driver.find_elements(By.CSS_SELECTOR, "div.download")

def get_link(elem, title):
    try:
        installer = elem.find_element(By.XPATH, ".//a[span[text()='Installer']]")
        href = installer.get_attribute("href")
        print(f"{title} -> {href}")
        descargador().descargar(href)
    except:
        print("no link")

match downloads:
    case [p]:
        latest_elem =p.find_element(By.CLASS_NAME, "title")
        latest_text = latest_elem.text.split("\n")[0]
        get_link(p, latest_text)

    case [p,s]:
        latest_elem = p.find_element(By.CLASS_NAME, "title")
        latest_text = latest_elem.text.split("\n")[0]
        get_link(p, latest_text)
        recomended_elem = s.find_element(By.CLASS_NAME, "title")
        recomended_text = recomended_elem.text.split("\n")[0]
        get_link(s, recomended_text)

driver.quit()