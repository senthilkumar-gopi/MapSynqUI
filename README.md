# SeleniumUIAutomation

								=======================
								Framework Architecture:
								=======================

Project - Maven
Framework - Page Object Model using Page Factory in Selenium.
Language - Java
WebDriver - 3.141 version
Browsers - chrome and firefox
Test Executions - TestNG (or) maven test
Screenshots - {rootfolder}\target\surefire-reports\Screenshots\passed & {rootfolder}\target\surefire-reports\Screenshots\failed
Logger - Log4j - {rootfolder}/target/Log.log
Reports - testng reports - {rootfolder}\target\surefire-reports\index.html, {rootfolder}\target\surefire-reports\emailable-report.html
ReportNG reports -  {rootfolder}\target\surefire-reports\html\index.html
Editor - Eclipse

=================
Folder Structure
=================
src/main/java - Individual pages are classified in to their corresponding packages and written inside java file
				Framework related codes

src/main/resources - contains config folder gmail properties,driver folder Chromedriver exe, properties file and log4j.xml file

src/test/java - TestNG test file

src/test/resources - contains TestNG.xml file in SuiteFiles

target/Log.log - contains test execution log informations

Screenshots - contains test execution passed/failed screenshots if executed as TestNG tests

pom.xml - contains dependencies,plugins,etc

===========
Executions:
===========
=======
TestNG
=======
For running test:
 Right click on src/test/resources/SuiteFiles/<Any .xml file> and Run As-> TestNG Suite
	(OR)
 Right click on pom.xml and Run As->Maven test

Reports - testng reports - {rootfolder}\target\surefire-reports\index.html, {rootfolder}\target\surefire-reports\emailable-report.html
	      ReportNG reports -  {rootfolder}\target\surefire-reports\html\index.html
Screenshots - {rootfolder}\Screenshots\passed & {rootfolder}\Screenshots\failed
Logger - {rootfolder}/target/Log.log
