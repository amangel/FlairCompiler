#! /bin/bash

echo "Will run all valid Flair Programs found in ../sampleFlairPrograms/validPrograms/"
echo "assuming that the sample flair programs are in ./sampleFlairPrograms/validPrograms"


for flair in ../sampleFlairPrograms/validPrograms/*.fl
do
  echo ""
  echo "$flair"
  java driver "$flair"
  
done

