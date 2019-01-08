#!/usr/bin/env bash

if [[ ! -d src/main/java ]]; then
	echo "directory doesn't exist"
	mkdir -p src/main/java
fi

pushd src/main/schemas
xjc -d ../java/ -b binding.xjb OTA_Hotel*

popd
pushd src/main/java
rm -rf generated
popd
mvn install
