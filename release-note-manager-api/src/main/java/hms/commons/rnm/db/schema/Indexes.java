/*
 * This file is generated by jOOQ.
 */
package hms.commons.rnm.db.schema;


import hms.commons.rnm.db.schema.tables.CustomerModuleDeployment;
import hms.commons.rnm.db.schema.tables.DevReleaseNote;
import hms.commons.rnm.db.schema.tables.FlywaySchemaHistory;
import hms.commons.rnm.db.schema.tables.ModuleDependency;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables in the default schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index CUSTOMER_MODULE_DEPLOYMENT_CUSTOMER_ID = Internal.createIndex(DSL.name("customer_id"), CustomerModuleDeployment.CUSTOMER_MODULE_DEPLOYMENT, new OrderField[] { CustomerModuleDeployment.CUSTOMER_MODULE_DEPLOYMENT.CUSTOMER_ID }, false);
    public static final Index FLYWAY_SCHEMA_HISTORY_FLYWAY_SCHEMA_HISTORY_S_IDX = Internal.createIndex(DSL.name("flyway_schema_history_s_idx"), FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, new OrderField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.SUCCESS }, false);
    public static final Index CUSTOMER_MODULE_DEPLOYMENT_MODULE_ID = Internal.createIndex(DSL.name("module_id"), CustomerModuleDeployment.CUSTOMER_MODULE_DEPLOYMENT, new OrderField[] { CustomerModuleDeployment.CUSTOMER_MODULE_DEPLOYMENT.MODULE_ID }, false);
    public static final Index MODULE_DEPENDENCY_MODULE_ID = Internal.createIndex(DSL.name("module_id"), ModuleDependency.MODULE_DEPENDENCY, new OrderField[] { ModuleDependency.MODULE_DEPENDENCY.MODULE_ID }, false);
    public static final Index DEV_RELEASE_NOTE_TAG_ID = Internal.createIndex(DSL.name("tag_id"), DevReleaseNote.DEV_RELEASE_NOTE, new OrderField[] { DevReleaseNote.DEV_RELEASE_NOTE.TAG_ID }, false);
}
