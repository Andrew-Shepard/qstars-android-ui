from qstars.db.schemas.pilot import Pilot
from qstars.db.repos.base import BaseRepository

class PilotRepo(BaseRepository):
    async def create_pilot(self, new_pilot: Pilot):
        sql_query = f"""
            INSERT INTO pilot (pilot_id, role, date_of_birth, total_hours_flown, total_hours_oberved, FAA_number, TCC_number)
            VALUES ('{new_pilot.pilot_id}','{new_pilot.role}','{new_pilot.date_of_birth}','{new_pilot.total_hours_flown}','{new_pilot.total_hours_observed}','{new_pilot.FAA_number}','{new_pilot.TCC_number}')
        """
        async with self.db.acquire() as conn:
            await conn.execute(sql_query)