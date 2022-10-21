from qstars.db.schemas.drone import Drone
from qstars.db.repos.base import BaseRepository


class DroneRepo(BaseRepository):
    async def create_drone(self, new_drone: Drone):
        sql_query = f"""
            INSERT INTO drone (drone_id, check_in, check_out, last_maintenance, children, status, hours_flown, latitude, longitude)
            VALUES ('{new_drone.drone_id}','{new_drone.check_in}','{new_drone.check_out}','{new_drone.last_maintenance}','{new_drone.children}',
            '{new_drone.status}','{new_drone.hours_flown}','{new_drone.latitude}','{new_drone.longitude}')
        """
        async with self.db.acquire() as conn:
            await conn.execute(sql_query)
