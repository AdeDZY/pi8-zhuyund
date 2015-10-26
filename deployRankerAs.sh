#!/bin/bash

PROJ_FOLDER=`dirname $0`

DD=${1:-src/main/resources/deploy/deployRankerAAE.xml} 
BROKER=${2:-tcp://localhost:61616}

export UIMA_JVM_OPTS="-Xms128M -Xmx4000M -XX:-UseGCOverheadLimit"

export UIMA_CLASSPATH="$PROJ_FOLDER/target/classes:$PROJ_FOLDER/target/dependency"

$UIMA_HOME/bin/deployAsyncService.sh $DD -brokerURL $BROKER
