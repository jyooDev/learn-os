#!/bin/bash

echo "Compiling Java files..."
javac RemoteFortune.java
javac RemoteFortuneImpl.java
javac FortuneRmiServer.java
javac FortuneRmiClient.java


echo "Starting RMI registry..."
start rmiregistry 7000

echo "Starting the RMI server..."
java FortuneRmiServer &

echo "Starting the RMI client..."
java FortuneRmiClient

cat << 'EOF'
       .
     .    .
      )  (
    (      )
  .'--------`.
 /    .-""-.   \
|   /        \  |
|  |          | |
 \  \        /  /
  '.________.'.'
     '------'
     _|____|_
    |________|
     |      |
     '------'
EOF
