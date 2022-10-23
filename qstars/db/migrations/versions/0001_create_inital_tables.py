"""create_intial_tables

Revision ID: 0001
Revises: 
Create Date: 2022-10-08 15:30:50.987340

"""
from alembic import op
import sqlalchemy as sa


# revision identifiers, used by Alembic.
revision = "0001"
down_revision = None
branch_labels = None
depends_on = None


def create_assets_table():
    op.create_table(
        "assets",
        sa.Column("asset_id", sa.String, primary_key=True),
        sa.Column("name", sa.String, nullable=False),
        sa.Column("type", sa.String),
        sa.Column("status", sa.String),
        sa.Column("check_in", sa.TIMESTAMP),
        sa.Column("check_out", sa.TIMESTAMP),
        sa.Column("last_maintenance", sa.TIMESTAMP),
        sa.Column("total_hours_used", sa.TIME),
        sa.Column("description", sa.String),
        sa.Column("latitude", sa.DECIMAL(8, 6)),
        sa.Column("longitude", sa.DECIMAL(9, 6)),
    )


def create_check_in_out_logs_table():
    op.create_table(
        "check_in_out_logs",
        sa.Column("check_in_out_id", sa.String, primary_key=True),
        sa.Column("employee_id", sa.String, sa.ForeignKey("employees.employee_id")),
        sa.Column("asset_id", sa.String, sa.ForeignKey("assets.asset_id")),
        sa.Column("checkin_date", sa.TIMESTAMP),
        sa.Column("checkout_date", sa.TIMESTAMP),
        sa.Column("total_time_checkout", sa.TIME),
    )


def create_drones_table() -> None:
    op.create_table(
        "drones",
        sa.Column("drone_id", sa.String, primary_key=True),
        sa.Column("check_in", sa.TIMESTAMP,),
        sa.Column("check_out", sa.TIMESTAMP,),
        sa.Column("last_maintenance", sa.TIMESTAMP),
        sa.Column("children", sa.String),
        sa.Column("status", sa.String, sa.ForeignKey("status.status_name")),
        sa.Column("hours_flown", sa.TIME),
        sa.Column("latitude", sa.DECIMAL(8, 6)),
        sa.Column("longitude", sa.DECIMAL(9, 6)),
    )


def create_employees_table():
    op.create_table(
        "employees",
        sa.Column("employee_id", sa.String, primary_key=True),
        sa.Column("first_name", sa.String, nullable=False),
        sa.Column("last_name", sa.String, nullable=False),
        sa.Column("email", sa.String, nullable=False),
    )


def create_flight_logs_table():
    op.create_table(
        "flight_logs",
        sa.Column("flight_log_id", sa.String, primary_key=True),
        sa.Column("drone_id", sa.String, sa.ForeignKey("drones.drone_id")),
        sa.Column("pilot_id", sa.String, sa.ForeignKey("pilots.pilot_id")),
        sa.Column("visual_observer_id", sa.String, sa.ForeignKey("pilots.pilot_id")),
        sa.Column("test_mission", sa.BOOLEAN),
        sa.Column("mission_date", sa.TIMESTAMP),
        sa.Column("start_time", sa.TIMESTAMP),
        sa.Column("stop_time", sa.TIMESTAMP),
        sa.Column("number_of_cycles", sa.INTEGER),
        sa.Column("number_of_landings", sa.INTEGER),
        sa.Column("crash_observed", sa.BOOLEAN),
        sa.Column("mission_success", sa.String),
        sa.Column("summary", sa.String),
    )


def create_maintenance_logs_table():
    op.create_table(
        "maintenance_logs",
        sa.Column("maintenance_id", sa.String, primary_key=True),
        sa.Column("asset_id", sa.String, sa.ForeignKey("assets.asset_id")),
        sa.Column("employee_id", sa.String, sa.ForeignKey("employees.employee_id")),
        sa.Column("date_of_maintenance", sa.TIMESTAMP),
        sa.Column("maintenance_type", sa.String),
    )


def create_pilots_table():
    op.create_table(
        "pilots",
        sa.Column("pilot_id", sa.String, primary_key=True),
        sa.Column("role", sa.String, nullable=False),
        sa.Column("date_of_birth", sa.String, nullable=False),
        sa.Column("total_hours_flown", sa.TIME, nullable=False),
        sa.Column("total_hours_observed", sa.TIME, nullable=False),
        sa.Column("FAA_number", sa.String),
        sa.Column("TCC_number", sa.String),
    )


def upgrade():
    create_assets_table()
    create_check_in_out_logs_table()
    create_drones_table()
    create_employees_table()
    create_flight_logs_table()
    create_maintenance_logs_table()
    create_pilots_table()
    pass


def downgrade():
    op.drop_table("assets")
    op.drop_table("check_in_out_logs")
    op.drop_table("drones")
    op.drop_table("employees")
    op.drop_table("flight_logs")
    op.drop_table("maintence_logs")
    op.drop_table("pilots")
    pass
