package no.geonorge.nedlasting.data.auto;

import org.apache.cayenne.CayenneDataObject;

import no.geonorge.nedlasting.data.Dataset;

/**
 * Class _DatasetFile was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _DatasetFile extends CayenneDataObject {

    public static final String CATEGORY_PROPERTY = "category";
    public static final String CATEGORY_SUB_PROPERTY = "categorySub";
    public static final String FILE_NAME_PROPERTY = "fileName";
    public static final String FORMAT_PROPERTY = "format";
    public static final String PROJECTION_PROPERTY = "projection";
    public static final String SEGMENT_PROPERTY = "segment";
    public static final String SEGMENT_VALUE_PROPERTY = "segmentValue";
    public static final String URL_PROPERTY = "url";
    public static final String DATASET_PROPERTY = "dataset";

    public static final String DATASET_ID_PK_COLUMN = "DATASET_ID";
    public static final String URL_PK_COLUMN = "URL";

    public void setCategory(String category) {
        writeProperty(CATEGORY_PROPERTY, category);
    }
    public String getCategory() {
        return (String)readProperty(CATEGORY_PROPERTY);
    }

    public void setCategorySub(String categorySub) {
        writeProperty(CATEGORY_SUB_PROPERTY, categorySub);
    }
    public String getCategorySub() {
        return (String)readProperty(CATEGORY_SUB_PROPERTY);
    }

    public void setFileName(String fileName) {
        writeProperty(FILE_NAME_PROPERTY, fileName);
    }
    public String getFileName() {
        return (String)readProperty(FILE_NAME_PROPERTY);
    }

    public void setFormat(String format) {
        writeProperty(FORMAT_PROPERTY, format);
    }
    public String getFormat() {
        return (String)readProperty(FORMAT_PROPERTY);
    }

    public void setProjection(String projection) {
        writeProperty(PROJECTION_PROPERTY, projection);
    }
    public String getProjection() {
        return (String)readProperty(PROJECTION_PROPERTY);
    }

    public void setSegment(String segment) {
        writeProperty(SEGMENT_PROPERTY, segment);
    }
    public String getSegment() {
        return (String)readProperty(SEGMENT_PROPERTY);
    }

    public void setSegmentValue(String segmentValue) {
        writeProperty(SEGMENT_VALUE_PROPERTY, segmentValue);
    }
    public String getSegmentValue() {
        return (String)readProperty(SEGMENT_VALUE_PROPERTY);
    }

    public void setUrl(String url) {
        writeProperty(URL_PROPERTY, url);
    }
    public String getUrl() {
        return (String)readProperty(URL_PROPERTY);
    }

    public void setDataset(Dataset dataset) {
        setToOneTarget(DATASET_PROPERTY, dataset, true);
    }

    public Dataset getDataset() {
        return (Dataset)readProperty(DATASET_PROPERTY);
    }


}
