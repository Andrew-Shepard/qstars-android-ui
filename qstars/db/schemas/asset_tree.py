from pydantic import BaseModel


class AssetTree(BaseModel):
    parent_id: str
    child_id: str

    class Config:
        orm_mode = True
