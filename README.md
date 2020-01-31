# this project is a clone of Fabric Samples.
## introduction
I rewrote the java code smart contract and chaincode.

The code now stores a log entrie.
The class is still called fabcar this has to do with the docker compose and configurations.

I still don't 100% understand the path variable directing to the install folder.
The path references to the gopath but tries to get the github code it seems.
as it is in the github .com directory.

This code runs 2 organisations and 4 poeple in total that make usage of the blockchain.

## RUN THE CODE
### Before
first install the fabric sample binaries.
This can be done with this command
```
curl -sSL https://raw.githubusercontent.com/hyperledger/fabric/master/scripts/bootstrap.sh | bash -s 1.1.0

```
this will create a folder `fabric-sample`
And compile docker images 2.0 that we will need to run the application.
Be aware not to remove the images then you need to redo the above process.

Copy recursivly the `bin` folder to architecture.
I don't commit the binaries as this is OS specific.

Now your setup to run the code

### READY

The code can be run by creating a network.
This can be done by navigating to the first network folder.

``` bash
./byfn.sh generate
./byfn.sh up 
```

then navigate to the fabcar folder and type ;

`./startFabric.sh`

The smart contract will now be deployed to the blockchain network.

## Using the code

To use the code navigate to `fabcar/java`
Make sure there is active wallet and the blockchain doesn't have a user1.

Now run `mvn test`

This will generate a admin, an user and finaly post an log interaction to the blockchain.


## This project usages version 2.0 of fabric.
If the docker images are deleted the docker-compose will try to download 1.4 this is incompatible.
Make sure you download the newest version again and create the docker images version 2.0.

