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
        String database = "test";
        String collection = "city";
        File jsonFile = new File(Thread.currentThread().getContextClassLoader().getResource("static/city.list.json").getFile());
        String jsonPath = jsonFile.getAbsolutePath();
        MongodProcess mongod = startMongod();


        try {
            MongoImportProcess mongoImport = startMongoImport(database, collection, jsonPath, true, true, true);
        } finally {
            mongod.stop();
        }
    }

    private MongoImportProcess startMongoImport(String dbName, String collection, String jsonFile, Boolean jsonArray, Boolean upsert, Boolean drop)
            throws IOException {
        IMongoImportConfig mongoImportConfig = new MongoImportConfigBuilder()
                .version(Version.Main.PRODUCTION)
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

    private MongodProcess startMongod() throws IOException {
        IMongodConfig mongoConfigConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .build();

        MongodStarter defaultInstance = MongodStarter.getDefaultInstance();
        MongodExecutable mongodExecutable = defaultInstance.prepare(mongoConfigConfig);
        MongodProcess mongod = mongodExecutable.start();
        return mongod;
    }

}
