/*
 * This file is generated by jOOQ.
 */
package hms.commons.rnm.db.schema;


import hms.commons.rnm.db.schema.tables.Customer;
import hms.commons.rnm.db.schema.tables.CustomerModuleDeployment;
import hms.commons.rnm.db.schema.tables.DaReleaseNote;
import hms.commons.rnm.db.schema.tables.DeploymentLocation;
import hms.commons.rnm.db.schema.tables.DevReleaseNote;
import hms.commons.rnm.db.schema.tables.FlywaySchemaHistory;
import hms.commons.rnm.db.schema.tables.Module;
import hms.commons.rnm.db.schema.tables.ModuleDependency;
import hms.commons.rnm.db.schema.tables.ModuleDeployments;
import hms.commons.rnm.db.schema.tables.ModuleTag;
import hms.commons.rnm.db.schema.tables.ReleaseNote;
import hms.commons.rnm.db.schema.tables.records.CustomerModuleDeploymentRecord;
import hms.commons.rnm.db.schema.tables.records.CustomerRecord;
import hms.commons.rnm.db.schema.tables.records.DaReleaseNoteRecord;
import hms.commons.rnm.db.schema.tables.records.DeploymentLocationRecord;
import hms.commons.rnm.db.schema.tables.records.DevReleaseNoteRecord;
import hms.commons.rnm.db.schema.tables.records.FlywaySchemaHistoryRecord;
import hms.commons.rnm.db.schema.tables.records.ModuleDependencyRecord;
import hms.commons.rnm.db.schema.tables.records.ModuleDeploymentsRecord;
import hms.commons.rnm.db.schema.tables.records.ModuleRecord;
import hms.commons.rnm.db.schema.tables.records.ModuleTagRecord;
import hms.commons.rnm.db.schema.tables.records.ReleaseNoteRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in 
 * the default schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<CustomerRecord> KEY_CUSTOMER_PRIMARY = Internal.createUniqueKey(Customer.CUSTOMER, DSL.name("KEY_customer_PRIMARY"), new TableField[] { Customer.CUSTOMER.ID }, true);
    public static final UniqueKey<CustomerModuleDeploymentRecord> KEY_CUSTOMER_MODULE_DEPLOYMENT_PRIMARY = Internal.createUniqueKey(CustomerModuleDeployment.CUSTOMER_MODULE_DEPLOYMENT, DSL.name("KEY_customer_module_deployment_PRIMARY"), new TableField[] { CustomerModuleDeployment.CUSTOMER_MODULE_DEPLOYMENT.ID }, true);
    public static final UniqueKey<DaReleaseNoteRecord> KEY_DA_RELEASE_NOTE_PRIMARY = Internal.createUniqueKey(DaReleaseNote.DA_RELEASE_NOTE, DSL.name("KEY_da_release_note_PRIMARY"), new TableField[] { DaReleaseNote.DA_RELEASE_NOTE.ID }, true);
    public static final UniqueKey<DeploymentLocationRecord> KEY_DEPLOYMENT_LOCATION_PRIMARY = Internal.createUniqueKey(DeploymentLocation.DEPLOYMENT_LOCATION, DSL.name("KEY_deployment_location_PRIMARY"), new TableField[] { DeploymentLocation.DEPLOYMENT_LOCATION.CUSTOMER_ID, DeploymentLocation.DEPLOYMENT_LOCATION.LOCATION_CODE }, true);
    public static final UniqueKey<DevReleaseNoteRecord> KEY_DEV_RELEASE_NOTE_PRIMARY = Internal.createUniqueKey(DevReleaseNote.DEV_RELEASE_NOTE, DSL.name("KEY_dev_release_note_PRIMARY"), new TableField[] { DevReleaseNote.DEV_RELEASE_NOTE.ID }, true);
    public static final UniqueKey<FlywaySchemaHistoryRecord> KEY_FLYWAY_SCHEMA_HISTORY_PRIMARY = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, DSL.name("KEY_flyway_schema_history_PRIMARY"), new TableField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK }, true);
    public static final UniqueKey<ModuleRecord> KEY_MODULE_PRIMARY = Internal.createUniqueKey(Module.MODULE, DSL.name("KEY_module_PRIMARY"), new TableField[] { Module.MODULE.ID }, true);
    public static final UniqueKey<ModuleDependencyRecord> KEY_MODULE_DEPENDENCY_PRIMARY = Internal.createUniqueKey(ModuleDependency.MODULE_DEPENDENCY, DSL.name("KEY_module_dependency_PRIMARY"), new TableField[] { ModuleDependency.MODULE_DEPENDENCY.ID }, true);
    public static final UniqueKey<ModuleDeploymentsRecord> KEY_MODULE_DEPLOYMENTS_PRIMARY = Internal.createUniqueKey(ModuleDeployments.MODULE_DEPLOYMENTS, DSL.name("KEY_module_deployments_PRIMARY"), new TableField[] { ModuleDeployments.MODULE_DEPLOYMENTS.ID }, true);
    public static final UniqueKey<ModuleTagRecord> KEY_MODULE_TAG_PRIMARY = Internal.createUniqueKey(ModuleTag.MODULE_TAG, DSL.name("KEY_module_tag_PRIMARY"), new TableField[] { ModuleTag.MODULE_TAG.ID }, true);
    public static final UniqueKey<ReleaseNoteRecord> KEY_RELEASE_NOTE_PRIMARY = Internal.createUniqueKey(ReleaseNote.RELEASE_NOTE, DSL.name("KEY_release_note_PRIMARY"), new TableField[] { ReleaseNote.RELEASE_NOTE.MODULE_ID, ReleaseNote.RELEASE_NOTE.VERSION_NO }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<CustomerModuleDeploymentRecord, CustomerRecord> CUSTOMER_MODULE_DEPLOYMENT_IBFK_1 = Internal.createForeignKey(CustomerModuleDeployment.CUSTOMER_MODULE_DEPLOYMENT, DSL.name("customer_module_deployment_ibfk_1"), new TableField[] { CustomerModuleDeployment.CUSTOMER_MODULE_DEPLOYMENT.CUSTOMER_ID }, Keys.KEY_CUSTOMER_PRIMARY, new TableField[] { Customer.CUSTOMER.ID }, true);
    public static final ForeignKey<CustomerModuleDeploymentRecord, ModuleRecord> CUSTOMER_MODULE_DEPLOYMENT_IBFK_2 = Internal.createForeignKey(CustomerModuleDeployment.CUSTOMER_MODULE_DEPLOYMENT, DSL.name("customer_module_deployment_ibfk_2"), new TableField[] { CustomerModuleDeployment.CUSTOMER_MODULE_DEPLOYMENT.MODULE_ID }, Keys.KEY_MODULE_PRIMARY, new TableField[] { Module.MODULE.ID }, true);
    public static final ForeignKey<DeploymentLocationRecord, CustomerRecord> DEPLOYMENT_LOCATION_IBFK_1 = Internal.createForeignKey(DeploymentLocation.DEPLOYMENT_LOCATION, DSL.name("deployment_location_ibfk_1"), new TableField[] { DeploymentLocation.DEPLOYMENT_LOCATION.CUSTOMER_ID }, Keys.KEY_CUSTOMER_PRIMARY, new TableField[] { Customer.CUSTOMER.ID }, true);
    public static final ForeignKey<DevReleaseNoteRecord, ModuleTagRecord> DEV_RELEASE_NOTE_IBFK_1 = Internal.createForeignKey(DevReleaseNote.DEV_RELEASE_NOTE, DSL.name("dev_release_note_ibfk_1"), new TableField[] { DevReleaseNote.DEV_RELEASE_NOTE.TAG_ID }, Keys.KEY_MODULE_TAG_PRIMARY, new TableField[] { ModuleTag.MODULE_TAG.ID }, true);
    public static final ForeignKey<ModuleDependencyRecord, ModuleRecord> MODULE_DEPENDENCY_IBFK_1 = Internal.createForeignKey(ModuleDependency.MODULE_DEPENDENCY, DSL.name("module_dependency_ibfk_1"), new TableField[] { ModuleDependency.MODULE_DEPENDENCY.MODULE_ID }, Keys.KEY_MODULE_PRIMARY, new TableField[] { Module.MODULE.ID }, true);
}