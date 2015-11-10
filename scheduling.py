import random
import math

def genDiskAccesses(num,lower,upper): #Generates random disk accesses
	accessList = []
	for i in range(num):
		randomval = random.randint(lower,upper)
		accessList.append(randomval)
		return accessList

def FCFS(accessList,startPos):
	currentPos=startPos
	tracksTraversed=0
	for value in accessList:
		diff = math.fabs(currentPos-value)
		tracksTraversed+=diff
		currentPos=value
	return tracksTraversed

def closestSeek(accessList,currentPos): #Returns closest guy in list given current pos
	minimum = 99999999
	nextPos=0
	for value in accessList:
		if(math.fabs(currentPos-value)<minimum):
			nextPos=value
			minimum = math.fabs(currentPos-value)
	return nextPos

def closestSeekFirst(accessList,startPos):
	currentPos=startPos
	tracksTraversed=0
	while(len(accessList)>0):
		nextSeek = closestSeek(accessList,currentPos)
		#print("Next Seek  : ",nextSeek)
		tracksTraversed+=math.fabs(currentPos-nextSeek)
		accessList.remove(nextSeek)
		currentPos=nextSeek
	return tracksTraversed

def scan(accessList,startPos,maxEndPos): #Head always moves from lower to higher first
	currentPos=startPos
	tracksTraversed=0
	
	for currentPos in range(startPos,maxEndPos+1):
		if currentPos in accessList:
			accessList.remove(currentPos)
	tracksTraversed+=maxEndPos-startPos

	if(len(accessList)==0):
		return tracksTraversed
	else:
		accessList.sort()
		tracksTraversed+=maxEndPos-accessList[0];
	

	return tracksTraversed
	


tempAccessList = [10,50,30,20,40]
#print(FCFS(tempAccessList,0))
#print(closestSeekFirst(tempAccessList,0))
#print(scan(tempAccessList,10,50))