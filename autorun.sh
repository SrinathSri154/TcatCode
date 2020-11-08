#!/bin/bash
#
#
# Description: CSF tool for CCTF
#
# Author: Harry Zhang
# Version: 1.0.0
#
# Revision history
# REV:     DATE:           WHO:    COMMENTS:
# 1.0.0    03/01/2019      tkt     Initial version
#

TEMP=`getopt -o t:d:l::c:: -a -l type:,dir:,list::,command:: -- "$@"`

if [ $? != 0 ]
then
        echo "Terminating....." >&2
        exit 1
fi

eval set -- "$TEMP"

LIST=""
DIR=""

while true
do
    case "$1" in
        -d | --dir | -dir)
            echo "base dir: $2"
            DIR=$2
            shift 2
            ;;
        -l | --list | -list)
            case "$2" in
                "")
                    echo "tag lists: $2"
                    shift 2
                    ;;
                *)
                    echo "option list: $2"
                    LIST=$2
                    shift 2
            esac
            ;;
        --)
            shift
            break
            ;;
        *)
           echo "Internal error!"
           exit 1
           ;;
        esac
done

for arg do
   echo '--> '"$arg" ;
done

Result_File="runnerResults.json"

function  exec_testcases
{
        TEST_LIST=$1
        TEST_LIST=$(echo $TEST_LIST | tr -d '[ \t]')
        TEST_ID=$(echo $TEST_LIST | tr -s "," " ")
        
        cmd="mvn test -s settings.xml -Dcucumber.options=\" -t @"   
        TMP_JSON="tmp_result.json"
        Create_Json_File
        for TEST in $TEST_ID;do
            if [ -a $TMP_JSON ]; then
                rm $TMP_JSON
            fi
            tmp_cmd=${cmd}${TEST}\"   
            eval ${tmp_cmd}       
            python getTestResult.py
            if [ -a $TMP_JSON ]; then
              RESULT=$(cat tmp_result.json | tr -d '"')
              if [ $RESULT ]
              then
                Add_Test_Case $TEST $RESULT
              fi
              rm $TMP_JSON
            fi
        done
        Complete_Json_File
}

function SH
{
        integer rc=99
        eval ${1}
        rc=$?
        if [ ${rc} -ne 0 ]
        then
                print "ERROR: [${1}] failed with [${rc}]"
                exit 1
        fi
        return 0
}

function Create_Json_File
{
        if [ -a $Result_File ]
        then
            rm -rf $Result_File
        fi
        touch $Result_File
        printf "{\n" >> $Result_File
        printf '\t"test_cases":[\n' >> $Result_File
}

function Add_Test_Case
{
        printf '\t\t{\n' >> $Result_File
        if [ -n $1 ] && [ -n $2 ]
        then
            printf "\t\t\t\"Test_ID\":\"$1\",\n" >> $Result_File
            printf "\t\t\t\"Test_Result\":\"$2\"\n" >> $Result_File
        fi
        printf "\t\t},\n" >> $Result_File

}

function Complete_Json_File
{
        compare_message="\"test_cases\":["
        check_message=`sed -n '$p' $Result_File`
        if [ $compare_message != $check_message ]; then
            sed -i '$d' $Result_File
            printf "\t\t}\n" >> $Result_File
        fi
        printf "\t]\n" >> $Result_File
        printf "}\n" >> $Result_File
}

exec_testcases $LIST
