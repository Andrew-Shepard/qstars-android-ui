import pytest
from httpx import AsyncClient
import respx
from qstars.api import create_app
from qstars.settings import get_settings, Settings


@pytest.fixture(scope="session")
def app(settings):
    app = create_app(settings)
    app.dependency_overrides[get_settings] = lambda: settings
    return app


@pytest.fixture
def client(app):
    respx.route(host="test").pass_through()
    aclient = AsyncClient(app=app, base_url="http://test")
    return aclient


@pytest.fixture(scope="session")
def settings():
    return Settings()
