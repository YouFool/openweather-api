package br.com.hbsis.openweather.service;

import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.*;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

@Service
public class MongoImportService {

    /**
     * Imports cities in JSON file.
     */
    public void importCitiesFile() throws UnknownHostException, IOException {
        int defaultConfigPort = 27017;
        String defaultHost = "localhost";
        String database = "test";
        String collection = "city";
        File jsonFile = new File(Thread.currentThread().getContextClassLoader().getResource("static/city.list.json").getFile());
        String jsonPath = jsonFile.getAbsolutePath();
        MongodProcess mongod = startMongod(defaultConfigPort);


        try {
            MongoImportProcess mongoImport = startMongoImport(defaultHost, defaultConfigPort, database, collection, jsonPath, true, true, true);
            try {
                MongoClient mongoClient = new MongoClient(defaultHost, defaultConfigPort);
                System.out.println("DB Names: " + mongoClient.getDatabaseNames());
            } finally {
                mongoImport.stop();
            }
        } finally {
            mongod.stop();
        }
    }

    private MongoImportProcess startMongoImport(String bindIp, int port, String dbName, String collection, String jsonFile, Boolean jsonArray, Boolean upsert, Boolean drop)
            throws IOException {
        IMongoImportConfig mongoImportConfig = new MongoImportConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                .db(dbName)
                .collection(collection)
                .upsert(upsert)
                .dropCollection(drop)
                .jsonArray(jsonArray)
                .importFile(jsonFile)
                .build();


        MongoImportExecutable mongoImportExecutable = MongoImportStarter.getDefaultInstance().prepare(mongoImportConfig);
        MongoImportProcess mongoImport = mongoImportExecutable.start();
        return mongoImport;
    }

    private MongodProcess startMongod(int defaultConfigPort) throws IOException {
        IMongodConfig mongoConfigConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(defaultConfigPort, Network.localhostIsIPv6()))
                .build();


        MongodExecutable mongodExecutable = MongodStarter.getDefaultInstance().prepare(mongoConfigConfig);
        MongodProcess mongod = mongodExecutable.start();
        return mongod;
    }

}
