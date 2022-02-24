Maven docker plugin
http://dmp.fabric8.io/#example

Integration testing
https://www.baeldung.com/maven-failsafe-plugin

## Testcontainers
https://engineering.zalando.com/posts/2021/02/integration-tests-with-testcontainers.html

## docker-maven-plugin

Multiple containers can be managed at once, which can be linked together or share data via volumes. Containers are created and started with the docker:start goal and stopped and destroyed with the docker:stop goal. For integration tests both goals are typically bound to the the pre-integration-test and post-integration-test phase, respectively. It is recommended to use the maven-failsafe-plugin for integration testing in order to stop the docker container even when the tests fail.

## Surefire
This runs all the unit tests in our project. Since the Surefire plugin binds with the test phase, in case of any test failures, the build fails, and no further phases execute during the build process.

Alternatively, we can modify the plugin configuration to run integration tests, as well as the unit tests. However, this may not be desirable behavior for integration tests which could require some environment setup before, as well as some clean-up after test execution.

## Failsafe

The Failsafe Plugin is designed to run the integration tests in the project.

## Profiles 
https://www.baeldung.com/maven-profiles

By default `maven verify` executes unit tests. 
New profile created `integration-tests` to run the integration tests: `mvn clean verify -P integration-tests`.
 