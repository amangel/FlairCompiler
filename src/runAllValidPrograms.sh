#! /bin/bash

echo "Will run all valid Flair Programs found in ../sampleFlairPrograms/validPrograms/"
echo "assuming that the sample flair programs are in ./sampleFlairPrograms/validPrograms"
echo "If the compiler has not yet been built, it will build the Compiler."

if [ -f ./driver.class ]
	then
		for flair in ../sampleFlairPrograms/validPrograms/*.fl
		do
		  echo ""
		  echo "$flair"
		  java driver "$flair"
		  
		done
	else
		echo "Building compiler, re-run this script"
		javac driver.java
fi

