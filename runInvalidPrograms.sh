#! /bin/bash
echo "Running all invalid flair programs located in ../sampleFlairPrograms/invalidPrograms"

cd bin/
java driver ../sampleFlairPrograms/invalidPrograms/invalid-tokens.fl
java driver ../sampleFlairPrograms/invalidPrograms/tokenTesting.fl
java driver ../sampleFlairPrograms/invalidPrograms/valid-tokens.fl
java driver ../sampleFlairPrograms/invalidPrograms/white-space.fl
echo
echo "complete"
