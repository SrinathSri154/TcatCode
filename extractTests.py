#!/usr/bin/python

# From a given test package, extract all tests with test ID,
# test title, and all tags associated with each test.

import re
import os
import copy
import sys
import json
from pprint import pprint

def addTest(aTestData, elements):
    'Pckage internal function'
    oneTest={}
    for element in elements:
        if aTestData.has_key(element):
            oneTest[element]=aTestData[element]
        else:
            oneTest[element]=""
    return oneTest


def getTestFromFile(FileName):
    'Extrat all tests from a feature file. This function should not be called out of this package'
    if not os.path.isfile(FileName):
        print "Not a file"
        return

    print "Processing file %s ......." % ( FileName)

    # Define the elments of each tuple to be returned
    fldTestID="Test_ID"
    fldTestTitle="Test_Title"
    fldTags="Tags"
    TupTestElement=(fldTestID, fldTestTitle, fldTags)

    # Tags that should be excluded
    dummyTag="dummyTag"
    TupNotTags=("precondition", "constant", dummyTag)

    # RegEx used to identify each type of lines
    RegFeature="^Feature:"
    RegScenario="^Scenario:"
    RegOutline="^Scenario Outline:"
    RegLoop="^Scenario Loop [1-9][0-9]*:"
    RegExample="^Examples:"
    RegDecorator="^@[a-zA-Z0-9_-]+"
    RegID="^ID_[a-zA-Z0-9_-]+"

    # A list of tests to be returned
    fileTests=[]
    DraftTest={}

    flagEOF=""

    FeatureTags=[]
    ScenarioTags=[]
    featureTitle=""
    test_ID=""
    test_Title=""

    featureFile=open(FileName)
    line=featureFile.readline()
    if not line:
        print "Blank file"
        return(-1)
    else:
        pass

    line=line.strip()

    # Extract the feature tags first
    while re.match(RegFeature, line) is None:
        Tag=""
        if re.match(RegDecorator, line) is not None:
            line=line.expandtabs()
            if " " in line:
                Tag=line.partition(" ")[0][1:]
            else:
                Tag=line[1:]

            if "(" in Tag:  Tag=dummyTag

            if not (Tag in TupNotTags):
                FeatureTags.append(Tag)
            else:
                pass

        line=featureFile.readline()
        if not line:
            flagEOF="Yes"
            break
        line=line.strip()

    if re.match(RegFeature, line) is not None:
        featureTitle=line.strip().partition(":")[2].strip()
    else:
        print ("Feature is not found in file %s", FileName)
        return(-1)

    while line:

        line=featureFile.readline()
        if not line:
            flagEOF="Yes"
            break

        line=line.strip()
        if line=="":
            line="dummy line"
            continue

        if re.match(RegDecorator, line) is not None:
            line=line.expandtabs()
            if " " in line:
                Tag=line.partition(" ")[0][1:]
            else:
                Tag=line[1:]
            if "(" in Tag:  continue

            if re.match(RegID, Tag) is not None:
                test_ID=Tag
                continue

            if not (Tag in TupNotTags):
                ScenarioTags.append(Tag)

            continue

        if re.match(RegExample, line) is not None:
            # For Scenario Outline, when an "Example" line is
            # found, add the tags. This is to avoid mixing its
            # tags with next Scenario
            TotTags=FeatureTags + ScenarioTags

            if DraftTest.has_key(fldTags):
                if TotTags:
                    DraftTest[fldTags]=copy.deepcopy(DraftTest[fldTags]) + copy.deepcopy(TotTags)
            else:
                DraftTest[fldTags]=copy.deepcopy(TotTags)

            # Exclude duplicate tags
            DraftTest[fldTags]=list(set(DraftTest[fldTags]))

            ScenarioTags=[]
            # For Scenario Outline, the test is not added here
            # This is because there might be multiple "Example"s
            # for one Scenario Outline. This kind of test is left
            # to be added when next Scenario is checked.

            continue

        if re.match(RegOutline, line) is not None or \
               re.match(RegScenario, line) is not None or \
               re.match(RegLoop, line) is not None:

            # First Check if there is a Scenario outline above
            if DraftTest.has_key(fldTestID) and DraftTest.has_key(fldTestTitle):
                newTest=addTest(DraftTest, TupTestElement)
                fileTests.append(copy.deepcopy(newTest))
                DraftTest={}

            if test_ID!="":
                DraftTest[fldTestID]=test_ID
                test_ID=""

            test_Title=line.partition(":")[2].strip()
            if test_Title!="":
                DraftTest[fldTestTitle]=test_Title

            # For regular Scenario or Scenario Loop, add it right now
            if re.match(RegScenario, line) is not None or \
                re.match(RegLoop, line) is not None:

                TotTags=FeatureTags + ScenarioTags
                TotTags=list(set(TotTags))

                if TotTags:
                    DraftTest[fldTags]=copy.deepcopy(TotTags)

                ScenarioTags=[]

                if DraftTest.has_key(fldTestID) and DraftTest.has_key(fldTestTitle):
                    newTest=addTest(DraftTest, TupTestElement)
                    fileTests.append(copy.deepcopy(newTest))
                    DraftTest={}

            continue

    if flagEOF:
        # Check if there is a Scenario Outline not added yet
        if DraftTest.has_key(fldTestID) and DraftTest.has_key(fldTestTitle):
            newTest=addTest(DraftTest, TupTestElement)
            fileTests.append(copy.deepcopy(newTest))
            DraftTest={}

    featureFile.close()
    # Return the uniqe ID in a test together with the tests set
    return [fldTestID, fileTests]


