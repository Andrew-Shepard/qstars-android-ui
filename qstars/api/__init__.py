import logging
from fastapi import FastAPI
from qstars.settings import Settings
from qstars.external.reporter import LogReporter
from qstars.api.routes import root as root_router


def create_app(settings: Settings):
    app = FastAPI()
    app.reporter = LogReporter()
    app.include_router(root_router)

    @app.on_event("startup")
    async def _startup():
        """
        Use this to initialize all of the singleton dependencies and shared
        objects.  i.e. db, reporters, bugsnag, etc
        """

        init_logger(settings.log_level)

        # TODO: await init_mongodb(settings.mongodb)

    @app.on_event("shutdown")
    async def _shutdown():
        """
        Clean up before closing the application
        """
        pass

    return app


def init_logger(log_level):
    logging.basicConfig(
        format="%(asctime)s|%(name)s|%(levelname)-5.5s|%(message)s",
        datefmt="%Y-%m-%dT%H:%M:%S",
    )
    logger = logging.getLogger()
    logger.setLevel(log_level)
