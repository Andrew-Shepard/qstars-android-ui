#!/bin/bash
set -o nounset -o errexit -o xtrace

echo "Waiting for db..."
while ! nc -z db 5432
    do
        echo sleeping
        sleep 1
    done
echo "Response from db!"

# For setting up credentials and things, not really needed yet and requires waiting for db
#if [[ "$CREATEDB" == 'true' ]]; then 
#    echo "Setting up database..."
#    cd /app/utility
#    python3 /app/utility/manage_db.py create
#fi

# python3 /app/utility/manage_db.py wait

cd /app
alembic upgrade head