def getTestsFromJson(JsonFile):
    'Extract the test list from a json file'

    print "entering getTestsFromJson...."
    # Define the elments of each tuple to be returned
    fldTestID="Test_ID"
    fldTestTitle="Test_Title"
    fldTags="Tags"
    TupTestElement=(fldTestID, fldTestTitle, fldTags)

    if not os.path.isfile(JsonFile):
        print "Not a file"
        return

    IDs=[]

    pkgTests=[]

    with open(JsonFile, 'r') as f:
        data=json.load(f)

    if not data.has_key("test_cases"):
        print "Not a valid json file for test list"
        return(-1)

    for test in data["test_cases"]:
        for element in TupTestElement:
            if not test.has_key(element):
                print "not a valid test"
                continue
        if test[fldTestID] not in IDs:
            pkgTests.append(copy.deepcopy(test))
            IDs.append(copy.deepcopy(test[fldTestID]))
        else:
            print "Duplicate tests: ", test[fldTestID]
            continue

    print "returning below test....."
    for test in pkgTests: print test
    #print pkgTests
    return pkgTests


# function getTestFromPkg() return a list of tests in a package
# with {Test_ID, Test_Title, Tags}
#
# This function support three types of input:
#
# 1.) A feature file:   Then return the tests in this feature file
# 2.) A directory: then return all the tests from all feature files
#                  under this directory
# 3.) A json file: Then return the tests listed in this Json file
def getTestFromPkg(pkgPath):
    'Extract all tests from a test package'

    RegTestListJsonfile="[.\\a-zA-Z0-9_]*testsInPackage.json"

    if os.path.isfile(pkgPath) and  re.match(RegTestListJsonfile,pkgPath.strip()):
        return getTestsFromJson(pkgPath)

    RegFeatureFile="[.\\a-zA-Z0-9_]+.feature"
    pkgTests=[]
    pkgIDs=[]

    # Get a name list of featrue files
    if os.path.isdir(pkgPath):
        cmd = "find " + str(pkgPath) + " -name *.feature"
        str1=os.popen(cmd).read()
        files=str1.split("\n")
    elif os.path.isfile(pkgPath) and  re.match(RegFeatureFile,pkgPath.strip()):
        files=[pkgPath.strip()]
    else:
        files=[]

    for File in files:
        if re.match(RegFeatureFile, File.strip()) and os.path.isfile(File.strip()):
            results=getTestFromFile(File.strip())
            # Check if there are any errors occur
            if len(results)<2:
                continue

            unique_fld=results[0]
            newTests=results[1]
            for test in newTests:
                # Exclude duplicate tests
                if test[unique_fld] in pkgIDs:
                    print "Duplicate test ID found: %s" % (test[unique_fld])
                else:
                    pkgIDs.append(copy.deepcopy(test[unique_fld]))
                    pkgTests.append(copy.deepcopy(test))

    for test in pkgTests: print test

    return pkgTests


