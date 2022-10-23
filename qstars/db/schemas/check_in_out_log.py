from pydantic import BaseModel
from sqlalchemy import TIME, TIMESTAMP, DECIMAL


class CheckInOutLog(BaseModel):
    check_in_out_id: str
    employee_id: str
    asset_id: str
    checkin_date: TIMESTAMP
    checkout_date: TIMESTAMP
    total_time_checkout: TIME

    class Config:
        orm_mode = True
