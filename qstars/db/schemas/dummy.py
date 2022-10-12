from pydantic import BaseModel


class Dummy(BaseModel):
    id: str
    info: str

    class Config:
        orm_mode = True