def getFeatureResult(feature):
    'Extract the test execution results for one feature file. This should not be called out of this package'
    # Define the tuple elements of returned list
    fldTest_ID="Test_ID"
    fldTest_Title="Test_Title"
    fldTest_Result="Test_Result"  # The scenario result-- passed, failed, skipped, pending, untested
    fldStep_Result="Step_Result"  # Step results. One character represents one step.
    fldLoops="Loops"              # The number of loops for scenario outline or scenario loop
    tupResultElement=(fldTest_ID,fldTest_Title,fldTest_Result,fldStep_Result,fldLoops)

    regScenarioLoop0="- iteration 0$"
    regScenarioOutline0="- row 0$"
    # A mutual mapping between test results and characters.
    dictResultMap={'passed':'p', 'failed':'f', 'skipped':'s', 'pending':'d', 'untested':'u', \
                   'p':'passed', 'f':'failed', 's':'skipped', 'd':'pending', 'u':'untested'}

    featureResults=[]

    print "In getFeatureResult"
    featureUri=feature["uri"]
    numOfElements=len(feature["elements"])

    if not os.path.isfile(featureUri):
        featurefile=featureUri
        while '/' in featurefile:
            featurefile = featurefile.partition('/')[2]
        cmd = "find . " + " -name " + featurefile
        str1=os.popen(cmd).read()
        files=str1.split("\n")
        if len(files)>=1:
            featureUri=files[0]
            for File in files:
                if feature["uri"][1:]  in File:
                    featureUri = File

    featureTests=getTestFromPkg(featureUri)


    test_ID=""
    test_Title=""
    step_Result=""
    Scenario_loop=0
    oneTestResult={}  # The result of one test. Will be appended to featureResults[].

    # One element represents one execution of one scenario
    elements=feature["elements"]
    for element in elements:
        keyword=element["keyword"]
        name=element["name"]

        Scenario_loop+=1
        test_Title=""
        if keyword=="Scenario Outline":
            test_Title=name.partition(" - row ")[0]
        elif keyword=="Scenario Loop":
            test_Title=name.partition("- iteration")[0]
        elif keyword=="Scenario":
            test_Title=name
        else:
            print "Unknow keyword: %s"%(keyword)

        # Only when a new Scenario comes, check out the test ID
        if (keyword=="Scenario Outline" and not oneTestResult) or \
           (keyword=="Scenario Loop" and not oneTestResult) or\
           keyword=="Scenario":
            # Check if there is a Scenario Outline/Loop ends already.
            if oneTestResult:
                oneTestResult[fldLoops]=Scenario_loop-1
                featureResults.append(copy.deepcopy(oneTestResult))
                Scenario_loop=1
                oneTestResult={}

            # Check out the test ID. Firstly, check it from the JSON file,
            # If not found, then search feature file to get it.
            test_ID=""
            for tag in element["tags"]:
                if re.match("^@ID_", tag["name"]):
                    test_ID=str(tag["name"][1:])

            if test_ID=="":
                for test in featureTests:
                    if test[fldTest_Title].strip()==test_Title.strip():
                        test_ID=test[fldTest_ID]

            if test_ID=="": print "Error: test not found: %s"%(test_Title)

        # Extrat the step results for current scenario execution/element.
        step_Result=""
        for step in element["steps"]:
            step_Result=step_Result + dictResultMap[str(step["result"]["status"])]

        # For the following execution for scenario outline/loop, only append the step results
        # For new scenarios, add the test ID,test title, and step results
        if (keyword=="Scenario Outline" and oneTestResult) or \
           (keyword=="Scenario Loop" and oneTestResult):
            print("fld"+str(oneTestResult))
            print("stepres" + step_Result)
            oneTestResult[fldStep_Result]=oneTestResult[fldStep_Result] + step_Result
        else:
            oneTestResult[fldTest_ID]=test_ID
            oneTestResult[fldTest_Title]=str(test_Title)
            oneTestResult[fldStep_Result]=step_Result

        # If a new "Scenario", add this test immediately.
        if keyword=="Scenario":
            oneTestResult[fldLoops]=Scenario_loop
            featureResults.append(copy.deepcopy(oneTestResult))
            test_ID=""
            Scenario_loop=0
            oneTestResult={}

    # There  is a Scenario Outline/Loop at the end of ths feature file.
    if oneTestResult:
        oneTestResult[fldLoops]=Scenario_loop
        featureResults.append(copy.deepcopy(oneTestResult))


    # Add the Scenario execution results
    for result in featureResults:
        str1=result[fldStep_Result].replace('p', '')
        # If all steps succeed, then the scenario succeds.
        if str1=="":
            result[fldTest_Result]=dictResultMap['p']
        # If not all steps succeed, then the result of first not "passed" step is the result of the scenario.
        elif dictResultMap.has_key(str1[0]):
            result[fldTest_Result]=dictResultMap[str1[0]]

    #for result in featureResults: print result
    return featureResults



