import imp
from qstars.db.dependencies import get_db
from qstars.db.schemas.dummy import Dummy
from qstars.db.repos.base import BaseRepository


class DummyRepo(BaseRepository):
    async def create_dummy(self, new_dummy: Dummy):
        sql_query = f"""
            INSERT INTO dummy (id,info)
            VALUES ('{new_dummy.id}','{new_dummy.info}')
        """
        async with self.db.acquire() as conn:
            await conn.execute(sql_query)
