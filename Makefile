# Makefile :^)

main_image = qstars:red

docker_build_args = \
	--build-arg GIT_COMMIT=$(shell git show -s --format=%H) \
	--build-arg GIT_COMMIT_DATE="$(shell git show -s --format=%ci)" \
	--build-arg IMAGE_NAME=$(service) \
	--build-arg BUILD_DATE=$(shell date -u +"%Y-%m-%dT%T.%N%Z") \
	--build-arg VER_PYTHON=$(python_version) \
	--build-arg VER_PIP=$(pip_version) \
	

black: 
	black .

requirements.txt:
	pip-compile --generate-hashes --quiet requirements.in

test:
	# please run in docker eventually
	pytest tests/

build:
	DOCKER_BUILDKIT=1 docker build $(docker_build_args) -t $(main_image) .
	
run: 
	docker-compose -p qstars up qstars