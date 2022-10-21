from fastapi import APIRouter, Depends
from starlette.status import HTTP_201_CREATED
from typing import List, Optional, Union
from enum import Enum
from qstars.db.dependencies import get_repository
from qstars.db.schemas.employee import Employee
from qstars.db.repos.employee import EmployeeRepo
from qstars.dependencies.logger import logger

private_router = APIRouter()
private_tags: Optional[List[Union[str, Enum]]] = ["private"]


@private_router.get(
    "/resource/example", tags=private_tags,
)
async def get_example():
    return {"example": "ok"}


@private_router.post(
    "/create_employee",
    tags=private_tags,
    response_model=Employee,
    status_code=HTTP_201_CREATED,
)
async def create_employee(
    new_employee: Employee, employee_repo: EmployeeRepo = Depends(get_repository(EmployeeRepo))
) -> Employee:
    await employee_repo.create_employee(new_employee=new_employee)
    return new_employee

@private_router.post(
    "/select_all_employees",
    tags=private_tags,
    response_model=List[Employee],
    status_code=200,
)
async def select_all_employees(
    employee_repo: EmployeeRepo = Depends(get_repository(EmployeeRepo))
) -> Optional[List[Employee]]:
    employees = await employee_repo.select_all_employees()
    return employees

