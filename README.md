# pi8-zhuyund
A QA system with N-gram and relevance feed back
AAE is deployed remotely

##Install

```{r, engine='bash', count_lines}
mvn install
```

##Deploy

```{r, engine='bash', count_lines}
./deployRankerAs.sh
```

##Execute

mvn exec:java -Dexec.mainClass="Main" -Dexec.args="<input directory> <output directory>"
