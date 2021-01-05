#!/bin/bash

CWD=`pwd`
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $SCRIPT_DIR/2020/adventofcode2020/

echo "Running Pre-commit Maven tests..."
# running maven clean test
pwd
mvn clean test
if [ $? -ne 0 ]; then
  echo "Errors found!"
  cd $CWD
  exit 1
fi
cd $CWD