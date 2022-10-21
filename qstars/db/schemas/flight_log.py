from pydantic import BaseModel
from sqlalchemy import TIME,TIMESTAMP,DECIMAL

class FlightLog(BaseModel):
    flight_log_id: str
    drone_id: str
    pilot_id: str
    visual_observer_id: str
    test_mission: bool 
    mission_date: TIMESTAMP
    start_time: TIMESTAMP
    stop_time: TIMESTAMP
    number_of_cycles: int
    number_of_landings: int
    crash_observed: bool
    mission_success: str
    summary: str

    class Config: 
        orm_mode = True