from aiopg.sa import create_engine, Engine
from fastapi import Depends
from typing import Type, Callable
from qstars.db.repos.base import BaseRepository

_db = None
CONN_SCHEMA = "postgresql"


async def init_db(host, port, user, password, database):
    global _db
    if _db is not None:
        raise RuntimeError("database is already initialized")
    db_uri = f"{CONN_SCHEMA}://{user}:{password}@{host}:{port}/{database}"
    _db = await create_engine(db_uri)


async def disconnect_db():
    global _db
    if _db is None:
        raise RuntimeError("database is not initialized")
    _db.terminate()
    await _db.wait_closed()
    _db = None


def get_db() -> Engine:
    if _db is None:
        raise RuntimeError("database is not initialized")
    return _db


def get_repository(Repo_type: Type[BaseRepository]) -> Callable:
    def get_repo(db: Engine = Depends(get_db)) -> Type[BaseRepository]:
        return Repo_type(db)

    return get_repo
