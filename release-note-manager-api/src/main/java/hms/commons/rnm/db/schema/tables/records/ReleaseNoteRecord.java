/*
 * This file is generated by jOOQ.
 */
package hms.commons.rnm.db.schema.tables.records;


import hms.commons.rnm.db.schema.tables.ReleaseNote;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.JSON;
import org.jooq.Record18;
import org.jooq.Record2;
import org.jooq.Row18;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ReleaseNoteRecord extends UpdatableRecordImpl<ReleaseNoteRecord> implements Record18<Integer, Integer, String, String, String, LocalDate, String, JSON, String, String, String, String, String, String, String, LocalDateTime, LocalDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>release_note.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>release_note.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>release_note.module_id</code>.
     */
    public void setModuleId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>release_note.module_id</code>.
     */
    public Integer getModuleId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>release_note.version_no</code>.
     */
    public void setVersionNo(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>release_note.version_no</code>.
     */
    public String getVersionNo() {
        return (String) get(2);
    }

    /**
     * Setter for <code>release_note.release_by</code>.
     */
    public void setReleaseBy(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>release_note.release_by</code>.
     */
    public String getReleaseBy() {
        return (String) get(3);
    }

    /**
     * Setter for <code>release_note.branch</code>.
     */
    public void setBranch(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>release_note.branch</code>.
     */
    public String getBranch() {
        return (String) get(4);
    }

    /**
     * Setter for <code>release_note.release_date</code>.
     */
    public void setReleaseDate(LocalDate value) {
        set(5, value);
    }

    /**
     * Getter for <code>release_note.release_date</code>.
     */
    public LocalDate getReleaseDate() {
        return (LocalDate) get(5);
    }

    /**
     * Setter for <code>release_note.apply_on_top_of_version</code>.
     */
    public void setApplyOnTopOfVersion(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>release_note.apply_on_top_of_version</code>.
     */
    public String getApplyOnTopOfVersion() {
        return (String) get(6);
    }

    /**
     * Setter for <code>release_note.dependant_version</code>.
     */
    public void setDependantVersion(JSON value) {
        set(7, value);
    }

    /**
     * Getter for <code>release_note.dependant_version</code>.
     */
    public JSON getDependantVersion() {
        return (JSON) get(7);
    }

    /**
     * Setter for <code>release_note.status</code>.
     */
    public void setStatus(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>release_note.status</code>.
     */
    public String getStatus() {
        return (String) get(8);
    }

    /**
     * Setter for <code>release_note.build_instructions</code>.
     */
    public void setBuildInstructions(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>release_note.build_instructions</code>.
     */
    public String getBuildInstructions() {
        return (String) get(9);
    }

    /**
     * Setter for <code>release_note.fixed_bugs</code>.
     */
    public void setFixedBugs(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>release_note.fixed_bugs</code>.
     */
    public String getFixedBugs() {
        return (String) get(10);
    }

    /**
     * Setter for <code>release_note.known_issues</code>.
     */
    public void setKnownIssues(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>release_note.known_issues</code>.
     */
    public String getKnownIssues() {
        return (String) get(11);
    }

    /**
     * Setter for <code>release_note.deployment_details</code>.
     */
    public void setDeploymentDetails(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>release_note.deployment_details</code>.
     */
    public String getDeploymentDetails() {
        return (String) get(12);
    }

    /**
     * Setter for <code>release_note.areas_to_be_tested</code>.
     */
    public void setAreasToBeTested(String value) {
        set(13, value);
    }

    /**
     * Getter for <code>release_note.areas_to_be_tested</code>.
     */
    public String getAreasToBeTested() {
        return (String) get(13);
    }

    /**
     * Setter for <code>release_note.reference_docs</code>.
     */
    public void setReferenceDocs(String value) {
        set(14, value);
    }

    /**
     * Getter for <code>release_note.reference_docs</code>.
     */
    public String getReferenceDocs() {
        return (String) get(14);
    }

    /**
     * Setter for <code>release_note.created_date</code>.
     */
    public void setCreatedDate(LocalDateTime value) {
        set(15, value);
    }

    /**
     * Getter for <code>release_note.created_date</code>.
     */
    public LocalDateTime getCreatedDate() {
        return (LocalDateTime) get(15);
    }

    /**
     * Setter for <code>release_note.last_updated_time</code>.
     */
    public void setLastUpdatedTime(LocalDateTime value) {
        set(16, value);
    }

    /**
     * Getter for <code>release_note.last_updated_time</code>.
     */
    public LocalDateTime getLastUpdatedTime() {
        return (LocalDateTime) get(16);
    }

    /**
     * Setter for <code>release_note.feature_summary</code>.
     */
    public void setFeatureSummary(String value) {
        set(17, value);
    }

    /**
     * Getter for <code>release_note.feature_summary</code>.
     */
    public String getFeatureSummary() {
        return (String) get(17);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Integer, String> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record18 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row18<Integer, Integer, String, String, String, LocalDate, String, JSON, String, String, String, String, String, String, String, LocalDateTime, LocalDateTime, String> fieldsRow() {
        return (Row18) super.fieldsRow();
    }

    @Override
    public Row18<Integer, Integer, String, String, String, LocalDate, String, JSON, String, String, String, String, String, String, String, LocalDateTime, LocalDateTime, String> valuesRow() {
        return (Row18) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return ReleaseNote.RELEASE_NOTE.ID;
    }

    @Override
    public Field<Integer> field2() {
        return ReleaseNote.RELEASE_NOTE.MODULE_ID;
    }

    @Override
    public Field<String> field3() {
        return ReleaseNote.RELEASE_NOTE.VERSION_NO;
    }

    @Override
    public Field<String> field4() {
        return ReleaseNote.RELEASE_NOTE.RELEASE_BY;
    }

    @Override
    public Field<String> field5() {
        return ReleaseNote.RELEASE_NOTE.BRANCH;
    }

    @Override
    public Field<LocalDate> field6() {
        return ReleaseNote.RELEASE_NOTE.RELEASE_DATE;
    }

    @Override
    public Field<String> field7() {
        return ReleaseNote.RELEASE_NOTE.APPLY_ON_TOP_OF_VERSION;
    }

    @Override
    public Field<JSON> field8() {
        return ReleaseNote.RELEASE_NOTE.DEPENDANT_VERSION;
    }

    @Override
    public Field<String> field9() {
        return ReleaseNote.RELEASE_NOTE.STATUS;
    }

    @Override
    public Field<String> field10() {
        return ReleaseNote.RELEASE_NOTE.BUILD_INSTRUCTIONS;
    }

    @Override
    public Field<String> field11() {
        return ReleaseNote.RELEASE_NOTE.FIXED_BUGS;
    }

    @Override
    public Field<String> field12() {
        return ReleaseNote.RELEASE_NOTE.KNOWN_ISSUES;
    }

    @Override
    public Field<String> field13() {
        return ReleaseNote.RELEASE_NOTE.DEPLOYMENT_DETAILS;
    }

    @Override
    public Field<String> field14() {
        return ReleaseNote.RELEASE_NOTE.AREAS_TO_BE_TESTED;
    }

    @Override
    public Field<String> field15() {
        return ReleaseNote.RELEASE_NOTE.REFERENCE_DOCS;
    }

    @Override
    public Field<LocalDateTime> field16() {
        return ReleaseNote.RELEASE_NOTE.CREATED_DATE;
    }

    @Override
    public Field<LocalDateTime> field17() {
        return ReleaseNote.RELEASE_NOTE.LAST_UPDATED_TIME;
    }

    @Override
    public Field<String> field18() {
        return ReleaseNote.RELEASE_NOTE.FEATURE_SUMMARY;
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
        return getReleaseBy();
    }

    @Override
    public String component5() {
        return getBranch();
    }

    @Override
    public LocalDate component6() {
        return getReleaseDate();
    }

    @Override
    public String component7() {
        return getApplyOnTopOfVersion();
    }

    @Override
    public JSON component8() {
        return getDependantVersion();
    }

    @Override
    public String component9() {
        return getStatus();
    }

    @Override
    public String component10() {
        return getBuildInstructions();
    }

    @Override
    public String component11() {
        return getFixedBugs();
    }

    @Override
    public String component12() {
        return getKnownIssues();
    }

    @Override
    public String component13() {
        return getDeploymentDetails();
    }

    @Override
    public String component14() {
        return getAreasToBeTested();
    }

    @Override
    public String component15() {
        return getReferenceDocs();
    }

    @Override
    public LocalDateTime component16() {
        return getCreatedDate();
    }

    @Override
    public LocalDateTime component17() {
        return getLastUpdatedTime();
    }

    @Override
    public String component18() {
        return getFeatureSummary();
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
        return getReleaseBy();
    }

    @Override
    public String value5() {
        return getBranch();
    }

    @Override
    public LocalDate value6() {
        return getReleaseDate();
    }

    @Override
    public String value7() {
        return getApplyOnTopOfVersion();
    }

    @Override
    public JSON value8() {
        return getDependantVersion();
    }

    @Override
    public String value9() {
        return getStatus();
    }

    @Override
    public String value10() {
        return getBuildInstructions();
    }

    @Override
    public String value11() {
        return getFixedBugs();
    }

    @Override
    public String value12() {
        return getKnownIssues();
    }

    @Override
    public String value13() {
        return getDeploymentDetails();
    }

    @Override
    public String value14() {
        return getAreasToBeTested();
    }

    @Override
    public String value15() {
        return getReferenceDocs();
    }

    @Override
    public LocalDateTime value16() {
        return getCreatedDate();
    }

    @Override
    public LocalDateTime value17() {
        return getLastUpdatedTime();
    }

    @Override
    public String value18() {
        return getFeatureSummary();
    }

    @Override
    public ReleaseNoteRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public ReleaseNoteRecord value2(Integer value) {
        setModuleId(value);
        return this;
    }

    @Override
    public ReleaseNoteRecord value3(String value) {
        setVersionNo(value);
        return this;
    }

    @Override
    public ReleaseNoteRecord value4(String value) {
        setReleaseBy(value);
        return this;
    }

    @Override
    public ReleaseNoteRecord value5(String value) {
        setBranch(value);
        return this;
    }

    @Override
    public ReleaseNoteRecord value6(LocalDate value) {
        setReleaseDate(value);
        return this;
    }

    @Override
    public ReleaseNoteRecord value7(String value) {
        setApplyOnTopOfVersion(value);
        return this;
    }

    @Override
    public ReleaseNoteRecord value8(JSON value) {
        setDependantVersion(value);
        return this;
    }

    @Override
    public ReleaseNoteRecord value9(String value) {
        setStatus(value);
        return this;
    }

    @Override
    public ReleaseNoteRecord value10(String value) {
        setBuildInstructions(value);
        return this;
    }

    @Override
    public ReleaseNoteRecord value11(String value) {
        setFixedBugs(value);
        return this;
    }

    @Override
    public ReleaseNoteRecord value12(String value) {
        setKnownIssues(value);
        return this;
    }

    @Override
    public ReleaseNoteRecord value13(String value) {
        setDeploymentDetails(value);
        return this;
    }

    @Override
    public ReleaseNoteRecord value14(String value) {
        setAreasToBeTested(value);
        return this;
    }

    @Override
    public ReleaseNoteRecord value15(String value) {
        setReferenceDocs(value);
        return this;
    }

    @Override
    public ReleaseNoteRecord value16(LocalDateTime value) {
        setCreatedDate(value);
        return this;
    }

    @Override
    public ReleaseNoteRecord value17(LocalDateTime value) {
        setLastUpdatedTime(value);
        return this;
    }

    @Override
    public ReleaseNoteRecord value18(String value) {
        setFeatureSummary(value);
        return this;
    }

    @Override
    public ReleaseNoteRecord values(Integer value1, Integer value2, String value3, String value4, String value5, LocalDate value6, String value7, JSON value8, String value9, String value10, String value11, String value12, String value13, String value14, String value15, LocalDateTime value16, LocalDateTime value17, String value18) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        value18(value18);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ReleaseNoteRecord
     */
    public ReleaseNoteRecord() {
        super(ReleaseNote.RELEASE_NOTE);
    }

    /**
     * Create a detached, initialised ReleaseNoteRecord
     */
    public ReleaseNoteRecord(Integer id, Integer moduleId, String versionNo, String releaseBy, String branch, LocalDate releaseDate, String applyOnTopOfVersion, JSON dependantVersion, String status, String buildInstructions, String fixedBugs, String knownIssues, String deploymentDetails, String areasToBeTested, String referenceDocs, LocalDateTime createdDate, LocalDateTime lastUpdatedTime, String featureSummary) {
        super(ReleaseNote.RELEASE_NOTE);

        setId(id);
        setModuleId(moduleId);
        setVersionNo(versionNo);
        setReleaseBy(releaseBy);
        setBranch(branch);
        setReleaseDate(releaseDate);
        setApplyOnTopOfVersion(applyOnTopOfVersion);
        setDependantVersion(dependantVersion);
        setStatus(status);
        setBuildInstructions(buildInstructions);
        setFixedBugs(fixedBugs);
        setKnownIssues(knownIssues);
        setDeploymentDetails(deploymentDetails);
        setAreasToBeTested(areasToBeTested);
        setReferenceDocs(referenceDocs);
        setCreatedDate(createdDate);
        setLastUpdatedTime(lastUpdatedTime);
        setFeatureSummary(featureSummary);
    }
}
