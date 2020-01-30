/*
 * SPDX-License-Identifier: Apache-2.0
 */

package org.hyperledger.fabric.samples.fabcar;

import com.owlike.genson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import java.util.Objects;

@DataType()
public final class Log {

    @Property()
    private final String actor_id;

    @Property()
    private final String actor_permission;


    @Property()
    private final String file_id;

    @Property()
    private final String file_permission;

    @Property()
    private final String company;

    @Property()
    private final String owner;


    public String getActor_id() {
        return actor_id;
    }

    public String getActor_permission() {
        return actor_permission;
    }

    public String getFile_id() {
        return file_id;
    }

    public String getFile_permission() {
        return file_permission;
    }

    public String getCompany() {
        return company;
    }

    public String getOwner() {
        return owner;
    }

    public Log(@JsonProperty("actor_id") final String actor_id, @JsonProperty("actor_permission") final String actor_permission,
               @JsonProperty("file_id") final String file_id, @JsonProperty("file_permission") final String file_permission, @JsonProperty("company") final String company, @JsonProperty("owner") final String owner) {
        this.actor_id = actor_id;
        this.actor_permission = actor_permission;
        this.file_id = file_id;
        this.file_permission = file_permission;
        this.company = company;
        this.owner = owner;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        Log other = (Log) obj;

        return Objects.deepEquals(new String[]{getActor_id(), getActor_permission(), getFile_id(), getActor_permission(), getCompany(), getOwner()},
                new String[]{other.getActor_id(), other.getActor_permission(), other.getFile_id(), other.getFile_permission(), other.getCompany(), other.getOwner()});
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActor_id(), getActor_permission(), getFile_id(), getFile_permission(), getCompany(), getOwner());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " [file_id=" + file_id + ", file permission="
                + file_permission + ", file_id=" + file_id + ", file permission= " + file_permission + ", company= " + company + ", owner=" + owner + "]";
    }
}
