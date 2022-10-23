from qstars.db.schemas.maintenance_log import MaintenanceLog
from qstars.db.repos.base import BaseRepository


class MaintenanceLogRepo(BaseRepository):
    async def create_maintenance_log(self, new_maintenance_log: MaintenanceLog):
        sql_query = f"""
            INSERT INTO maintenance_log (maintenance_id, asset_id, employee_id, date_of_maintenance, maintenance_type)
            VALUES ('{new_maintenance_log.maintenance_id}','{new_maintenance_log.asset_id}', '{new_maintenance_log.employee_id}','{new_maintenance_log.date_of_maintenance}','{new_maintenance_log.maintenance_type}')
        """
        async with self.db.acquire() as conn:
            await conn.execute(sql_query)
