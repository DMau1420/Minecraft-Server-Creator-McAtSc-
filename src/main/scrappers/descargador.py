import requests
import os

class descargador:
    def __init__(self):
        href = ""

    def descargar(self, href):
        userpath = os.path.expanduser("~")
        recursospath = os.path.join(userpath, "McAtSc/recursos")
        os.makedirs(recursospath, exist_ok=True)

        direct_url = href.split("url=")[-1]  # elimina el wrapper de adfoc.us
        filename = direct_url.split("/")[-1]

        if not filename in os.listdir(recursospath):
            print(f"Descargando {filename} ...")
            r = requests.get(direct_url)
            descargapath = os.path.join(recursospath, filename)
            with open(descargapath, "wb") as f:
                f.write(r.content)
            print(f"Archivo descargado en {descargapath}")
        else:
            print("archivo existente")