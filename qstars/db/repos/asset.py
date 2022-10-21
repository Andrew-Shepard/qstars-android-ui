from qstars.db.schemas.asset import Asset
from qstars.db.repos.base import BaseRepository


class AssetRepo(BaseRepository):
    async def create_asset(self, new_asset: Asset):
        sql_query = f"""
            INSERT INTO assets (asset_id,name,type,status,check_in,check_out,last_maintenance,total_hours_used,description,latitutde,longitude)
            VALUES ('{new_asset.asset_id}','{new_asset.name}','{new_asset.type}','{new_asset.status}','{new_asset.check_in}','{new_asset.check_out}'
            ,'{new_asset.last_maintenance}','{new_asset.total_hours_used}','{new_asset.description}','{new_asset.latitude}','{new_asset.longitude}')
        """
        async with self.db.acquire() as conn:
            await conn.execute(sql_query)
