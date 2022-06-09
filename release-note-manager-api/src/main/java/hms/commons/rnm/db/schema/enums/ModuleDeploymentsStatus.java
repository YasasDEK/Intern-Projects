/*
 * This file is generated by jOOQ.
 */
package hms.commons.rnm.db.schema.enums;


import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public enum ModuleDeploymentsStatus implements EnumType {

    Planned("Planned"),

    Active("Active"),

    Rejected("Rejected"),

    Retierd("Retierd");

    private final String literal;

    private ModuleDeploymentsStatus(String literal) {
        this.literal = literal;
    }

    @Override
    public Catalog getCatalog() {
        return null;
    }

    @Override
    public Schema getSchema() {
        return null;
    }

    @Override
    public String getName() {
        return "module_deployments_status";
    }

    @Override
    public String getLiteral() {
        return literal;
    }
}