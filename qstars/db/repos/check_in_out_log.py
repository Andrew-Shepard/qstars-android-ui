from qstars.db.schemas.check_in_out_log import CheckInOutLog
from qstars.db.repos.base import BaseRepository

class CheckInOutLogRepo(BaseRepository):
    async def create_check_in_out_log(self, new_check_in_out_log: CheckInOutLog):
        sql_query = f"""
        INSERT INTO check_in_out_logs (check_in_out_id, employee_id, asset_id, checkin_date, checkout_date, total_time_checkout)
        VALUES ('{new_check_in_out_log.check_in_out_id}','{new_check_in_out_log.employee_id}','{new_check_in_out_log.asset_id}','{new_check_in_out_log.checkin_date}','{new_check_in_out_log.checkout_date}',
        '{new_check_in_out_log.total_time_checkout}')
        """
        async with self.db.acquire() as conn:
            await conn.execute(sql_query)