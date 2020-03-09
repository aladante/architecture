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

### The statement under are no longer of use.
revised.
I made sure to delete al the not necessary files.
Now you only need to copy the files and overwrite the existing files.

It's important to double check if all the test are called.
The last issue was that 1 test was commented.

Thanks to this it missed a essential part.

This is know fixed.

MAKE SURE TO COPY THE CONTENT RIGHT.
I didn't only edited the code but also the start script.

If this is not copied correctly the code will not run.
This has to do with the fact that the code will run queryAllCars to initilize (not sure but it is necessary).
Because i changed up the code it now has to call a different function to init the chaincode

### Before
first install the fabric sample binaries.
This is necesarry to compile specific docker images that we use.

The latest described in the docker-compose file is not up to date with the docker images created with this script
This can be done with this command

```
curl -sSL https://raw.githubusercontent.com/hyperledger/fabric/master/scripts/bootstrap.sh | bash -s 

```
this will create a folder `fabric-sample`
And compile docker images 2.0 that we will need to run the application.
Be aware not to remove the images then you need to redo the above process.

Copy recursivly the `bin` folder to architecture.
I don't commit the binaries as this is OS specific.

Now your setup to run the code

### READY

The code can be run by creating a network.

Navigate to the fabcar folder and type ;

`./startFabric.sh java`

The smart contract will now be deployed to the blockchain network.

## Using the code

To use the code navigate to `fabcar/ching`
Make sure there is active wallet and the blockchain doesn't have a user1.

Now run `mvn test`

This will generate a admin, an user and finaly post an log interaction to the blockchain.

The test classes will call the actual code to initiate the transaction between the blockchain and application.
This will illustrate how a transaction will look.


## This project usages version 2.0 of fabric.
If the docker images are deleted the docker-compose will try to download 1.4 this is incompatible.
Make sure you download the newest version again and create the docker images version 2.0.

### The out put 
If you have run the test the output should be simulair to the provide output below.
Here it is clear that the data is added to the ledger.



```bash
-------------------------------------------------------
 T E S T S
-------------------------------------------------------                                                               
Running org.example.ClientTest                                                                                        
log4j:WARN No appenders could be found for logger (org.hyperledger.fabric_ca.sdk.helper.Config).                      
log4j:WARN Please initialize the log4j system properly.                                                               
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
Successfully enrolled user "admin" and imported it into the wallet                                                    
Successfully enrolled user "user1" and imported it into the wallet                                                    
[{"owner":"Tomoko","company":"lean","actor_id":"Toyota","actor_permission":"B","file_id":"12a","file_permission":"A"},{"owner":"Tomoko","company":"lean","actor_id":"ching","actor_permission":"A","file_id":"12b","file_permission":"A"}]
{"owner":"ching","company":"lean","actor_id":"ab1","actor_permission":"A","file_id":"Grey","file_permission":"A"}     
{"owner":"ching","company":"lean","actor_id":"ab1","actor_permission":"A","file_id":"Grey","file_permission":"A"}     
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 16.584 sec                                            
Results :                                                                                                             
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0

```