def getResultFromJson(JsonFile):
    'Extract the test execution results from a JSON file'

    pkgResults=[]
    RegJsonFile="[.\\a-zA-Z0-9_]+.json"
    RegRunnerRltJsonFile="[.\\a-zA-Z0-9_]*runnerResults.json"


    if os.path.isfile(JsonFile) and re.match(RegRunnerRltJsonFile, JsonFile):
        pkgResults=getRunnerResult(JsonFile)
        return pkgResults

    if os.path.isfile(JsonFile) and re.match(RegJsonFile, JsonFile):
        with open(JsonFile) as f:
            data = json.load(f)

        for feature in data:
            pkgResults.extend(copy.deepcopy(getFeatureResult(feature)))

    for item in  pkgResults: print item
    return pkgResults

def getRunnerResult(Jsonfile):
    'Extract the test execution result from a test runner returned json file'

    print "Entering getRunnerResult()..."
    pkgResults=[]
    RegRunnerRltJsonFile="[.\\a-zA-Z0-9_]*runnerResults.json"
    IDs=[]

    validResult=['passed','failed', 'skipped', 'pending', 'untested']

    fldTestID="Test_ID"
    fldStatus="Test_Result"

    if os.path.isfile(Jsonfile) and re.match(RegRunnerRltJsonFile, Jsonfile):
        with open(Jsonfile) as f:
            data = json.load(f)
    if not data.has_key("test_cases"):
        print "No valid data found"
        return pkgResults

    for test in data["test_cases"]:
        print test
        if not(test.has_key(fldTestID) and test.has_key(fldStatus)):
            print "invalid test"
            break
        if test[fldTestID] in IDs:
            print "duplicate test"
            break
        if test[fldStatus] in validResult:
            pkgResults.append(copy.deepcopy(test))
            IDs.append(copy.deepcopy(test[fldTestID]))
        else:
            print "Not valid results"

    print IDs
    for item in  pkgResults: print item
    return pkgResults




if __name__ == "__main__":

    RegJsonFile="[.\\a-zA-Z0-9_]+.json"
    RegTestListJsonfile="[.\\a-zA-Z0-9_]*testsInPackage.json"

    if len(sys.argv) >= 2:
        # Check if it is a Json file with test list
        if os.path.isfile(sys.argv[1]) and re.match(RegTestListJsonfile, sys.argv[1]):
            getTestFromPkg(sys.argv[1])
        # Check if it is a Json file with test execution results
        elif os.path.isfile(sys.argv[1]) and re.match(RegJsonFile, sys.argv[1]):
            getResultFromJson(sys.argv[1])
        # Check if it is a feature file or a directory
        elif (os.path.isdir(sys.argv[1]) or os.path.isfile(sys.argv[1])):
            getTestFromPkg(sys.argv[1])
    else:
        getTestFromPkg(".")



