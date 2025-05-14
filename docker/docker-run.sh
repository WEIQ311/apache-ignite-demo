#!/bin/bash
docker run -d -p 10300:10300 -p 10800:10800 -p 3344:3344 -e IGNITE_NODE_NAME=ignite_node  apacheignite/ignite:3.0.0