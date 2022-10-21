from pydantic import BaseModel


class Employee(BaseModel):
    employee_id: str
    first_name: str
    last_name: str
    email: str
    
    class Config:
        orm_mode = True
