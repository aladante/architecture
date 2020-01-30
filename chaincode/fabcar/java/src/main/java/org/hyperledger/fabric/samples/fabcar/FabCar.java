/*
 * SPDX-License-Identifier: Apache-2.0
 */

package org.hyperledger.fabric.samples.fabcar;

import com.owlike.genson.Genson;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.*;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.hyperledger.fabric.shim.ledger.KeyValue;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Java implementation of the Fabric Car Contract described in the Writing Your
 * First Application tutorial
 */
@Contract(
        name = "FabCar",
        info = @Info(
                title = "FabCar contract",
                description = "The hyperlegendary car contract",
                version = "0.0.1-SNAPSHOT",
                license = @License(
                        name = "Apache 2.0 License",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"),
                contact = @Contact(
                        email = "f.carr@example.com",
                        name = "F Carr",
                        url = "https://hyperledger.example.com")))
@Default
public final class FabCar implements ContractInterface {

    private final Genson genson = new Genson();

    private enum FabCarErrors {
        CAR_NOT_FOUND,
        CAR_ALREADY_EXISTS
    }

    /**
     * Retrieves a car with the specified key from the ledger.
     *
     * @param ctx the transaction context
     * @param key the key
     * @return the Car found on the ledger if there was one
     */
    @Transaction()
    public Log queryLog(final Context ctx, final String key) {
        ChaincodeStub stub = ctx.getStub();
        String carState = stub.getStringState(key);

        if (carState.isEmpty()) {
            String errorMessage = String.format("Log %s does not exist", key);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, FabCarErrors.CAR_NOT_FOUND.toString());
        }

        Log log = genson.deserialize(carState, Log.class);

        return log;
    }

    /**
     * Creates some initial Cars on the ledger.
     *
     * @param ctx the transaction context
     */
    @Transaction()
    public void initLedger(final Context ctx) {
        ChaincodeStub stub = ctx.getStub();

        String[] logData = {
                "{ \"actor_id\": \"Toyota\", \"actor_permission\": \"B\", \"file_id\": \"12a\", \"file_permission\": \"A\", \"company\": \"lean\", \"owner\": \"Tomoko\" }",
                "{ \"actor_id\": \"ching\", \"actor_permission\": \"A\", \"file_id\": \"12b\", \"file_permission\": \"A\", \"company\": \"lean\", \"owner\": \"Tomoko\" }",
        };

        for (int i = 0; i < logData.length; i++) {
            String key = String.format("LOG%03d", i);

            Log log = genson.deserialize(logData[i], Log.class);
            String carState = genson.serialize(log);
            stub.putStringState(key, carState);
        }
    }

    /**
     * Creates a new car on the ledger.
     *
     * @param ctx              the transaction context
     * @param key              the key for the new car
     * @param actor_id         the make of the new car
     * @param actor_permission the model of the new car
     * @param file_id          the color of the new car
     * @param file_permission  the owner of the new car
     * @param owner            the owner of the new car
     * @return the created Car
     */
    @Transaction()
    public Log createLog(final Context ctx, final String key, final String actor_id, final String actor_permission, final String file_id, final String file_permission,
                         final String company, final String owner) {
        ChaincodeStub stub = ctx.getStub();

        String logState;

        Log log = new Log(actor_id, actor_permission, file_id, file_permission, company, owner);
        logState = genson.serialize(log);
        stub.putStringState(key, logState);

        return log;
    }

    /**
     * Retrieves every car between CAR0 and CAR999 from the ledger.
     *
     * @param ctx the transaction context
     * @return array of Cars found on the ledger
     */
    @Transaction()
    public Log[] queryAllLogs(final Context ctx) {
        ChaincodeStub stub = ctx.getStub();

        final String startKey = "LOG0";
        final String endKey = "LOG999";
        List<Log> logs = new ArrayList<Log>();

        QueryResultsIterator<KeyValue> results = stub.getStateByRange(startKey, endKey);

        for (KeyValue result : results) {
            Log log = genson.deserialize(result.getStringValue(), Log.class);
            logs.add(log);
        }

        Log[] response = logs.toArray(new Log[logs.size()]);

        return response;
    }

}
