"""create_dummy_tables

Revision ID: 46112b6f9246
Revises: 
Create Date: 2022-10-08 15:30:50.987340

"""
from alembic import op
import sqlalchemy as sa


# revision identifiers, used by Alembic.
revision = "46112b6f9246"
down_revision = None
branch_labels = None
depends_on = None


def create_dummy_table() -> None:
    op.create_table(
        "dummy",
        sa.Column("id", sa.String, primary_key=True),
        sa.Column("info", sa.String, nullable=False, index=True),
    )


def upgrade():
    create_dummy_table()
    pass


def downgrade():
    op.drop_table("dummy")
    pass
