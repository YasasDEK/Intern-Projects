/*
 * This file is generated by jOOQ.
 */
package hms.commons.rnm.db.schema.tables.records;


import hms.commons.rnm.db.schema.tables.ModuleTag;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ModuleTagRecord extends UpdatableRecordImpl<ModuleTagRecord> implements Record6<Integer, Integer, String, String, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>module_tag.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>module_tag.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>module_tag.module_id</code>.
     */
    public void setModuleId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>module_tag.module_id</code>.
     */
    public Integer getModuleId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>module_tag.version_no</code>.
     */
    public void setVersionNo(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>module_tag.version_no</code>.
     */
    public String getVersionNo() {
        return (String) get(2);
    }

    /**
     * Setter for <code>module_tag.branch_name</code>.
     */
    public void setBranchName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>module_tag.branch_name</code>.
     */
    public String getBranchName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>module_tag.created_date</code>.
     */
    public void setCreatedDate(LocalDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>module_tag.created_date</code>.
     */
    public LocalDateTime getCreatedDate() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>module_tag.last_updated_time</code>.
     */
    public void setLastUpdatedTime(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>module_tag.last_updated_time</code>.
     */
    public LocalDateTime getLastUpdatedTime() {
        return (LocalDateTime) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, Integer, String, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Integer, Integer, String, String, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return ModuleTag.MODULE_TAG.ID;
    }

    @Override
    public Field<Integer> field2() {
        return ModuleTag.MODULE_TAG.MODULE_ID;
    }

    @Override
    public Field<String> field3() {
        return ModuleTag.MODULE_TAG.VERSION_NO;
    }

    @Override
    public Field<String> field4() {
        return ModuleTag.MODULE_TAG.BRANCH_NAME;
    }

    @Override
    public Field<LocalDateTime> field5() {
        return ModuleTag.MODULE_TAG.CREATED_DATE;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return ModuleTag.MODULE_TAG.LAST_UPDATED_TIME;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public Integer component2() {
        return getModuleId();
    }

    @Override
    public String component3() {
        return getVersionNo();
    }

    @Override
    public String component4() {
        return getBranchName();
    }

    @Override
    public LocalDateTime component5() {
        return getCreatedDate();
    }

    @Override
    public LocalDateTime component6() {
        return getLastUpdatedTime();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public Integer value2() {
        return getModuleId();
    }

    @Override
    public String value3() {
        return getVersionNo();
    }

    @Override
    public String value4() {
        return getBranchName();
    }

    @Override
    public LocalDateTime value5() {
        return getCreatedDate();
    }

    @Override
    public LocalDateTime value6() {
        return getLastUpdatedTime();
    }

    @Override
    public ModuleTagRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public ModuleTagRecord value2(Integer value) {
        setModuleId(value);
        return this;
    }

    @Override
    public ModuleTagRecord value3(String value) {
        setVersionNo(value);
        return this;
    }

    @Override
    public ModuleTagRecord value4(String value) {
        setBranchName(value);
        return this;
    }

    @Override
    public ModuleTagRecord value5(LocalDateTime value) {
        setCreatedDate(value);
        return this;
    }

    @Override
    public ModuleTagRecord value6(LocalDateTime value) {
        setLastUpdatedTime(value);
        return this;
    }

    @Override
    public ModuleTagRecord values(Integer value1, Integer value2, String value3, String value4, LocalDateTime value5, LocalDateTime value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ModuleTagRecord
     */
    public ModuleTagRecord() {
        super(ModuleTag.MODULE_TAG);
    }

    /**
     * Create a detached, initialised ModuleTagRecord
     */
    public ModuleTagRecord(Integer id, Integer moduleId, String versionNo, String branchName, LocalDateTime createdDate, LocalDateTime lastUpdatedTime) {
        super(ModuleTag.MODULE_TAG);

        setId(id);
        setModuleId(moduleId);
        setVersionNo(versionNo);
        setBranchName(branchName);
        setCreatedDate(createdDate);
        setLastUpdatedTime(lastUpdatedTime);
    }
}