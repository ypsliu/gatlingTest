#!/usr/bin/env python
#When you run this python script, you should clean your log every time
#argument1 is absolute path of your log
#argument2 is the value of your interface, eg POST_/authentication
import sys
import matplotlib.pyplot as plt

def parse_argument():
    if (len(sys.argv) < 1):
        raise RuntimeError('parseArgument error')
    #init
    argus = {}
    argus["filePath"] = sys.argv[1]
    argus["interface"] = sys.argv[2]
    return argus

def update_dict(dict, line, interface):
    content = line.rstrip().split("|")
    key = content[0].split(" ")[8]
    totalTime = content[1]
    xDistTime = content[2]
    totalTimeKey = content[0].split("]")[0]
    timeKey = totalTimeKey.split(" ")[2]
    totalTimeList = []
    xDistTimeList = []
    realTimeList = []
    if (key != interface):
        return
    if dict.has_key(key):
        valueDict = dict.get(key)
        valueDict.get("realTimeList").append(timeKey)
        valueDict.get("xDistTimeList").append(int(xDistTime))
        valueDict.get("totalTimeList").append(int(totalTime))
    else:
        realTimeList.append(timeKey)
        xDistTimeList.append(int(xDistTime))
        totalTimeList.append(int(totalTime))
        valueDict = {"realTimeList": realTimeList, "xDistTimeList": xDistTimeList, "totalTimeList":totalTimeList}
        interFaceDict = {key:valueDict}
        dict.update(interFaceDict)
    return


def read_file(fliePath, interface):
    file = open(fliePath)
    dict = {}
    while 1:
        lines = file.readlines(10000)
        if not lines:
            break
        for line in lines:
            update_dict(dict, line, interface)
    file.close()
    return dict


def get_graph(dicts):
     for (interface, timeBean) in dicts.items():
        plt.figure(interface + " Stress Testing")
        realTimeList = timeBean.get("realTimeList")
        xDistTimeList = timeBean.get("xDistTimeList")
        totalTimeList = timeBean.get("totalTimeList")
        plt.title('TotalTime & XDistTime Analysis')
        plt.plot(realTimeList, totalTimeList, 'o')
        plt.plot(realTimeList, xDistTimeList, 'o')
        plt.xticks(rotation=90)
        plt.legend(('TotalTime', 'XDistTime'))
        plt.xlabel('Time')
        plt.ylabel('Expand time(ms)')
        plt.show()

if __name__ == '__main__':
    argus = parse_argument()
    print(argus)
    print ("---------------------------------------------------------------------------------------------------------------")
    dicts = read_file(argus["filePath"], argus["interface"])
    get_graph(dicts)
    print ("---------------------------------------------------------------------------------------------------------------")
    print ("End")





