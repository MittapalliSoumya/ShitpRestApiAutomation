
##########Rest API FrameWork details:


TOOLS USED:

JAVA,
TESTNG,
JAYWAY JSON PATH library,
MAVEN

Details to test the rest api frame works:


1) Import the project. pox.xml will download all the dependancies for running the project
2) Navigate to StarWarApiTest. And right click on run methods or entire class.
   Note:verifyEnterprisIsStarship() test is expected to fail as per requirement
3) StarWarApiTest extends Base test  which means all the test preparation will be in Base test.
4) In test file, all the test methods uses StarWarAPi which is in main java class and retrieves
   the result of the methods
5) StarWarApi java class uses the RestUtils class to hit the api and get the results.

