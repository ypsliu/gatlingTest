#!/usr/bin/env python
#When you run this python script, you should clean your log every time
#argument is absolute path of your log
import sys

def parse_argument():
    if (len(sys.argv) < 1):
        raise RuntimeError('parseArgument error')
    #init
    argus = {}
    argus["filePath"] = sys.argv[1]
    return argus

def update_dict(dict, line):
    content = line.rstrip().split("|")
    key = content[0].split(" ")[8]
    valueDict = {content[1]: content[2]}
    valueList = []
    if dict.has_key(key):
        valueList = dict.get(key)
        valueList.append(valueDict)
    else:
        valueList.append(valueDict)
        interFaceDict = {key:valueList}
        dict.update(interFaceDict)
    return


def read_file(fliePath):
    file = open(fliePath)
    dict = {}
    while 1:
        lines = file.readlines(10000)
        if not lines:
           break
        for line in lines:
           update_dict(dict, line)
    file.close()
    return dict

#print every interface average time in t-RetailAPI, xDist and total
def get_result(dicts):
    for (interface, timeBean) in dicts.items():
        sumTotalTime = 0
        sumXDistTime = 0
        tRetailAPITime = 0
        count = 0
        for item in timeBean:
            for (totalTime, xDistTime) in item.items():
                sumTotalTime = sumTotalTime + int(totalTime)
                sumXDistTime = sumXDistTime + int(xDistTime)
            count = count + 1
        tRetailAPITime = (sumTotalTime - sumXDistTime) / count
        print (interface, ",", tRetailAPITime, ",", sumXDistTime/count, ",", sumTotalTime/count, ",", count)


if __name__ == '__main__':
    argus = parse_argument()
    print(argus)
    print ("---------------------------------------------------------------------------------------------------------------")
    dicts = read_file(argus["filePath"])
    get_result(dicts)
    print ("---------------------------------------------------------------------------------------------------------------")
    print ("End")





