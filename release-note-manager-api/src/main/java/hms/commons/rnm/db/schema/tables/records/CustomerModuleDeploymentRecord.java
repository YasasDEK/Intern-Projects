/*
 * This file is generated by jOOQ.
 */
package hms.commons.rnm.db.schema.tables.records;


import hms.commons.rnm.db.schema.tables.CustomerModuleDeployment;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CustomerModuleDeploymentRecord extends UpdatableRecordImpl<CustomerModuleDeploymentRecord> implements Record5<Integer, Integer, LocalDateTime, LocalDateTime, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>customer_module_deployment.customer_id</code>.
     */
    public void setCustomerId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>customer_module_deployment.customer_id</code>.
     */
    public Integer getCustomerId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>customer_module_deployment.module_id</code>.
     */
    public void setModuleId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>customer_module_deployment.module_id</code>.
     */
    public Integer getModuleId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>customer_module_deployment.created_date</code>.
     */
    public void setCreatedDate(LocalDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>customer_module_deployment.created_date</code>.
     */
    public LocalDateTime getCreatedDate() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>customer_module_deployment.last_updated_time</code>.
     */
    public void setLastUpdatedTime(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>customer_module_deployment.last_updated_time</code>.
     */
    public LocalDateTime getLastUpdatedTime() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>customer_module_deployment.id</code>.
     */
    public void setId(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>customer_module_deployment.id</code>.
     */
    public Integer getId() {
        return (Integer) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, Integer, LocalDateTime, LocalDateTime, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Integer, Integer, LocalDateTime, LocalDateTime, Integer> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return CustomerModuleDeployment.CUSTOMER_MODULE_DEPLOYMENT.CUSTOMER_ID;
    }

    @Override
    public Field<Integer> field2() {
        return CustomerModuleDeployment.CUSTOMER_MODULE_DEPLOYMENT.MODULE_ID;
    }

    @Override
    public Field<LocalDateTime> field3() {
        return CustomerModuleDeployment.CUSTOMER_MODULE_DEPLOYMENT.CREATED_DATE;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return CustomerModuleDeployment.CUSTOMER_MODULE_DEPLOYMENT.LAST_UPDATED_TIME;
    }

    @Override
    public Field<Integer> field5() {
        return CustomerModuleDeployment.CUSTOMER_MODULE_DEPLOYMENT.ID;
    }

    @Override
    public Integer component1() {
        return getCustomerId();
    }

    @Override
    public Integer component2() {
        return getModuleId();
    }

    @Override
    public LocalDateTime component3() {
        return getCreatedDate();
    }

    @Override
    public LocalDateTime component4() {
        return getLastUpdatedTime();
    }

    @Override
    public Integer component5() {
        return getId();
    }

    @Override
    public Integer value1() {
        return getCustomerId();
    }

    @Override
    public Integer value2() {
        return getModuleId();
    }

    @Override
    public LocalDateTime value3() {
        return getCreatedDate();
    }

    @Override
    public LocalDateTime value4() {
        return getLastUpdatedTime();
    }

    @Override
    public Integer value5() {
        return getId();
    }

    @Override
    public CustomerModuleDeploymentRecord value1(Integer value) {
        setCustomerId(value);
        return this;
    }

    @Override
    public CustomerModuleDeploymentRecord value2(Integer value) {
        setModuleId(value);
        return this;
    }

    @Override
    public CustomerModuleDeploymentRecord value3(LocalDateTime value) {
        setCreatedDate(value);
        return this;
    }

    @Override
    public CustomerModuleDeploymentRecord value4(LocalDateTime value) {
        setLastUpdatedTime(value);
        return this;
    }

    @Override
    public CustomerModuleDeploymentRecord value5(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public CustomerModuleDeploymentRecord values(Integer value1, Integer value2, LocalDateTime value3, LocalDateTime value4, Integer value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CustomerModuleDeploymentRecord
     */
    public CustomerModuleDeploymentRecord() {
        super(CustomerModuleDeployment.CUSTOMER_MODULE_DEPLOYMENT);
    }

    /**
     * Create a detached, initialised CustomerModuleDeploymentRecord
     */
    public CustomerModuleDeploymentRecord(Integer customerId, Integer moduleId, LocalDateTime createdDate, LocalDateTime lastUpdatedTime, Integer id) {
        super(CustomerModuleDeployment.CUSTOMER_MODULE_DEPLOYMENT);

        setCustomerId(customerId);
        setModuleId(moduleId);
        setCreatedDate(createdDate);
        setLastUpdatedTime(lastUpdatedTime);
        setId(id);
    }
}
