from typing import List, Optional

from qstars.db.dependencies import get_db
from qstars.db.schemas.employee import Employee
from qstars.db.repos.base import BaseRepository


class EmployeeRepo(BaseRepository):
    async def create_employee(self, new_employee: Employee):
        sql_query = f"""
            INSERT INTO employees (employee_id,first_name,last_name,email)
            VALUES ('{new_employee.employee_id}','{new_employee.first_name}','{new_employee.last_name}','{new_employee.email}');
        """
        async with self.db.acquire() as conn:
            await conn.execute(sql_query)

    async def select_all_employees(self) -> Optional[List[Employee]]:
        sql_query = f"""
            SELECT * FROM employees;
        """

        async with self.db.acquire() as conn:
            await conn.execute(sql_query)
