package no.geonorge.nedlasting.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.access.DataDomain;
import org.apache.cayenne.access.DataNode;
import org.apache.cayenne.configuration.DataNodeDescriptor;
import org.apache.cayenne.configuration.server.DataSourceFactory;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.dba.DbAdapter;
import org.apache.cayenne.map.DataMap;
import org.apache.cayenne.merge.AbstractToDbToken;
import org.apache.cayenne.merge.DbMerger;
import org.apache.cayenne.merge.DropColumnToDb;
import org.apache.cayenne.merge.DropTableToDb;
import org.apache.cayenne.merge.ExecutingMergerContext;
import org.apache.cayenne.merge.MergerToken;
import org.apache.commons.dbcp2.BasicDataSource;

import no.geonorge.nedlasting.data.upgrade.DbUpgrade;
import no.geonorge.nedlasting.utils.IOUtils;

public class Config implements DataSourceFactory {

    private static final String KEY_SERVER_PORT = "server.port";
    private static final String KEY_JETTY_PORT = "jetty.port";

    private static final String KEY_DATABASE_DRIVER = "database.driver";
    private static final String KEY_DATABASE_URL = "database.url";
    private static final String KEY_DATABASE_USERNAME = "database.username";
    private static final String KEY_DATABASE_PASSWORD = "database.password";
    private static final String KEY_ALLOWED_FILETYPES = "allowed_filetypes";
    private static final String KEY_ALLOWED_HOSTS = "allowed_hosts";
    private static final String KEY_SUPPORT_DOWNLOAD_BUNDLING = "supportDownloadBundling";
    private static final String KEY_DISTRIBUTED_BY = "distributedBy";
    
    private static final String KEY_CORS = "cors";
    private static final java.lang.String KEY_ORDER_VALID_DAYS = "order.days_valid";

    private static ServerRuntime runtime;
    private static boolean inited = false;
    private static int serverPort = 10000;
    private static DataSource dataSource;
    private static String cors;
    private static boolean enableFileProxy = true; // default true. Override in property-file: fileproxy.enable=false
    private static List<String> allowedHosts = new ArrayList<>();
    private static List<String> allowedFiletypes = new ArrayList<>();
    private static int orderValidDays = 7; // default value. Can be overridden by property order.days_valid
    private static boolean supportsDownloadBundling = false; // default false
    private static String distributedBy;

    private static final Logger log = Logger.getLogger(Config.class.getName());
    public static List<String> getAllowedFiletypes;

    static {
        log.info("setting up cayenne ServerRuntime");
        runtime = new ServerRuntime("cayenne-domain.xml");
    }

    public static void readConfiguration() throws IOException {

        Properties prop = new Properties();

        for (String file : Arrays.asList("/etc/geonorge.properties", "geonorge.properties")) {
            File configFile = new File(file);
            if (!configFile.canRead()) {
                continue;
            }
            InputStream input = null;
            try {
                input = new FileInputStream(configFile);
                prop.load(input);
                log.info("read configuration from " + configFile);
            } finally {
                IOUtils.close(input);
            }
        }

        // look for two different variables for port number. This is to both
        // be compatible with geonorge.properties and 
        // https://github.com/ElectronicChartCentre/deploy-scripts
        Config.serverPort = getProperty(prop, KEY_SERVER_PORT, Config.serverPort);
        Config.serverPort = getProperty(prop, KEY_JETTY_PORT, Config.serverPort);
        String _enableFileproxy = getProperty(prop,"fileproxy.enable","true");
        if (_enableFileproxy.equalsIgnoreCase("true")) {
            Config.enableFileProxy = true;
        }
        if (_enableFileproxy.equalsIgnoreCase("false")) {
            Config.enableFileProxy = false;
        }
        
        Config.cors = getProperty(prop, KEY_CORS, "*");
        String _allowedHosts = getProperty(prop,KEY_ALLOWED_HOSTS,null);
        if (_allowedHosts != null) {
            String[] _hosts = _allowedHosts.split(",");
            for (String _host:_hosts) {
                getAllowedHosts().add(_host);
            }
        }
        String _allowedFiletypes = getProperty(prop,KEY_ALLOWED_FILETYPES,null);
        if (_allowedFiletypes != null) {
            String[] _filetypes = _allowedFiletypes.split(",");
            for (String _filetype:_filetypes) {
                getAllowedFiletypes().add(_filetype);
            }
        } else {
            Collections.addAll(getAllowedFiletypes(), new String[]{"zip", "sos", "fgdb", "gz", "tar.gz", "tgz"});
        }

        String _orderDaysValid = getProperty(prop,KEY_ORDER_VALID_DAYS,null);
        if (_orderDaysValid != null) {
            try {
                orderValidDays = Integer.parseInt(_orderDaysValid);
            } catch (Exception e) {
                e.printStackTrace();
                orderValidDays = 7;
            }
        } else {
            orderValidDays = 7;
        }

        String _supportsDownloadBundling = getProperty(prop,KEY_SUPPORT_DOWNLOAD_BUNDLING,null);
        if (_supportsDownloadBundling != null) {
            if (_supportsDownloadBundling.equalsIgnoreCase("true")) {
                supportsDownloadBundling = true;
            } else {
                supportsDownloadBundling = false;
            }
        }
        String _distributedBy = getProperty(prop,KEY_DISTRIBUTED_BY,null);
        if (_distributedBy != null) {
            distributedBy = _distributedBy;
        } else {
            System.err.println("Missing property in geonorge.properties: " + KEY_DISTRIBUTED_BY);
            System.err.println("Using default: Geonorge");
            distributedBy = "Geonorge";
        }

        BasicDataSource nds = new BasicDataSource();
        nds.setUrl(getRequiredProperty(prop, KEY_DATABASE_URL));
        nds.setDriverClassName(getRequiredProperty(prop, KEY_DATABASE_DRIVER));
        nds.setUsername(getProperty(prop, KEY_DATABASE_USERNAME));
        nds.setPassword(getProperty(prop, KEY_DATABASE_PASSWORD));
        setDataSource(nds);

    }

