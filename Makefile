# Makefile :^)

black: 
	black .

requirements.txt:
	pip-compile --generate-hashes --quiet requirements.in

test:
	echo "andrew needs to set up the test suite"