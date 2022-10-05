from fastapi import APIRouter
from typing import List, Optional, Union
from enum import Enum

private_router = APIRouter()
private_tags: Optional[List[Union[str, Enum]]] = ["private"]


@private_router.get(
    "/resource/example", tags=private_tags,
)
async def get_exampple():
    return {"example": "ok"}
