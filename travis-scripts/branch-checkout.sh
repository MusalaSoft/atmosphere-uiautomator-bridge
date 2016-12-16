#!/bin/bash
set -e
BRANCH_NAME="dev"
if [ "${TRAVIS_PULL_REQUEST}" = "false" ]
then
  if [ `git branch -a | egrep "remotes/origin/${TRAVIS_BRANCH}$" ` ]
  then
    BRANCH_NAME=$TRAVIS_BRANCH
  fi
else
  if [ `git branch -a | egrep "remotes/origin/${TRAVIS_PULL_REQUEST_BRANCH}$" ` ]
  then
    BRANCH_NAME=$TRAVIS_PULL_REQUEST_BRANCH
  fi
fi

echo "Checking out branch $BRANCH_NAME"
git checkout $BRANCH_NAME
