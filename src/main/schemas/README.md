#JAXB Generation

OTA schemas will be used to generate java objects.  We only use Hotel specif schemas to generate the java classes.

## Generate sources
```
xjc -d ../src/ -b binding.xjb OTA_Hotel*
```

The generated classes will be in a different package name structure.  Rename the packages to `org.opentravel.ota`