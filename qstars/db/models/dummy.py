import sqlalchemy as sa
from qstars.db.metadata import METADATA

DUMMY = sa.Table(
    "dummy",
    METADATA,
    sa.Column("id", sa.String, nullable=False, primary_key=True),
    sa.Column("info", sa.String, nullable=False),
)
