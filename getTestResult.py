# -*- coding: utf-8 -*-
#!/bin/python
import json
import os
from extractTests import getResultFromJson

def main():
    testResultList = getResultFromJson('./test_output_result.json')
    parameters=''
    for testResult in testResultList:
        if testResult['Test_ID'] == "" :
            continue
        parameters = testResult["Test_Result"]
    with open("tmp_result.json","w") as f:
        json.dump(parameters,f)
    os.system('rm test_output_result.json')


if __name__=='__main__':
    main()
