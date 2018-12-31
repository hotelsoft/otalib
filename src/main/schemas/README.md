#JAXB Generation

OTA schemas will be used to generate java objects.  We only use Hotel specif schemas to generate the java classes.

## Generate sources
```
xjc -d ../java/ -b binding.xjb OTA_Hotel*
```


