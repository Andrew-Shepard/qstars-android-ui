from qstars.settings import Settings, init_settings
from qstars.api import create_app

settings = Settings()
init_settings(settings)
app = create_app(settings)
