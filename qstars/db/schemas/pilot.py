from pydantic import BaseModel
from sqlalchemy import TIME, TIMESTAMP, DECIMAL


class Pilot(BaseModel):
    pilot_id: str
    role: str
    date_of_birth: TIMESTAMP
    total_hours_flown: TIME
    total_hours_observed: TIME
    FAA_number: str
    TCC_number: str

    class Config:
        orm_mode = True
