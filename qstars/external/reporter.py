import logging

logger = logging.getLogger()


class LogReporter:
    def debug(self, msg: str, *args, **kwargs):
        logger.debug(msg)

    def info(self, msg: str, *args, **kwargs):
        logger.info(msg)

    def warning(
        self, exc: Exception, context: str = None, cause: Exception = None,
    ):
        logger.warning("[%s] %s: %s", exc.__class__.__name__, exc)

    def error(
        self, exc: Exception, context: str = None, cause: Exception = None,
    ):
        logger.error(
            "[%s] %s: %s", exc.__class__.__name__, exc, exc_info=True,
        )
