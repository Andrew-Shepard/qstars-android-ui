from qstars.db.schemas.asset import Asset
from qstars.db.schemas.asset_tree import AssetTree
from qstars.db.repos.base import BaseRepository

class AssetTreeRepo(BaseRepository):
    async def create_asset_tree(self, new_asset_tree: AssetTree):
        sql_query = f"""
            INSERT INTO asset_tree (parent_id, child_id)
            VALUES ('{new_asset_tree.parent_id}','{new_asset_tree.child_id}')        
        
        """

        async with self.db.acquire() as conn:
            await conn.execute(sql_query)
