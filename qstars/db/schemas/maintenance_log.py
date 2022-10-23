from pydantic import BaseModel
from sqlalchemy import TIME, TIMESTAMP, DECIMAL


class MaintenanceLog(BaseModel):
    maintenance_id: str
    asset_id: str
    employee_id: str
    date_of_maintenance: TIMESTAMP
    maintenance_type: str

    class Config:
        orm_mode = True
