package dao;

/**
 * Created by jswense2 on 10/16/18.
 */

import com.couchbase.client.core.error.CouchbaseException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.query.QueryOptions;
import com.couchbase.client.java.query.QueryResult;

public class CouchBaseDB {
    private static String connectionString = "localhost";
    private static CouchBaseDB _dataBase;
    private static Cluster cluster;

    private CouchBaseDB() {
    }

    public static CouchBaseDB getInstance()
    {
        if (_dataBase == null)
        {
            _dataBase = new CouchBaseDB();
        }
        return _dataBase;
    }

    public static QueryResult query(String query, QueryOptions q)
    {
        try
        {
            if (q != null)
            {
                return cluster.query(query, q);
            }
            else
            {
                return cluster.query(query);
            }
        } catch (CouchbaseException e)
        {
            System.err.println("CouchDB has a problem querying: " + e.getMessage());
            return null;
        }
    }

    // execute SQL update with query
    public static Bucket getBucket(String bucketName)
    {
        try
        {
            return cluster.bucket(bucketName);

        } catch (Exception e)
        {
            System.err.println("CouchDB has a problem getting bucket " + bucketName + ": " + e.getMessage());
            return null;
        }
    }

    // open up a connection
    public static void openConnection()
    {
        cluster = Cluster.connect(connectionString, "admin", "kittyPass");
    }

    // close the connection
    public static void closeConnection()
    {
        cluster.disconnect();
    }
}
