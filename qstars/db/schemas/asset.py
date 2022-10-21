from pydantic import BaseModel
from sqlalchemy import TIME,TIMESTAMP,DECIMAL

class Asset(BaseModel):
    asset_id: str
    name: str
    type: str
    status: str
    check_in: TIMESTAMP
    check_out: TIMESTAMP
    last_maintenance: TIMESTAMP
    total_hours_used: TIME
    description: str
    latitude: DECIMAL(8, 6)
    longitude: DECIMAL(9, 6)
    
    class Config:
        orm_mode = True
