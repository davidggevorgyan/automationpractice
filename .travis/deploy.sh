#!/bin/sh

mkdir target/github-pages
cd target/github-pages
git clone --single-branch --branch gh-pages https://davidggevorgyan:$GITHUB_TOKEN@github.com/davidggevorgyan/automationpractice.git .
git config user.name "Travis CI"
git config user.email "deploy@travis-ci.org"
cp -R history ../allure-results/
cd ../..
mvn allure:report
cp -R target/site/allure-maven-plugin/* target/github-pages/
cd target/github-pages
git add --all
git commit -m "Auto deploy from Travis CI build $TRAVIS_BUILD_NUMBER"
git push -u origin gh-pages