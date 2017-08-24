package no.geonorge.nedlasting.data.client;

public class Link {

    private String rel;
    private String href;
    private boolean templatedSpecified = false;

    public Link() {
    }

    public Link(String rel, String href) {
        this.rel = rel;
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isTemplatedSpecified() {
        return templatedSpecified;
    }

}
