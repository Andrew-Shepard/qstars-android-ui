from pydantic import BaseModel
from sqlalchemy import TIME,TIMESTAMP,DECIMAL


class Drone(BaseModel):
    drone_id: str
    check_in: TIMESTAMP
    check_out: TIMESTAMP
    last_maintenance: TIMESTAMP
    children: str
    status: str
    hours_flown: TIME
    latitude: DECIMAL(8, 6)
    longitude: DECIMAL(9, 6)

    class Config:
        orm_mode = True
