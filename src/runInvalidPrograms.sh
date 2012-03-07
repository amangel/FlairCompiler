#! /bin/bash

echo "Will run all valid Flair Programs found in ../sampleFlairPrograms/invalidPrograms/"
echo "assuming that the sample flair programs are in ./sampleFlairPrograms/invalidPrograms"


for flair in ../sampleFlairPrograms/invalidPrograms/*.fl
do
  echo ""
  echo "$flair"
  java driver "$flair"
  
done