    public static boolean isEnableFileProxy() {
        return enableFileProxy;
    }

    public static List<String> getAllowedFiletypes() {
        return allowedFiletypes;
    }

    public static List<String> getAllowedHosts() {
        return allowedHosts;
    }

    public static int getOrderDaysValid() {
        return orderValidDays;
    }

    public static int getServerPort() {
        return serverPort;
    }

    public static void setDataSource(DataSource ds) {
        Config.dataSource = ds;
        init();
    }

    public static DataSource getDataSource() {
        if (Config.dataSource == null) {
            throw new IllegalStateException("A jdbc data source not configured");
        }
        return Config.dataSource;
    }

    public static ObjectContext getObjectContext() {
        return runtime.getContext();
    }

    private static void init() {
        if (inited) {
            return;
        }
        inited = true;

        log.info("init geonorge download..");

        if (dataSource == null) {
            log.info("missing data source. not good.");
        }

        DataDomain domain = runtime.getDataDomain();
        DataMap dataMap = domain.getDataMap("datamap");
        DataNode dataNode = domain.getDataNode("datanode");
        DbAdapter adapter = dataNode.getAdapter();

        DbMerger merger = new DbMerger();
        List<MergerToken> tokens = merger.createMergeTokens(dataNode, dataMap);
        List<AbstractToDbToken> toDbTokens = new ArrayList<AbstractToDbToken>();
        for (MergerToken token : tokens) {
            if (token.getDirection().isToModel()) {
                token = token.createReverse(adapter.mergerFactory());
            }
            if (token instanceof DropTableToDb) {
                continue;
            }
            if (token instanceof DropColumnToDb) {
                continue;
            }
            if (token instanceof AbstractToDbToken) {
                AbstractToDbToken dbToken = (AbstractToDbToken) token;
                toDbTokens.add(dbToken);
            }
        }

        ExecutingMergerContext mc = new ExecutingMergerContext(dataMap, dataNode);
        for (AbstractToDbToken token : toDbTokens) {
            token.execute(mc);
        }
        
        DbUpgrade.upgrade();

        log.info("inited geonorge download..");
    }

    private static String getProperty(Properties prop, String key) {
        String v = prop.getProperty(key);
        if (v == null) {
            v = System.getProperty(key);
        }
        if (v == null) {
            v = System.getenv(key);
        }
        return v;
    }

    private static String getRequiredProperty(Properties prop, String key) {
        String v = getProperty(prop, key);
        if (v == null) {
            throw new IllegalStateException("missing required configuration parameter: " + key);
        }
        return v;
    }

    private static int getProperty(Properties prop, String key, int defaultValue) {
        String v = getProperty(prop, key);
        return v == null ? defaultValue : Integer.parseInt(v);
    }
    
    private static String getProperty(Properties prop, String key, String defaultValue) {
        String v = getProperty(prop, key);
        return v == null ? defaultValue : v;
    }

    public DataSource getDataSource(DataNodeDescriptor desc) throws Exception {
        return dataSource;
    }
    
    public static String getCors() {
        return cors;
    }

    public static String getKeyDistributedBy() { return distributedBy; }

    public static boolean isSupportsDownloadBundling() { return supportsDownloadBundling; }

}
