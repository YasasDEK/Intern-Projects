/*
 * This file is generated by jOOQ.
 */
package hms.commons.rnm.db.schema.tables;


import hms.commons.rnm.db.schema.DefaultSchema;
import hms.commons.rnm.db.schema.Indexes;
import hms.commons.rnm.db.schema.Keys;
import hms.commons.rnm.db.schema.tables.records.ModuleDependencyRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
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
public class ModuleDependency extends TableImpl<ModuleDependencyRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>module_dependency</code>
     */
    public static final ModuleDependency MODULE_DEPENDENCY = new ModuleDependency();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ModuleDependencyRecord> getRecordType() {
        return ModuleDependencyRecord.class;
    }

    /**
     * The column <code>module_dependency.module_id</code>.
     */
    public final TableField<ModuleDependencyRecord, Integer> MODULE_ID = createField(DSL.name("module_id"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>module_dependency.dependant_module_id</code>.
     */
    public final TableField<ModuleDependencyRecord, Integer> DEPENDANT_MODULE_ID = createField(DSL.name("dependant_module_id"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>module_dependency.created_date</code>.
     */
    public final TableField<ModuleDependencyRecord, LocalDateTime> CREATED_DATE = createField(DSL.name("created_date"), SQLDataType.LOCALDATETIME(0).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>module_dependency.last_updated_time</code>.
     */
    public final TableField<ModuleDependencyRecord, LocalDateTime> LAST_UPDATED_TIME = createField(DSL.name("last_updated_time"), SQLDataType.LOCALDATETIME(0).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>module_dependency.id</code>.
     */
    public final TableField<ModuleDependencyRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    private ModuleDependency(Name alias, Table<ModuleDependencyRecord> aliased) {
        this(alias, aliased, null);
    }

    private ModuleDependency(Name alias, Table<ModuleDependencyRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>module_dependency</code> table reference
     */
    public ModuleDependency(String alias) {
        this(DSL.name(alias), MODULE_DEPENDENCY);
    }

    /**
     * Create an aliased <code>module_dependency</code> table reference
     */
    public ModuleDependency(Name alias) {
        this(alias, MODULE_DEPENDENCY);
    }

    /**
     * Create a <code>module_dependency</code> table reference
     */
    public ModuleDependency() {
        this(DSL.name("module_dependency"), null);
    }

    public <O extends Record> ModuleDependency(Table<O> child, ForeignKey<O, ModuleDependencyRecord> key) {
        super(child, key, MODULE_DEPENDENCY);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.MODULE_DEPENDENCY_MODULE_ID);
    }

    @Override
    public Identity<ModuleDependencyRecord, Integer> getIdentity() {
        return (Identity<ModuleDependencyRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<ModuleDependencyRecord> getPrimaryKey() {
        return Keys.KEY_MODULE_DEPENDENCY_PRIMARY;
    }

    @Override
    public List<UniqueKey<ModuleDependencyRecord>> getKeys() {
        return Arrays.<UniqueKey<ModuleDependencyRecord>>asList(Keys.KEY_MODULE_DEPENDENCY_PRIMARY);
    }

    @Override
    public List<ForeignKey<ModuleDependencyRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ModuleDependencyRecord, ?>>asList(Keys.MODULE_DEPENDENCY_IBFK_1);
    }

    public Module module() {
        return new Module(this, Keys.MODULE_DEPENDENCY_IBFK_1);
    }

    @Override
    public ModuleDependency as(String alias) {
        return new ModuleDependency(DSL.name(alias), this);
    }

    @Override
    public ModuleDependency as(Name alias) {
        return new ModuleDependency(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ModuleDependency rename(String name) {
        return new ModuleDependency(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ModuleDependency rename(Name name) {
        return new ModuleDependency(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, Integer, LocalDateTime, LocalDateTime, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}