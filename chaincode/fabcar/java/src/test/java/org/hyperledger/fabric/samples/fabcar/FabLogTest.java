/*
 * SPDX-License-Identifier: Apache-2.0
 */

package org.hyperledger.fabric.samples.fabcar;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.hyperledger.fabric.shim.ledger.KeyValue;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.*;

public final class FabLogTest {

    private final class MockKeyValue implements KeyValue {

        private final String key;
        private final String value;

        MockKeyValue(final String key, final String value) {
            super();
            this.key = key;
            this.value = value;
        }

        @Override
        public String getKey() {
            return this.key;
        }

        @Override
        public String getStringValue() {
            return this.value;
        }

        @Override
        public byte[] getValue() {
            return this.value.getBytes();
        }

    }

    private final class MockCarResultsIterator implements QueryResultsIterator<KeyValue> {

        private final List<KeyValue> carList;

        MockCarResultsIterator() {
            super();

            carList = new ArrayList<KeyValue>();

            carList.add(new MockKeyValue("LOG000",
                    "{ \"actor_id\": \"Toyota\", \"actor_permission\": \"B\", \"file_id\": \"12a\", \"file_permission\": \"A\", \"company\": \"lean\", \"owner\": \"Tomok\" }"));
            carList.add(new MockKeyValue("LOG001",
                    "{ \"actor_id\": \"Toyta\", \"actor_permission\": \"B\", \"file_id\": \"2a\", \"file_permission\": \"A\", \"company\": \"rean\", \"owner\": \"omoko\" }"));
            carList.add(new MockKeyValue("LOG002",
                    "{ \"actor_id\": \"Toota\", \"actor_permission\": \"B\", \"file_id\": \"22a\", \"file_permission\": \"B\", \"company\": \"lwan\", \"owner\": \"Tomko\" }"));
            carList.add(new MockKeyValue("LOG003",
                    "{ \"actor_id\": \"oyota\", \"actor_permission\": \"B\", \"file_id\": \"11a\", \"file_permission\": \"A\", \"company\": \"lewn\", \"owner\": \"Tooko\" }"));
            carList.add(new MockKeyValue("LOG004",
                    "{ \"actor_id\": \"Tyota\", \"actor_permission\": \"B\", \"file_id\": \"1a\", \"file_permission\": \"B\", \"company\": \"leaw\", \"owner\": \"Tmoko\" }"));
        }

        @Override
        public Iterator<KeyValue> iterator() {
            return carList.iterator();
        }

        @Override
        public void close() throws Exception {
            // do nothing
        }

    }

    @Test
    public void invokeUnknownTransaction() {
        FabCar contract = new FabCar();
        Context ctx = mock(Context.class);

        Throwable thrown = catchThrowable(() -> {
            contract.unknownTransaction(ctx);
        });

        assertThat(thrown).isInstanceOf(ChaincodeException.class).hasNoCause()
                .hasMessage("Undefined contract method called");
        assertThat(((ChaincodeException) thrown).getPayload()).isEqualTo(null);

        verifyZeroInteractions(ctx);
    }

}

