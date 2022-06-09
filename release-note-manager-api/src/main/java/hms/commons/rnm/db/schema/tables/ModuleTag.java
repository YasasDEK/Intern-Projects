/*
 * This file is generated by jOOQ.
 */
package hms.commons.rnm.db.schema.tables;


import hms.commons.rnm.db.schema.DefaultSchema;
import hms.commons.rnm.db.schema.Keys;
import hms.commons.rnm.db.schema.tables.records.ModuleTagRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ModuleTag extends TableImpl<ModuleTagRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>module_tag</code>
     */
    public static final ModuleTag MODULE_TAG = new ModuleTag();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ModuleTagRecord> getRecordType() {
        return ModuleTagRecord.class;
    }

    /**
     * The column <code>module_tag.id</code>.
     */
    public final TableField<ModuleTagRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>module_tag.module_id</code>.
     */
    public final TableField<ModuleTagRecord, Integer> MODULE_ID = createField(DSL.name("module_id"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>module_tag.version_no</code>.
     */
    public final TableField<ModuleTagRecord, String> VERSION_NO = createField(DSL.name("version_no"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>module_tag.branch_name</code>.
     */
    public final TableField<ModuleTagRecord, String> BRANCH_NAME = createField(DSL.name("branch_name"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>module_tag.created_date</code>.
     */
    public final TableField<ModuleTagRecord, LocalDateTime> CREATED_DATE = createField(DSL.name("created_date"), SQLDataType.LOCALDATETIME(0).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>module_tag.last_updated_time</code>.
     */
    public final TableField<ModuleTagRecord, LocalDateTime> LAST_UPDATED_TIME = createField(DSL.name("last_updated_time"), SQLDataType.LOCALDATETIME(0).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.LOCALDATETIME)), this, "");

    private ModuleTag(Name alias, Table<ModuleTagRecord> aliased) {
        this(alias, aliased, null);
    }

    private ModuleTag(Name alias, Table<ModuleTagRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>module_tag</code> table reference
     */
    public ModuleTag(String alias) {
        this(DSL.name(alias), MODULE_TAG);
    }

    /**
     * Create an aliased <code>module_tag</code> table reference
     */
    public ModuleTag(Name alias) {
        this(alias, MODULE_TAG);
    }

    /**
     * Create a <code>module_tag</code> table reference
     */
    public ModuleTag() {
        this(DSL.name("module_tag"), null);
    }

    public <O extends Record> ModuleTag(Table<O> child, ForeignKey<O, ModuleTagRecord> key) {
        super(child, key, MODULE_TAG);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<ModuleTagRecord, Integer> getIdentity() {
        return (Identity<ModuleTagRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<ModuleTagRecord> getPrimaryKey() {
        return Keys.KEY_MODULE_TAG_PRIMARY;
    }

    @Override
    public List<UniqueKey<ModuleTagRecord>> getKeys() {
        return Arrays.<UniqueKey<ModuleTagRecord>>asList(Keys.KEY_MODULE_TAG_PRIMARY);
    }

    @Override
    public ModuleTag as(String alias) {
        return new ModuleTag(DSL.name(alias), this);
    }

    @Override
    public ModuleTag as(Name alias) {
        return new ModuleTag(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ModuleTag rename(String name) {
        return new ModuleTag(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ModuleTag rename(Name name) {
        return new ModuleTag(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, Integer, String, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}