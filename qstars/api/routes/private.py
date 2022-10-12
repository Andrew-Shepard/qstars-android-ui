from fastapi import APIRouter, Depends
from starlette.status import HTTP_201_CREATED
from typing import List, Optional, Union
from enum import Enum
from qstars.db.dependencies import get_repository
from qstars.db.schemas.dummy import Dummy
from qstars.db.repos.dummy import DummyRepo
from qstars.dependencies.logger import logger

private_router = APIRouter()
private_tags: Optional[List[Union[str, Enum]]] = ["private"]


@private_router.get(
    "/resource/example", tags=private_tags,
)
async def get_example():
    return {"example": "ok"}


@private_router.post(
    "/create_dummy",
    tags=private_tags,
    response_model=Dummy,
    status_code=HTTP_201_CREATED,
)
async def create_dummy(
    new_dummy: Dummy, dummy_repo: DummyRepo = Depends(get_repository(DummyRepo))
) -> Dummy:
    await dummy_repo.create_dummy(new_dummy=new_dummy)
    return new_dummy
