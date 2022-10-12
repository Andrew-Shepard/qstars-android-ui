from invoke import task


@task
def db_setup(ctx):
    ctx.run("/app/files/db_init.sh")
