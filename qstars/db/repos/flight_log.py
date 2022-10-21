from qstars.db.schemas.flight_log import FlightLog
from qstars.db.repos.base import BaseRepository


class FlightLogRepo(BaseRepository):
    async def create_flight_log(self, new_flight_log: FlightLog):
        sql_query = f"""
            INSERT INTO flight_log (flight_log_id, drone_id, pilot_id, visual_observer_id, test_mission, mission_date, start_time, stop_time, number_of_cycles, number_of_landings, crash_observed, mission_success, summary)
            VALUES ('{new_flight_log.flight_log_id}','{new_flight_log.drone_id}','{new_flight_log.pilot_id}','{new_flight_log.visual_observer_id}','{new_flight_log.test_mission}', '{new_flight_log.mission_date}','{new_flight_log.start_time}','{new_flight_log.stop_time}',
            '{new_flight_log.number_of_cycles}', '{new_flight_log.number_of_landings}', '{new_flight_log.crash_observed}','{new_flight_log.mission_success}','{new_flight_log.summary}')
        """
        async with self.db.acquire() as conn:
            await conn.execute(sql_query)
