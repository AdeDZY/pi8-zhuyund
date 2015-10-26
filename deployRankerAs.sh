#!/bin/bash

PROJ_FOLDER=`dirname $0`

DD=${1:-src/main/resources/deploy/deployRankerAAE.xml} 
BROKER=${2:-tcp://ur.lti.cs.cmu.edu:61616}

# mkdir target/dependency
# cp -r $PROJ_FOLDER/dependency/ $PROJ_FOLDER/target/dependency

export UIMA_JVM_OPTS="-Xms128M -Xmx4000M -XX:-UseGCOverheadLimit"

export UIMA_CLASSPATH="$PROJ_FOLDER/target/classes:$PROJ_FOLDER/dependency/"

echo $UIMA_CLASSPATH

$UIMA_HOME/bin/deployAsyncService.sh $DD -brokerURL $BROKER
