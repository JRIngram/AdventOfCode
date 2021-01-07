#!/bin/bash

# Add the following the code to your pre-push hook file
# MAIN_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
# $MAIN_DIR/../../pre-commit.sh

CWD=`pwd`
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $SCRIPT_DIR/2020/adventofcode2020/

echo "Running Pre-commit Maven tests..."
mvn clean pmd:check pmd:cpd-check package assembly:single
if [ $? -ne 0 ]; then
  echo "Errors found!"
  cd $CWD
  exit 1
fi
cd $CWD