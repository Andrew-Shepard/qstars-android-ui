# syntax=docker/dockerfile:1
FROM python:3.8-slim-buster

ARG IMAGE_NAME="qstars"
ARG VER_PYTHON=""
ARG VER_PIP=""

COPY requirements.txt /app/
COPY utility/install_packages.sh /tmp
WORKDIR /app
RUN  /tmp/install_packages.sh

RUN pip3 install \
    --disable-pip-version-check \
    --no-cache-dir \
    --no-deps \
    --require-hashes \
    --requirement requirements.txt

COPY . /app
ENV PYTHONPATH=/app \
    CREATEDB=false
EXPOSE 8000

ENTRYPOINT ["/init"]

ARG GIT_COMMIT=""
ARG GIT_COMMIT_DATE=""
ARG BUILD_DATE=""

ENV IMAGE_NAME="$IMAGE_NAME" \
    VER_PYTHON="$VER_PYTHON" \
    VER_PIP="$VER_PIP" \
    GIT_COMMIT="$GIT_COMMIT" \
    GIT_COMMIT_DATE="$GIT_COMMIT_DATE"