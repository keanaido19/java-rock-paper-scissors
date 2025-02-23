SHELL := bash
.ONESHELL:
.SHELLFLAGS := -eu -o pipefail -c
.DELETE_ON_ERROR:
MAKEFLAGS += --warn-undefined-variables
MAKEFLAGS += --no-builtin-rules

.PHONY: help start_services grade test run

# help: @ Lists available make tasks
help:
	@egrep -oh '[0-9a-zA-Z_\.\-]+:.*?@ .*' $(MAKEFILE_LIST) | \
	awk 'BEGIN {FS = ":.*?@ "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}' | sort

# clean: @ Clean the build
clean:
	mvn clean
	docker-compose down

# test: @ Run all tests
test:
	mvn test

# run: @ Run the application locally
run: start_services
	mvn exec:java -Dexec.mainClass="za.co.wethinkcode.rockpaperscissors.GameRunner"

# grade: @ Grade a specific scenario (usage: `make grade SCENARIO=Login`)
GRADING_FILES = grading.rsync-filter.txt

grade: STORY?=*
grade: SUBMISSION_DIR?=${PWD}
grade: GRADE_CMD='mvn test -B'
grade:
	@echo +++ Copying grading files into student submission dir: $(SUBMISSION_DIR)
	rsync -av --include-from "$(GRADING_FILES)" . "$(SUBMISSION_DIR)" --ignore-times

	@echo +++ Running grading tests
	pushd "$(SUBMISSION_DIR)"
	export GRADE_CMD=$(GRADE_CMD)
	export SUBMISSION_DIR="$(SUBMISSION_DIR)"
	docker-compose -f docker-compose.grading.yml run grader
	popd
